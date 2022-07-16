package Server_potato;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;


public class DrawMethod {
	static String field = "online";
	static String[] draw9_g = new String[9];
	static String[] draw7_g = new String[7];
	public static void drawLine(int x1, int y1, int x2 , int y2 , Graphics g ) {
		if(field.equals("online")) {
			draw7_g[0] = "drawLine " ;
			draw7_g[1] = g.getColor()+" ";
			draw7_g[2] = x1+" " ;
			draw7_g[3] = y1+" " ;
			draw7_g[4] = x2+" " ;
			draw7_g[5] = y2+" " ;
			draw7_g[6] = "null "  ;
			for(String text : draw7_g) {
				DatagramPacket sendPacket=new DatagramPacket(text.getBytes(),text.getBytes().length,new InetSocketAddress(Receive_S.RehostIP, 10008));//Datagram_Send_S.address_Port
//				sendPacket.setPort(Datagram_Send_S.SENDBACK_PORT);
				try {
					Send_S.socket_Client_10008.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			g.drawLine(x1 , y1 , x2 , y2 );
	}
	public static void drawString(String line , int x , int y , int size , Graphics g) {
		if(field.equals("online")) {
			draw7_g[0] = "drawString " ;
			draw7_g[1] = g.getColor()+" ";
			draw7_g[2] = line+" " ;
			draw7_g[3] = x+" " ;
			draw7_g[4] = y+" " ;
			draw7_g[5] = size+" " ;
			draw7_g[6] = "null ";
			for(String text : draw7_g) {
				DatagramPacket sendPacket=new DatagramPacket(text.getBytes(),text.getBytes().length,new InetSocketAddress(Receive_S.RehostIP, 10008));//
//				sendPacket.setPort(Datagram_Send_S.SENDBACK_PORT);
				try {
					Send_S.socket_Client_10008.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			g.drawString(line , x , y );
	}
	public static void drawRect( int x , int y , int w , int h , Graphics g ) {
		if(field.equals("online")) {
			draw7_g[0] = "drawRect " ;
			draw7_g[1] = g.getColor()+" ";
			draw7_g[2] = x+" " ;
			draw7_g[3] = y+" " ;
			draw7_g[4] = w+" " ;
			draw7_g[5] = h+" " ;
			draw7_g[6] = "null ";
			for(String text : draw7_g) {
				DatagramPacket sendPacket=new DatagramPacket(text.getBytes(),text.getBytes().length,new InetSocketAddress(Receive_S.RehostIP, 10008));//Datagram_Send_S.address_Port
//				sendPacket.setPort(Datagram_Send_S.SENDBACK_PORT);
				try {
					Send_S.socket_Client_10008.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
			g.drawRect(x , y , w , h );
	}
	public static void drawArc( int x , int y , int w , int h , int sa , int aa , Graphics g ) {
		if(field.equals("online")) {
			draw9_g[0] = "drawArc ";
			draw9_g[1] = g.getColor()+" ";
			draw9_g[2] = x+ " " ;
			draw9_g[3] = y+ " " ;
			draw9_g[4] = w+ " " ;
			draw9_g[5] = h+ " " ;
			draw9_g[6] = sa+" " ;
			draw9_g[7] = aa+" " ;
			draw9_g[8] = "null ";
			for(String text : draw9_g) {
				DatagramPacket sendPacket=new DatagramPacket(text.getBytes(),text.getBytes().length,new InetSocketAddress(Receive_S.RehostIP, 10008));//Datagram_Send_S.address_Port
				try {
					Send_S.socket_Client_10008.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			g.drawArc(x, y, w, h, sa, aa);
	}
	public static void fillRoundRect(int x , int y , int w , int h , int aw , int ah , Graphics g) {
		if(field.equals("online")) {
			draw9_g[0] = "fillRoundRect ";
			draw9_g[1] = g.getColor()+" ";
			draw9_g[2] = x+ " " ;
			draw9_g[3] = y+ " " ;
			draw9_g[4] = w+ " " ;
			draw9_g[5] = h+ " " ;
			draw9_g[6] = aw+" " ;
			draw9_g[7] = ah+" " ;
			draw9_g[8] = "null ";
			for(String text : draw9_g) {
				DatagramPacket sendPacket=new DatagramPacket(text.getBytes(),text.getBytes().length,new InetSocketAddress(Receive_S.RehostIP, 10008));//Datagram_Send_S.address_Port
//				sendPacket.setPort(Datagram_Send_S.SENDBACK_PORT);
				try {
					Send_S.socket_Client_10008.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
			g.fillRoundRect(x, y, w, h, aw, ah);
	}
	public static void fillArc( int x , int y , int w , int h , int sa , int aa , Graphics g ) {
		if(field.equals("online")) {
			draw9_g[0] = "fillArc ";
			draw9_g[1] = g.getColor()+" ";
			draw9_g[2] = x+ " " ;
			draw9_g[3] = y+ " " ;
			draw9_g[4] = w+ " " ;
			draw9_g[5] = h+ " " ;
			draw9_g[6] = sa+" " ;
			draw9_g[7] = aa+" " ;
			draw9_g[8] = "null ";
			for(String text : draw9_g) {
				DatagramPacket sendPacket=new DatagramPacket(text.getBytes(),text.getBytes().length,new InetSocketAddress(Receive_S.RehostIP, 10008));//Datagram_Send_S.address_Port
//				sendPacket.setPort(Datagram_Send_S.SENDBACK_PORT);
				try {
					Send_S.socket_Client_10008.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
			g.fillArc(x, y, w, h, sa, aa);
	}
}
