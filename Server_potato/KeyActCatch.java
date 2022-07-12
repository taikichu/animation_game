package Server_potato;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class KeyActCatch implements KeyListener{
	public KeyActCatch(JFrame frame) {
		frame.addKeyListener(this);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		BattleGui.actFlag2++;
		System.out.println("押されました:"+ (BattleGui.actFlag=String.valueOf(e.getKeyChar())));
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
}
