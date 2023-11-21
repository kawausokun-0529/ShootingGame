import java.awt.*;

//EnemyAクラスとオブジェクトのサイズ、使用する画像ファイル以外同様

class EnemyB extends MovingObject{
    Image img = getToolkit().getImage("enemyB.png");
    
    EnemyB(int apWidth, int apHeight){
        super(apWidth, apHeight);
        w = 12;
        h = 6;
        hp = 0;
        point = 35;
    }

    void move(Graphics buf, int apWidth, int apHeight){
        buf.setColor(Color.orange);
        if(hp > 0){
            //buf.setColor(Color.black);
            buf.fillOval(x-w, y-h, 2*w, 2*h);
            x = x + dx;
            y = y + dy;
            if(y > apHeight+h){
                hp = 0;
            }
        }
    }

    void move(Graphics gc, int apHeight){
        if(hp>0){
            gc.drawImage(img, x-w, y-h, this);
            x = x + dx; 
            y = y + dy;
            if(y>apHeight+h){
                hp = 0;
            }
        }
    }

    void revive(int apWidth, int apHeight){
        x = (int)(Math.random()*(apWidth-2*w)+w);
        y = -h;
        dy = 2;
        if(x < apWidth/2){
            dx = (int)(Math.random()*2);
        }
        else{
            dx = -(int)(Math.random()*2);
        }
        hp = 3;
    }
}
