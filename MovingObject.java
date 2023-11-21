import java.awt.*;

//all moving objects' parent class (abstract class)
//variable: place(x,y), speed(vx, vy), HP(hp), width w, height h
//method: move, collisionCheck, revive

abstract class MovingObject extends Frame{ //abstract class
    //field variable
    int x; //center coordinate
    int y;
    int dx; //speed
    int dy;
    int w; //half of width
    int h; //half of height
    int hp; //hit point
    int score, point;

    //constructor1
    MovingObject(){
    }
    //constructor2
    MovingObject(int width, int height){
        x = (int) (Math.random()*width);
        y = (int) (Math.random()*height);
        dx = (int) (Math.random()*6-3);
        dy = (int) (Math.random()*6-3);
        w = 2;
        h = 2;
        hp = 10;
        score = 0;
        point = 0;
    }

    abstract void move(Graphics buf, int apWidth, int apHeight);

    abstract void revive(int w, int h);

    boolean collisionCheck(MovingObject obj, Fighter ftr, int point){
        if(Math.abs(this.x-obj.x) <= (this.w+obj.w) && Math.abs(this.y-obj.y) <= (this.h+obj.h)){
            this.hp--;
            obj.hp--;
            ftr.score = ftr.score + point;
            return true;
        }else{
            return false;
        }
    }

    boolean collisionCheck(MovingObject obj, int point){
        if(Math.abs(this.x-obj.x) <= (this.w+obj.w) && Math.abs(this.y-obj.y) <= (this.h+obj.h)){
            this.hp--;
            obj.hp--;
            return true;
        }else{
            return false;
        }
    }
}
