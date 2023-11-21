import java.awt.*;

//発射する弾のクラス

class FighterBullet extends MovingObject{
    //constructor
    FighterBullet(){
        w = h = 3;//弾の半径
        dx = 0; dy = -6;
        hp = 0;//初期状態は非アクティブ
    }
    
    //method
    void move(Graphics buf, int apWidth, int apHeight){
        if(hp>0){
            buf.setColor(Color.yellow);
            buf.fillOval(x-w, y-h, 2*w, 2*h);
            if(y>0 && y < apHeight && x>0 && x<apWidth){//弾が画面内にあれば
                y = y + dy;//座標の更新
            }
            else{
                hp = 0;//画面買いに出たらhpを0にする
            }
        }
    }
    void revive(int x, int y){//x,yはFighterの位置
        this.x = x;
        this.y = y;
        hp = 1;//発射したらアクティブに
    }
}
