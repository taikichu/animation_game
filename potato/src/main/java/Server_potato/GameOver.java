package Server_potato;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;

public class GameOver {
	
	private static int count = 0 ;
	private static int i = 0;
	public GameOver() {
		count = 0;
	}
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		count += 5;


		DrawMethod.fillRoundRect(0, 0 , WorkGui.w, count , 0 , 0 , g2 );


		g2.setFont(new Font("Serif", Font.PLAIN, 16+i));
		g2.setPaint(Color.white);
		if(  i  < 120){
			i+=2;
			DrawMethod.drawString((WorkGui.framespeed* WorkGui.pertime)/1000 + "second", 10, 15+i , 16+i/2 , g2);
		}
		else{
			g2.setFont(new Font("Serif", Font.PLAIN, 16+i/2));

			DrawMethod.drawString("タイムは", 10, 15+i-70 , i/2 , g2);

			DrawMethod.drawString((WorkGui.framespeed* WorkGui.pertime)/1000 + "second　です!", 10, 15+i , 16+i/2 , g2);

			DrawMethod.drawString("また遊んでね！", 10+15, 15+i+70 , i/2 , g2);

		}

		if(count >= WorkGui.h) {
			WorkGui.timer.stop();
			WorkGui.frame.setVisible(false);
//			Menu.frame.setVisible(true);
			WorkGui.boo = false ;

			String text = "gameover ";
			DatagramPacket sendPacket=new DatagramPacket(text.getBytes(),text.getBytes().length,new InetSocketAddress(Receive_S.RehostIP, 10009));//Datagram_Send_S.address_Port
			try {
				Send_S.socket_Client_10009.send(sendPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			i = 0;
		}
		
	}
}
