import java.awt.*;

public class Fighter extends MovingObject{
    boolean lflag;//←が押されているか
    boolean rflag;//→が押されているか
    boolean uflag;//↑が押されているか
    boolean dflag;//↓が押されているか
    boolean sflag;//スペースキーが押されているか
    int delaytime;//次の弾発射までの待ち時間

    Image img = getToolkit().getImage("rocket.png");//自機の画像をインスタンス化

    //constractor
    Fighter(int apWidth, int apHeight){
        x = (int)(apWidth/2);//画面の真ん中
        y = (int)(apHeight*0.9);//画面の下の方
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
    
    void move(Graphics buf, int apWidth, int apHeight){//画像を取り込まない場合
        buf.setColor(Color.green);
        buf.fillRect(x-w, y-h, 2*w, 2*h);
        if(lflag && !rflag && x>w){//←ON、→OFF、左の壁に当たっていない場合
            x = x - dx;//オブジェクトを左に動かす
        }
        if(rflag && !lflag && x<apWidth - w){//→ON、←OFF、右の壁に当たっていない場合
            x = x + dx;//オブジェクトを右に動かす
        }
        if(uflag && !dflag && y>h){//↑ON、↓OFF、上の壁に当たっていない場合
            y = y - dy;//オブジェクトを上に動かす
        }
        if(dflag && !uflag && y<apHeight - h){//↓ON、↑OFF、下の壁に当たっていない場合
            y = y + dy;//オブジェクトを下に動かす
        }
    }

    void move(Graphics gc){//画像を取り込む場合
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
