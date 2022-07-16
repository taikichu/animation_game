package Client_potato;//client

import java.awt.Graphics;

public class DrawMethod {
	public static String field = "offline";
	static String[] draw6_g = new String[8];
	static String[] draw4_g = new String[6];
	static String[] draw3_g = new String[5];

	public static void drawLine(int x1, int y1, int x2 , int y2 , Graphics g ) {
		if(field.equals("online")) {
			draw4_g[0] = "drawLine " ;
			draw4_g[1] = x1+" " ;
			draw4_g[2] = y1+" " ;
			draw4_g[3] = x2+" " ;
			draw4_g[4] = y2+" " ;
			draw4_g[5] = null ;
			
		}else {
			g.drawLine(x1 , y1 , x2 , y2 );
		}
	}
	public static void drawString(String line , int x , int y , Graphics g) {
		if(field.equals("online")) {
			draw3_g[0] = "drawString " ;
			draw3_g[1] = line+" " ;
			draw3_g[2] = x+" " ;
			draw3_g[3] = y+" " ;
			draw3_g[4] = null;
			
		}else {
			System.out.println("drawStringで受け取ったString : " + line);
			g.drawString(line , x , y );
		}
	}
	public static void drawRect( int x , int y , int w , int h , Graphics g ) {
		if(field.equals("online")) {
			draw4_g[0] = "drawRect " ;
			draw4_g[1] = x+" " ;
			draw4_g[2] = y+" " ;
			draw4_g[3] = w+" " ;
			draw4_g[4] = h+" " ;
			draw4_g[5] = null;
		}else {
			g.drawRect(x , y , w , h );
		}
	}
	public static void drawArc( int x , int y , int w , int h , int sa , int aa , Graphics g ) {
		if(field.equals("online")) {
			draw6_g[0] = "drawArc ";
			draw6_g[1] = x+ " " ;
			draw6_g[2] = y+ " " ;
			draw6_g[3] = w+ " " ;
			draw6_g[4] = h+ " " ;
			draw6_g[5] = sa+" " ;
			draw6_g[6] = aa+" " ;
			draw6_g[7] = null;
		}else {
			g.drawArc(x, y, w, h, sa, aa);
		}
	}
	public static void fillRoundRect(int x , int y , int w , int h , int aw , int ah , Graphics g) {
		if(field.equals("online")) {
			draw6_g[0] = "fillRoundRect ";
			draw6_g[1] = x+ " " ;
			draw6_g[2] = y+ " " ;
			draw6_g[3] = w+ " " ;
			draw6_g[4] = h+ " " ;
			draw6_g[5] = aw+" " ;
			draw6_g[6] = ah+" " ;
			draw6_g[7] = null;
			
		}else {
			g.fillRoundRect(x, y, w, h, aw, ah);
		}
	}
	public static void fillArc( int x , int y , int w , int h , int sa , int aa , Graphics g ) {
		if(field.equals("online")) {
			draw6_g[0] = "fillArc";
			draw6_g[1] = x+ " " ;
			draw6_g[2] = y+ " " ;
			draw6_g[3] = w+ " " ;
			draw6_g[4] = h+ " " ;
			draw6_g[5] = sa+" " ;
			draw6_g[6] = aa+" " ;
			draw6_g[7] = null;
			
		}else {
			g.fillArc(x, y, w, h, sa, aa);
		}
	}
}
