export class Cell{  //蛇里面的一个格子
    constructor(r, c){ //把r/c坐标转化成y/x坐标
        this.r = r;
        this.c = c;
        this.x = c + 0.5;
        this.y = r + 0.5;
    }
}