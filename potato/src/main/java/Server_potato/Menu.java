package Server_potato;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Menu {
	Menu_Potato M_Potato;
//	Potato_1 potato_1;
	static JFrame frame;
	static Threads_ thr_GUI;
	JButton buttonRun;
	JButton buttonOp;
	Graphics2D g2;
	int h=550;
	int w=550;

	public Menu() {
//		potato_1 = StockInstance;
		
		frame = new JFrame("pane");
		frame.setSize(w,h);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		M_Potato=new Menu_Potato();
		M_Potato.setLayout(null);
		
		frame.add(M_Potato);
		
		frame.setResizable(false);
		
	    frame.setVisible(true);
	}
	public void addComponent() {
		
			buttonRun = new JButton(new Run_button());//paint→コンポーネントの順で生成?
			buttonRun.setText("走る");
			buttonRun.setBounds(w/6,(h*2)/3,w/2,h/5);
			
			buttonOp = new JButton(new Option_button(frame));
			buttonOp.setText("option");
			buttonOp.setBounds((w*3)/4,(h*2)/3,w/5,h/5);
			
			M_Potato.add(buttonOp);
			M_Potato.add(buttonRun);
	}
	class Run_button extends AbstractAction{
		Run_button(){
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			new WorkGui();
		}

		
	}
	class Menu_Potato extends JPanel{
		
		static int potato_b_x = 230;
		static int potato_b_y = 180;
		private int potato_b_h;
		private int potato_b_w;
		private int potato_b_ah;
		private int potato_b_aw;
		
		public Menu_Potato() {
		}
		public void paint(Graphics g) {//setVisible(true)時に実行される
			g2=(Graphics2D)g;
			
			g2.setColor(Color.black);
			DrawMethod.drawRect( w/6 , (h*2)/3 , w/2 , h/5 , g2 );
			DrawMethod.drawRect( (w*3)/4 , (h*2)/3 , w/5 , h/5 , g2 );


			potato_b_w=Potato_1.body_w_L;
			potato_b_h=Potato_1.body_h_L;
			potato_b_aw=Potato_1.body_aw_L;
			potato_b_ah=Potato_1.body_ah_L;
			
			 RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
			 
		        rh.put(RenderingHints.KEY_RENDERING,
		                RenderingHints.VALUE_RENDER_QUALITY);
		        g2.setRenderingHints(rh);
		        
		        full(g2);
		        
		        new Threads_("menu2").start();
		        frame.setVisible(true);

				System.out.println("server:Menu:paint");
		}
		
		//横たわる芋を描画
		public void full(Graphics g) {
			
			makearmL(g2);
			makebody(g2);
			makearmR(g2);
			makeeye(g2);
			
		}
		
		
		public void makeeye(Graphics g) {
			g.setColor(Color.black);
			DrawMethod.drawArc( potato_b_x , potato_b_y*9/10 , potato_b_w , potato_b_h , 0 , 15 , g );
		}
		public void makebody(Graphics g) {
			g.setColor(Potato_1.body_color);
			DrawMethod.fillRoundRect( potato_b_x , potato_b_y , potato_b_h , potato_b_w , potato_b_ah , potato_b_aw , g );
		}
		public void makearmR(Graphics g) {
			g.setColor(Color.black);
			DrawMethod.drawLine( potato_b_x+(potato_b_h)/10*6 , potato_b_y+(potato_b_w*2)/3 , potato_b_x-10 , potato_b_y+potato_b_w , g);
			g.setColor(Color.white);
			DrawMethod.fillArc( potato_b_x-10-10/2 , potato_b_y+potato_b_w-10/2 , 10 , 10 , 0 , 360 , g );
			g.setColor(Color.black);
			DrawMethod.drawArc( potato_b_x-10-10/2 , potato_b_y+potato_b_w-10/2 , 10 , 10 , 0 , 360 , g );
		}
		public void makearmL(Graphics g) {
			g.setColor(Color.black);
			DrawMethod.drawLine( potato_b_x+(potato_b_h)/10*4 , potato_b_y+potato_b_w/3 , potato_b_x-10 ,  potato_b_y+potato_b_w*2/3 , g );
			g.setColor(Color.white);
			DrawMethod.fillArc( potato_b_x-10-10/2 , potato_b_y+potato_b_w*2/3-10/2 , 10 , 10 , 0 , 360 , g );
			g.setColor(Color.black);
			DrawMethod.drawArc( potato_b_x-10-10/2 , potato_b_y+potato_b_w*2/3-10/2 , 10 , 10 , 0 , 360 , g );
		}
	}
}
