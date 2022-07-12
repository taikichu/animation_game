package Server_potato;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;


public class Waste extends JPanel{
	protected static boolean stop = false ;
	private final Random random;
	private boolean make = true;
	private int x1=550;
	private int y1;
	private int bwc=15;
	private int bhc=15;
	private int xM=6;
	public Waste() {
		random=new Random();
	}
	public void setAndmove(Graphics2D g) {
		if(make==true) {
			make=false;
			//xの流れは固定//速さを変える場合は引く数字を変更(xM)
			//300~400が低レート
			y1=random.nextInt(100)+300;//y座標設定
			bhc=random.nextInt(5)+15;
			bwc=bhc;
		}
		c_bom(g);
	}
	public void c_bom(Graphics2D g) {
		if(stop) {
			xM = 0 ;
		}
		if(0<x1) {
			//bom
			g.setColor(Color.black);
			DrawMethod.drawLine( x1 , y1 , x1+bwc , y1+bhc , g );
			DrawMethod.drawLine( x1+bwc , y1 , x1 , y1+bhc , g );
			DrawMethod.drawLine( x1+bwc/2 , y1 , x1+bwc/2 , y1+bhc , g );
			DrawMethod.drawLine( x1 , y1+bhc/2 , x1+bwc , y1+bhc/2 , g );
			
			x1-=xM;
		}else {
			x1=551;
			make=true;
		}
		
	}
	public int getX() {
		return x1;
	}
	public int getY() {
		return y1;
	}
	public int getBWC() {
		return bwc;
	}
	public int getBHC() {
		return bhc;
	}
}
