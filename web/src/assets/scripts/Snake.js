import { AcGameObject } from "./AcGameObject";  
import { Cell } from "./Cell";  //定义蛇里面的一个格子


export class Snake extends AcGameObject{
    constructor(info, gamemap){
        super();

        this.id = info.id;
        this.color = info.color;
        this.gamemap = gamemap;

        this.cells = [new Cell(info.r, info.c)];  //存放蛇的身体，cells[0]存放蛇头,info.r和info.c初始点坐标
        this.next_cell = null;  //下一步目标位置

        this.speed = 5;  //蛇每秒走5个格子
        this.direction = -1;  //-1表示没有指令，0，1，2，3表示上右下左
        this.status = "idle";  //idle表示静止，move表示正在移动，die表示死亡

        this.dr = [-1,0,1,0];  //4个方向行的偏移量
        this.dc = [0,1,0,-1];  //4个方向列的偏移量

        this.step = 0;//表示回合数
        this.eps = 1e-2;//允许的误差
        
        this.eye_direction = 0 ;
        if(this.id === 1) this.eye_direction = 2; // 左下角蛇初始朝上，右上角蛇朝下

        this.eye_dx = [  //蛇眼睛不同方向x的偏移量
            [-1,1],  //方向朝上，左眼和右眼的x的偏移量分别为-1和1
            [1,1],
            [1,-1],
            [-1,-1],
        ];
        this.eye_dy = [
            [-1,-1],
            [-1,1],
            [1,1],
            [1,-1],
        ]
    }
    start(){  

    }
    set_direction(d){  //设置接口统一方向
        this.direction = d;
    }
    check_tail_increasing(){  //检测当前回合，蛇的长度是否增加
        if(this.step <= 10) return true;
        if(this.step % 3 === 1) return true;
        return false;
    }
    next_step(){  //将蛇的状态变为走下一步
        const d = this.direction;
        this.next_cell = new Cell(this.cells[0].r + this.dr[d], this.cells[0].c + this.dc[d]);  //蛇头行数加偏移行数，蛇头列数加偏移列数
        this.eye_direction = d;
        this.direction = -1;  //q清空操作
        this.status = "move";
        this.step ++ ;

        const k = this.cells.length;
        for(let i = k;i > 0;i --){
            this.cells[i] = JSON.parse(JSON.stringify(this.cells[i-1]));  //把每个小球向后移一位，先转化成JSON，再剪切掉
        }

        if(!this.gamemap.check_valid(this.next_cell)){  //下一步操作撞了，蛇死亡
            this.status = "die";
        }

        if(!this.gamemap.check_valid(this.next_cell)){  //下一步操作撞了，蛇死亡
            this.status = "die";
        }

    }
    update_move(){
        const dx = this.next_cell.x - this.cells[0].x;
        const dy = this.next_cell.y - this.cells[0].y;
        const distance = Math.sqrt(dx * dx + dy * dy);
        
        if(distance < this.eps)  //距离小于误差，走到目标点了
        {   
            this.cells[0] = this.next_cell;  //目标点是新蛇头
            this.next_cell = null;
            this.status = "idle";  //走完了，停下来

            if(!this.check_tail_increasing()){  //发现蛇没变长
                this.cells.pop();
            }
        }else{
            const move_distance = this.speed * this.timedelta / 1000 ;  //每两帧之间走过的距离：速度*时间
            this.cells[0].x += move_distance * dx / distance;
            this.cells[0].y += move_distance * dy / distance;

            if(!this.check_tail_increasing()){  //蛇不变长，蛇移动
                const k = this.cells.length;
                const tail = this.cells[k - 1], tail_target = this.cells[k - 2];
                const tail_dx = tail_target.x - tail.x;
                const tail_dy = tail_target.y - tail.y;
                tail.x += move_distance * tail_dx / distance;
                tail.y += move_distance * tail_dy / distance;
             }
        }
    }
    update(){  //每一帧执行一次，把每帧需要更新的逻辑放在update里
        if(this.status==='move'){
            this.update_move();
        }
        this.render();
    }
    render(){
        const L= this.gamemap.L;  //L即每个小球直径
        const ctx = this.gamemap.ctx;  //对画布的引用

        ctx.fillStyle =this.color;
        if(this.status === "die"){
            ctx.fillStyle = "white";
        }
        for(const cell of this.cells){
            ctx.beginPath();
            ctx.arc(cell.x*L,cell.y*L,L/2*0.8,0,Math.PI*2);
            ctx.fill();
        }

        //解决蛇中的两个球之间有缝隙
        for(let i = 1;i < this.cells.length; i ++){  //枚举相邻两个圈
            const a = this.cells[i - 1], b = this.cells[i];  //定义两个圈
            if(Math.abs(a.x - b.x) < this.eps && Math.abs(a.y - b.y) < this.eps) //两个球已经重合，不需要画了。abs方法，取绝对值
                continue;
            if(Math.abs(a.x - b.x) < this.eps){  //竖直方向
                ctx.fillRect((a.x - 0.4) * L, Math.min(a.y,b.y) * L , L * 0.8, Math.abs(a.y - b.y) * L);  //fillRect方法填充矩形，依次是矩形左上角x坐标，矩形左上角y坐标，矩形的宽度，矩形的高度
            }else {  //水平方向
                ctx.fillRect(Math.min(a.x, b.x) * L ,(a.y-0.4) * L,Math.abs(a.x - b.x) * L,L*0.8); //坐标存的是相对距离，要*L变成水平距离
            }
        }
        
        ctx.fillStyle = "black";
        for(let i = 0; i < 2;i ++ ){
            const eye_x = (this.cells[0].x + this.eye_dx[this.eye_direction][i]*0.15)*L; //眼睛横坐标
            const eye_y = (this.cells[0].y + this.eye_dy[this.eye_direction][i]*0.15)*L; //眼睛纵坐标
            ctx.beginPath();
            ctx.arc(eye_x, eye_y, L * 0.05, 0,Math.PI*2);  //创建圆，圆中心的x坐标，圆中心的y坐标，圆的半径，起始角，结束角
            ctx.fill();
        }
    }
}