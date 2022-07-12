package netpro.pokemon2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class KeyActCatch implements KeyListener{
//	BattleGui battleGui;
	public KeyActCatch(JFrame frame) {
		frame.addKeyListener(this);
//		battleGui = StockInstance.getBattleGuiInstance();
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
