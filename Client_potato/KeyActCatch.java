package Client_potato;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.DatagramPacket;

import javax.swing.JFrame;

public class KeyActCatch implements KeyListener{
	String word;
	public KeyActCatch(JFrame frame) {
		frame.addKeyListener(this);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("押されました:Client:"+ (word = ("keyTyped "+String.valueOf(e.getKeyChar()))));

		DatagramPacket sendPacket=new DatagramPacket(word.getBytes(),word.getBytes().length, Datagram_Send_C.sendAddress);//Datagram_Send_S.address_Port
		try {
			Datagram_Send_C.socket.send(sendPacket);
			System.out.println("keytypeを送りました");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
}
