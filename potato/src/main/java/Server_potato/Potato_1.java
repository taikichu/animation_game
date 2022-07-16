package Server_potato;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Potato_1の
 * 使うパーツは 目、体、腕、脚
 **/
public class Potato_1 extends Character{

	
	static Single_parts body ;
	Double_parts eye , leg , arm ;
	
	static ArrayList<Single_parts> parts_S = new ArrayList<Single_parts>();
	static ArrayList<Double_parts> parts_D = new ArrayList<Double_parts>();
	
	static Color body_color=Color.gray;
	
	/**arm moveの増減表記定数*/
	int tpaxl1=1,tpayl1,tpaxl2=1,tpayl2,tpaxr1=0,tpayr1,tpaxr2=0,tpayr2;

	/**目のheight*/
	int eye_h;//
	/**目のweight*/
	int eye_w;
	/**体のweight*/
	static int body_w_L = 40;
	/**体のheight*/
	static int body_h_L=60;
	
	/**体のw方向の曲がり距離*/
	static int body_aw_L=120;
	/**体のh方向の曲がり距離*/
	static int body_ah_L=120;

	/**振る肩の範囲*/
	int armlange1;
	/**振る手の範囲*/
	int armlange2;
	
	/**こぶしのweight*/
	int hand_w=10;
	/**こぶしのheight*/
	int hand_h=10;
	

	private int actFlag2;
	private int count;
	/**ジャンプ値*/
	private int JP;
	private int countF;
	
	/**bodyの初期座標ストック*/
	final static int body_x_L_N=50,body_y_L_N=350;
	
	/**armの初期座標ストック*/
	final int ax1l,ay1l,ax2l,ay2l,ax1r,ay1r,ax2r,ay2r;
	/**legの初期座標ストック*/
	final int lx1l,ly1l,lx2l,ly2l,lx1r,ly1r,lx2r,ly2r;
	
	Potato_1( int w , int h ){
		
		body = new Single_parts("body");
		parts_S.add(body);
		
		eye = new Double_parts("eye");
		arm = new Double_parts("arm");
		leg = new Double_parts("leg");
		parts_D.add(eye);
		parts_D.add(arm);
		parts_D.add(leg);
		
		body.addparts( body_x_L_N+w , body_y_L_N-h , LEFT);
		
		ax1l=body.getparts("x", 0, LEFT)+(body_w_L)/10*3;
		ay1l=body.getparts("y", 0, LEFT)+body_h_L/2;
		ax1r=body.getparts("x", 0, LEFT)+(body_w_L)/10*7;
		ay1r=body.getparts("y", 0, LEFT)+body_h_L/2;
		
		ax2l=ax1l-10;
		ay2l=ay1l+40;
		ax2r=ax1r+10;
		ay2r=ay1r+40;
		
		arm.addParts(ax1l, ay1l, ax1r, ay1r, LEFT);//腕の0番目の両腕を定義
		arm.addParts(ax2l, ay2l, ax2r, ay2r, LEFT);//腕の1番目の両腕を定義

		armlange2=ax2r-ax2l;
		armlange1=ax1r-ax1l;
		
		eye.addParts(arm.getPartsL("x", 0, LEFT)+body_w_L/8,
				arm.getPartsL("y", 0, LEFT)-body_h_L/3,
				arm.getPartsR("x", 0, LEFT)+body_w_L/8,
				arm.getPartsR("y", 0, LEFT)-body_h_L/3,
				LEFT);
		
		lx1l=body.getparts("x", 0, LEFT)+(body_w_L)/10*3;
		ly1l=body.getparts("y", 0, LEFT)+(body_h_L*3)/4;
		lx1r=body.getparts("x", 0, LEFT)+(body_w_L)/10*7;
		ly1r=body.getparts("y", 0, LEFT)+(body_h_L*3)/4;
		
		lx2l=lx1l-15;
		ly2l=ly1l+15;
		lx2r=lx1r-15;
		ly2r=ly1r+15;
		
		leg.addParts(lx1l ,ly1l,lx1r,ly1r,LEFT);
		leg.addParts(lx2l ,ly2l,lx2r,ly2r,LEFT);		
	}
	
	void fullset(Graphics g2) {//キャラの描画を設定

		movearmL(g2);setarmL(g2);
		moveeyeL(g2);seteyeL(g2);
		movelegL(g2);setlegL(g2);

		movebody(g2);setbody(g2);

		moveeyeR(g2);seteyeR(g2);
		movearmR(g2);setarmR(g2);
		movelegR(g2);setlegR(g2);
	}
	void stopset(Graphics g2) {
		setarmL(g2);
		seteyeL(g2);
		setlegL(g2);
		setbody(g2);
		seteyeR(g2);
		setarmR(g2);
		setlegR(g2);
	}
	
//	void head(Graphics g){
//	}
//	void body(Graphics g) {
//		
//	}
//	void arm(Graphics g) {
//
//	}
//	void leg(Graphics g) {
//	}
//	
//	void sethead(Graphics g) {
//		
//	}
	
	void seteyeL(Graphics g) {
		//左目描画
		g.setColor(Color.black);
		DrawMethod.fillRoundRect( eye.getPartsL( "x" , 0 , LEFT ) , eye.getPartsL( "y" , 0 , LEFT ) , eye_w,eye_h , body_aw_L , body_ah_L , g );//左1
		g.setColor(Color.white);
		DrawMethod.fillArc( eye.getPartsL( "x" , 0 , LEFT ) , eye.getPartsL( "y" , 0 , LEFT ) , eye_w,eye_h ,body_aw_L , body_ah_L , g );//eye 左2
		g.setColor(Color.blue);
		DrawMethod.fillArc( eye.getPartsL( "x" , 0 , LEFT )-(eye_w)/2 , eye.getPartsL( "y" , 0 , LEFT )-(eye_h)/2 , eye_w+8 , eye_h+8 , 0 , 180 , g );//eye 2 瞼
	}
	void moveeyeL(Graphics g) {
		//ポジション
		eye.setParts(arm.getPartsL("x", 0, LEFT)+body_w_L/8,
				arm.getPartsL("y", 0, LEFT)-body_h_L/3,
				eye.getPartsR("x", 0, LEFT),
				eye.getPartsR("y", 0, LEFT),
				0, LEFT);
		//両目のサイズ
		eye_h=body_h_L/5;//height
		eye_w=body_w_L/5;//weight
	}
	void seteyeR(Graphics g) {
		//右目描画
		g.setColor(Color.black);
		DrawMethod.fillRoundRect( eye.getPartsR( "x" , 0 , LEFT ) , eye.getPartsR( "y" , 0 , LEFT ) , eye_w,eye_h , body_aw_L , body_ah_L , g );//eye 右1
		g.setColor(Color.white);
		DrawMethod.fillArc( eye.getPartsR( "x" , 0 , LEFT ) , eye.getPartsR( "y" , 0 , LEFT ) , eye_w,eye_h , body_aw_L , body_ah_L , g );//eye 右2
		g.setColor(Color.blue);
		DrawMethod.fillArc( eye.getPartsR( "x" , 0 , LEFT )-(eye_w)/2 , eye.getPartsR( "y" , 0 , LEFT )-(eye_h)/2 , eye_w+8 , eye_h+8 , 0 , 180 , g );//eye 2 瞼
	}
	void moveeyeR(Graphics g) {
		//ポジション
		eye.setParts(eye.getPartsL("x",0, LEFT), eye.getPartsL("y",0, LEFT), arm.getPartsR("x", 0, LEFT)+body_w_L/8, arm.getPartsR("y", 0, LEFT)-body_h_L/3, 0, LEFT);
	}
	void setbody(Graphics g) {
		//体描画
			if(getdead()) {
				WorkGui.heart_color=Color.blue;
				if(WorkGui.pertime%2==0)g.setColor(Color.black);
				else g.setColor(Color.red);
			}
			else g.setColor(body_color);
		//体描画
		DrawMethod.fillRoundRect( body.getparts( "x" , 0 , LEFT ) , body.getparts( "y" , 0 , LEFT ) , body_w_L , body_h_L , body_aw_L , body_ah_L , g );
	}
	void movebody(Graphics g) {
		
	}
	void setarmL(Graphics g) {
		//左腕描画
		g.setColor(Color.black);
		DrawMethod.drawLine( arm.getPartsL( "x" , 0 , LEFT ) , arm.getPartsL( "y" , 0 , LEFT ), arm.getPartsL( "x" , 1 , LEFT ), arm.getPartsL( "y" , 1 , LEFT ), g );//左腕
		g.setColor(Color.white);
		DrawMethod.fillArc( arm.getPartsL("x", 1, LEFT)-hand_h/2 , arm.getPartsL( "y" , 1 , LEFT )-hand_w/2 , hand_w,hand_h , 0 , 360 , g );//左手
		g.setColor(Color.black);
		DrawMethod.drawArc( arm.getPartsL( "x" , 1 , LEFT )-hand_h/2 , arm.getPartsL( "y" , 1 , LEFT )-hand_w/2 , hand_w,hand_h , 0  , 360 , g );//左手の枠
	}
	void movearmL(Graphics g) {
		//左肩,左手
		armM1(ax1l,ax1r,arm.getPartsL("x", 0, LEFT),tpaxl1);armM1(arm.getPartsL("x", 1, LEFT),ax2r,tpaxl2,ax2l,armlange1,armlange2);
	}
	void setarmR(Graphics g) {
		//右腕描画
		g.setColor(Color.black);
		DrawMethod.drawLine( arm.getPartsR( "x" , 0 , LEFT ) , arm.getPartsR( "y" , 0 , LEFT ) , arm.getPartsR( "x" , 1 , LEFT ) , arm.getPartsR( "y" , 1 , LEFT ) , g );//右腕
		g.setColor(Color.white);
		DrawMethod.fillArc( arm.getPartsR( "x" , 1 , LEFT )-hand_h/2 , arm.getPartsR( "y" , 1 , LEFT )-hand_w/2 , hand_w , hand_h , 0 , 360 , g );//右手
		g.setColor(Color.black);
		DrawMethod.drawArc( arm.getPartsR( "x" , 1 , LEFT )-hand_h/2 , arm.getPartsR( "y" , 1 , LEFT )-hand_w/2 , hand_w , hand_h , 0 , 360 , g );
	}
	void movearmR(Graphics g) {
		//右肩,右手
		armM1(ax1r,ax1l,arm.getPartsR("x", 0, LEFT),tpaxr1);armM1(arm.getPartsR("x", 1, LEFT),ax2l,tpaxr2,ax2r,armlange1,armlange2);
	}
	void setlegL(Graphics g) {
		g.setColor(Color.black);
		DrawMethod.drawLine( leg.getPartsL( "x" , 0 , LEFT ) , leg.getPartsL( "y" , 0 , LEFT ) , leg.getPartsL( "x" , 1 , LEFT ) , leg.getPartsL( "y" , 1 , LEFT ) , g );
	}
	void movelegL(Graphics g) {
		leg.setParts(arm.getPartsL("x", 0, LEFT)-2, arm.getPartsL("y", 0, LEFT)+body_h_L/4, leg.getPartsR("x", 0, LEFT), leg.getPartsR("y", 0, LEFT), 0, LEFT);
		leg.setParts(arm.getPartsL("x", 0, LEFT)-12, arm.getPartsL("y", 0, LEFT)+body_h_L/4+15, leg.getPartsR("x", 1, LEFT), leg.getPartsR("y", 1, LEFT), 1, LEFT);		
	}
	void setlegR(Graphics g) {
		g.setColor(Color.black);
		DrawMethod.drawLine( leg.getPartsR( "x" , 0 , LEFT ) , leg.getPartsR( "y" , 0 , LEFT ) , leg.getPartsR( "x" , 1 , LEFT ) , leg.getPartsR( "y" , 1 , LEFT ), g );
	}
	void movelegR(Graphics g) {
		leg.setParts(leg.getPartsL("x", 0, LEFT), leg.getPartsL("y", 0, LEFT), arm.getPartsR("x", 0, LEFT)-2, arm.getPartsR("y", 0, LEFT)+body_h_L/4, 0, LEFT);
		leg.setParts(leg.getPartsL("x", 1, LEFT), leg.getPartsL("y", 1, LEFT), arm.getPartsR("x", 0, LEFT)-12, arm.getPartsR("y", 0, LEFT)+body_h_L/4+15, 1, LEFT);
	}
	void armM1( int ax1T , int ax1N , int arm_x1_T , int tpaxT1 ) {//肩の動作を設定//N..not(逆側)//T..True(正側)
		//left
		if(ax1T==ax1l) {
			if(arm_x1_T<=ax1N&&tpaxT1==1) {//ax1l,arm_x1_L,tpaxl1,
				arm_x1_T++;
			}else tpaxT1=0;
			if(ax1T<=arm_x1_T&&tpaxT1==0){
				arm_x1_T--;
			}else {
				tpaxT1=1;
				armM0(ax1T,ax1N,arm_x1_T,tpaxT1);
			}
			arm.setParts(arm_x1_T,arm.getPartsL("y",0,LEFT),arm.getPartsR("x",0,LEFT),arm.getPartsR("y",0,LEFT),0,LEFT);
			tpaxl1=tpaxT1;
		}
		//right
		else {
			if(arm_x1_T>=ax1N&&tpaxT1==0) {
				arm_x1_T--;
			}else tpaxT1=1;
			if(ax1T>=arm_x1_T&&tpaxT1==1){
				arm_x1_T++;
			}else {
				tpaxT1=0;
				armM0(ax1T,ax1N,arm_x1_T,tpaxT1);
			}
			arm.setParts(arm.getPartsL("x", 0,LEFT), arm.getPartsL("y", 0,LEFT), arm_x1_T,arm.getPartsR("y", 0,LEFT),0,LEFT);
			tpaxr1=tpaxT1;
		}
	}	
	void armM0( int ax1T , int ax1N , int arm_x1_T , int tpaxT1 ) {//分岐した肩の動作を設定
		//left
		if(ax1T==ax1l) {
			if(ax1T<=arm_x1_T&&tpaxT1==0){
				arm_x1_T--;
			}else tpaxT1=1;
			arm.setParts(arm_x1_T,arm.getPartsL("y",0,LEFT),arm.getPartsR("x",0,LEFT),arm.getPartsR("y",0,LEFT), 0, LEFT);
			tpaxl1=tpaxT1;
			
		}
		//right
		else {
			if(ax1T>=arm_x1_T&&tpaxT1==1){
				arm_x1_T++;
			}else tpaxT1=0;
			arm.setParts(arm.getPartsL("x", 0,LEFT), arm.getPartsL("y", 0,LEFT), arm_x1_T,arm.getPartsR("y", 0,LEFT),0,LEFT);
			tpaxr1=tpaxT1;
			
		}
	}
	void armM1(int arm_x2_T , int ax2N,int tpaxT2 , int ax2T, int armlange1, int armlange2 ){//手の動作を設定
		//left
		if(ax2T==ax2l) {
			if(arm_x2_T<ax2N&&tpaxT2==1) {//arm_x2_L,ax2r,tpaxl2,armlange,armlange1,ax2l				
				arm_x2_T+=armlange2/armlange1;
			}else{
				tpaxT2=0;
			}
			if(ax2T<arm_x2_T&&tpaxT2==0){
				arm_x2_T-=armlange2/armlange1;
			}else {
				 tpaxT2=1;
				 armM0(arm_x2_T,tpaxT2,ax2T,armlange1,armlange2);
			}
			arm.setParts(arm_x2_T,arm.getPartsL("y",1,LEFT),arm.getPartsR("x",1,LEFT),arm.getPartsR("y",1,LEFT), 1, LEFT);
			tpaxl2=tpaxT2;
		}
		//right
		else {
			if(arm_x2_T>ax2N&&tpaxT2==0) {//arm_x2_L,ax2r,tpaxl2,armlange,armlange1,ax2l				
				arm_x2_T-=armlange2/armlange1;
			}else{
				tpaxT2=1;
			}
			if(ax2T>arm_x2_T&&tpaxT2==1){
				arm_x2_T+=armlange2/armlange1;
			}else {
				 tpaxT2=0;
				 armM0(arm_x2_T,tpaxT2,ax2T,armlange1,armlange2);
			}
			arm.setParts(arm.getPartsL("x",1,LEFT),arm.getPartsL("y",1,LEFT),arm_x2_T,arm.getPartsR("y",1,LEFT), 1, LEFT);
			tpaxr2=tpaxT2;
		}
	}
	void armM0(int arm_x2_T , int tpaxT2 , int ax2T, int armlange1 , int armlange2) {//分岐に該当した手の動作を設定
		//left
		if(ax2T==ax2l) {
			if(ax2T<arm_x2_T&&tpaxT2==0){
				arm_x2_T-=armlange2/armlange1;
			}else {
				 tpaxT2=1;
			}
			arm.setParts(arm_x2_T,arm.getPartsL("y",1,LEFT),arm.getPartsR("x",1,LEFT),arm.getPartsR("y",1,LEFT), 1, LEFT);
			tpaxl2=tpaxT2;
		}
		//right
		else {
			if(ax2T>arm_x2_T&&tpaxT2==1){
				arm_x2_T+=armlange2/armlange1;
			}else tpaxT2=0;
			arm.setParts(arm.getPartsL("x",1,LEFT),arm.getPartsL("y",1,LEFT),arm_x2_T,arm.getPartsR("y",1,LEFT), 1, LEFT);
			tpaxr2=tpaxT2;
		}	
	}
	
	int getBX() {
		return body.getparts("x", 0, LEFT);
	}
	int getBY() {
		return  body.getparts("y", 0, LEFT);
	}
	int getBW() {
		return body_w_L;
	}
	int getBH() {
		return body_h_L;
	}
	int getBAW() {
		return body_aw_L;
	}
	int getBAH() {
		return body_ah_L;
	}
	void setdead() {
		WorkGui.countdownR=8;
	}
	
	boolean getdead() {//障害物に接触した時の時間をカウント、カウントが終わったら終了
		WorkGui.countdownR--;
        return WorkGui.countdownR > 0;
	}
	public void jump_short() {//持続ジャンプができない
		
		countF = WorkGui.countF;
		JP = WorkGui.JP;
		count = WorkGui.count;
		actFlag2 = WorkGui.actFlag2;
		
		actFlag2-=2;
		if(-JP<count&&countF==0) {
			body.setparts(body.getparts("x", 0, LEFT), body.getparts("y", 0, LEFT)-JP/8, 0, LEFT);
			arm.setParts(arm.getPartsL("x", 0, LEFT), arm.getPartsL("y", 0, LEFT)-JP/8, arm.getPartsR("x", 0, LEFT), arm.getPartsR("y", 0, LEFT)-JP/8, 0, LEFT);
			count-=JP/8;
		}else if(-JP>=count&&countF==0){
			countF=1;
		}
		if(count<0&&countF==1&&actFlag2<-30){//ボタンを押されなかったらここを実行
			body.setparts(body.getparts("x", 0, LEFT), body.getparts("y", 0, LEFT)+JP/8, 0, LEFT);
			arm.setParts(arm.getPartsL("x", 0, LEFT), arm.getPartsL("y", 0, LEFT)+JP/8, arm.getPartsR("x", 0, LEFT), arm.getPartsR("y", 0, LEFT)+JP/8, 0, LEFT);
				count+=JP/8;
		}else if(count>=0&&countF==1){// 下がり切ったら実行される//
			countF=0;
			WorkGui.actFlag = "";
			WorkGui.actnow = "";
			actFlag2=0;
		}
		System.out.println(actFlag2);
		
		WorkGui.countF = countF ;
		WorkGui.JP = JP ;
		WorkGui.count = count ;
		WorkGui.actFlag2 = actFlag2;
	}
	public void jump_high() {//持続ジャンプができない...timerよりボタンが離されてしまう？
		countF = WorkGui.countF;
		JP = WorkGui.JP;
		count = WorkGui.count;
		actFlag2 = WorkGui.actFlag2;
		
		actFlag2-=2;
		if(-JP*2<count&&countF==0) {
			body.setparts(body.getparts("x", 0, LEFT), body.getparts("y", 0, LEFT)-JP*2/8, 0, LEFT);
			arm.setParts(arm.getPartsL("x", 0, LEFT), arm.getPartsL("y", 0, LEFT)-JP*2/8, arm.getPartsR("x", 0, LEFT), arm.getPartsR("y", 0, LEFT)-JP*2/8, 0, LEFT);
			count-=JP*2/8;
		}else if(-JP*2>=count&&countF==0){
			countF=1;
		}
		if(count<0&&countF==1&&actFlag2<-30){//ボタンを押されなかったらここを実行
			body.setparts(body.getparts("x", 0 , LEFT), body.getparts("y", 0 , LEFT)+JP*2/8, 0, LEFT);
			arm.setParts(arm.getPartsL("x", 0, LEFT), arm.getPartsL("y", 0, LEFT)+JP*2/8, arm.getPartsR("x", 0, LEFT), arm.getPartsR("y", 0, LEFT)+JP*2/8, 0, LEFT );	
			count+=JP*2/8;
		}else if(count>=0&&countF==1){// 下がり切ったら実行される//
			countF=0;
			WorkGui.actFlag = "";
			WorkGui.actnow = "";
			actFlag2=0;
		}
		System.out.println(actFlag2);
		
		WorkGui.countF = countF ;
		WorkGui.JP = JP ;
		WorkGui.count = count ;
		WorkGui.actFlag2 = actFlag2;
	}
}
