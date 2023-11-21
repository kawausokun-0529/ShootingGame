import java.awt.*;

public class Fighter extends MovingObject{
    boolean lflag;
    boolean rflag;
    boolean uflag;
    boolean dflag;
    boolean sflag;
    int delaytime;

    Image img = getToolkit().getImage("rocket.png");

    //constractor
    Fighter(int apWidth, int apHeight){
        x = (int)(apWidth/2);
        y = (int)(apHeight*0.9);
        dx = 5;
        dy = 5;
        w = 32;
        h = 32;
        hp = 10;
        score = 0;
        lflag = false;
        rflag = false;
        uflag = false;
        dflag = false;
        sflag = false;
        delaytime = 5;
    }

    void revive(int apWidth, int apHeight){
    }
    
    void move(Graphics buf, int apWidth, int apHeight){
        buf.setColor(Color.green);
        buf.fillRect(x-w, y-h, 2*w, 2*h);
        if(lflag && !rflag && x>w){
            x = x - dx;
        }
        if(rflag && !lflag && x<apWidth - w){
            x = x + dx;
        }
        if(uflag && !dflag && y>h){
            y = y - dy;
        }
        if(dflag && !uflag && y<apHeight - h){
            y = y + dy;
        }
    }

    void move(Graphics gc){
        gc.drawImage(img, x-w, y-h, this);

        if(lflag && !rflag && x>w){
            x = x - dx;
        }
        if(rflag && !lflag && x<1200 - w){
            x = x + dx;
        }
        if(uflag && !dflag && y>h){
            y = y - dy;
        }
        if(dflag && !uflag && y<900 - h){
            y = y + dy;
        }
        
    }
}
