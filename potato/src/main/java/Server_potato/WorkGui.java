package Server_potato;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;





public class WorkGui extends JPanel implements ActionListener{
	
//	static String field = "offline" ;// "online"
	static Color heart_color=Color.blue;
    protected static int color_count  ;
    
    static boolean boo = false ;
    
    static Timer timer;
    protected static String actFlag;//actionflagが建てられたとき
    protected static String actnow;
    protected static int actFlag2;//actFlag2//継続させるかどうか
    protected static int pertime;
    protected static int count,countF;
    protected Potato_1 per;
    protected static int JP=80;
    
    static int lifenum = 3 ;
    static String mode = "battle" ;
    
    boolean gameover_Instance = false;
	boolean isResetProcess = true;
	
	GameOver gameover;
	

	protected static int framespeed=40;//40の場合25F*2?

    private Random random;
    private final ArrayList<Waste> array=new ArrayList<Waste>();
    
    Graphics2D g2;
	static JFrame frame;
	
	static int w = 700 , h = 550 ;
	
	//ダメージを受ける時間
	static int countdownR;
	
	//コンストラクタ
	public WorkGui(){
		color_count = 0;
		actFlag="";
		actnow="";
		actFlag2=0;
		pertime=0;
		count=0;
		countF=0;
		countdownR=0;
		isResetProcess = true;
		boo = false ;
		Waste.stop = false ;
		
		
		frame=new JFrame("S_run");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new WorkGui(0));//timerの付与
        frame.setResizable(false);
        frame.setSize( w , h );
        frame.setLocationRelativeTo(null);
		//
        frame.setVisible(true);
	}
	public WorkGui(int num) {
		timer=new Timer(framespeed,this);
		timer.setInitialDelay(500);
        timer.start();
        new Threads_(frame,"key").start();
	}
	public WorkGui(int a, int b) {
		
	}
	//A//一度だけ呼び出される-----------------------------------------------------------------------------------------------new Potato_1();
	public void one(int w, int h) {
		
		per=new Potato_1( 0 , 0 );
		random=new Random();
    }
	//A//コンストラクタの後に実行される
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);


		if(DrawMethod.field.equals("online")){
			DatagramPacket sendPacket=new DatagramPacket("false ".getBytes(),"false ".getBytes().length,new InetSocketAddress(Receive_S.RehostIP, 10008));
			sendPacket.setPort(Send_S.SENDBACK_PORT);
			try {
				Send_S.socket_Client_10008.send(sendPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}



        g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2.setRenderingHints(rh);
        Dimension windowSize = getSize();//現在あるコンポーネントの状態を返す　//Dimensionはguiにあるように調整してくれる
        
//        System.out.println(pertime);


        if (isResetProcess) {
            one(windowSize.width, windowSize.height);
            isResetProcess = false;
        }
        
        

        

        if(mode.equals("battle")){
			if(boo==false) {
				//jump等のアクションはキャラ描画前、move()前に実行
				this.judge_actFlag();

				//時間をカウント 表示
				this.painttime(g2);
				this.move();

				per.fullset(g2);//キャラクターを描画
			}else {
				if(gameover_Instance == false ) {
					gameover = new GameOver();
					gameover_Instance = true ;
				}
				per.stopset(g2);//止まったキャラクターを描画
				Waste.stop = true ;
				gameover.draw(g);
			}
			//とげ生成  難易度C //画面に1個以上4個未満//5/1&&10/1の確立
			this.syogai();

			//ハートを描画
//        this.makeHeart(g2);
		}
		else if(mode.equals("option")){
			per.fullset(g2);//キャラクターを描画
		}
        
	}
	
	
	void makeHeart(Graphics2D g) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		int y0 = 0 , y180 = 0 ;
		double t0 = 0 ;double t180 = 0;
		double c = 1.5 ;//ハートの大きさ
		for(int i = 0 ; i < 180 ; i++ ) {
			t0= Math.toRadians(i);
			t180= Math.toRadians(i+180);//色を満たすため180度ずらす
			for(int j = 0 ; j <lifenum ; j++ ) {
				array.add((int) ((16 * Math.sin(t0) *Math.sin(t0) * Math.sin(t0))*c)+50+(w*(8-j))/10);
				array.add((int) ((16 * Math.sin(t180) *Math.sin(t180) * Math.sin(t180))*c)+50+(w*(8-j))/10);
				
			}
			y0=(int) -((13 * Math.cos(t0) - 5 * Math.cos(2 * t0) - 2 * Math.cos(3 * t0) - Math.cos(4 * t0))*c)+50;
			y180=(int) -((13 * Math.cos(t180) - 5 * Math.cos(2 * (t180)) - 2 * Math.cos(3 * (t180)) - Math.cos(4 * (t180)))*c)+50;
			if(color_count>0 && heart_color != null ) {//color_countが増えていたらその数だけハートを青くする
				g.setColor(heart_color);
				
				for(int j = 0 ; j < array.size()/2 ; j++ ) {
					
					if((j+1) <= color_count) {
						DrawMethod.drawLine(array.get(2*j), y0, array.get(2*j+1) , y180 , g);
//						new Threads_("drawLine",array.get(2*j), y0, array.get(2*j+1) , y180 , g).start();
						
					}else {
						g.setColor(new Color(255,20,30));
						for(int k = j ; k < array.size()/2 ; k++) {
							DrawMethod.drawLine(array.get(2*k), y0, array.get(2*k+1) , y180 , g);
//							new Threads_("drawLine",array.get(2*k), y0, array.get(2*k+1) , y180 , g).start();
						}
						break;
					}
				}
			}else {
				g.setColor(new Color(255,20,30));
				for(int j = 0 ; j < array.size()/2 ; j++) {
					DrawMethod.drawLine(array.get(2*j), y0, array.get(2*j+1) , y180 , g);
//					new Threads_("drawLine",array.get(2*j), y0, array.get(2*j+1) , y180 , g).start();
				}
			}
			array.clear();
		}
		if(lifenum<=color_count) {
			boo = true ; 
		}
	}
	
	void painttime(Graphics2D g2) {
		g2.setColor(Color.blue);
		DrawMethod.drawString((framespeed*pertime)/1000 + "second", 10, 15 ,16 , g2);
	}
	
	void judge_actFlag() {
		if(actFlag.equals("")==false) {
        	if(actnow.equals("")) {
        		actnow=actFlag;//jump_shortの場合　i
        	}
    		if(actnow.equals("i")) {//		i	はジャンプ
        		per.jump_short();
        	}else if(actnow.equals("o")) {//		i	はジャンプ
        		per.jump_high();
        	}else {
        		actnow="";
        	}
        }
	}
	
	//毎時間//paintを呼び出し直す//timer_this
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void syogai() {
        if(array.size()==0||(random.nextInt(5)==0&&array.size()<4&&(pertime%10)==0)) {
        	array.add(new Waste());
        }
        for(Waste waste : array) {//とげを格納//cランクのみ適用//増やしたら修正
        	 waste.setAndmove(g2);
        	 if( 
        			 (( per.getBX()<=waste.getBWC()+waste.getX()&&waste.getX()+waste.getBWC()<=per.getBX()+per.getBW() )
        					 ||
        			 ( per.getBX()<=waste.getX()&&waste.getX()<=per.getBX()+per.getBW() ))
        			 &&  
        			 (( per.getBY()<=waste.getY()&&waste.getY()<=per.getBY()+per.getBH() )
        					 ||
        			 ( per.getBY()<=waste.getY()+waste.getBHC()&&waste.getBHC()+waste.getY()<=per.getBY()+per.getBH() ))
        		)
        	 { 
        		 if(countdownR<=0) {//ダメージを受けた時に色を変える為の印
        			 color_count++;
					 System.out.println("color_count:"+color_count);
					 //color_count
					 String text = "color_count " + color_count;
					 DatagramPacket sendPacket=new DatagramPacket(text.getBytes(),text.getBytes().length,new InetSocketAddress(Receive_S.RehostIP,10009));//Datagram_Send_S.address_Port
					 try {
						 Send_S.socket_Client_10009.send(sendPacket);
					 } catch (IOException e) {
						 e.printStackTrace();
					 }
        		 }
        		 per.setdead();//ダメージを受けた時に体の色を青くする
        	 }
        }
	}
	
	

	public int getframespeed() {
		return framespeed;
	}
	public void setFlag(String flag) {
		actFlag=flag;
	}
	public JFrame getFrame() {
		return frame;
	}void move() {
		pertime++;
	}
	public void setactFlag(String flag) {
		actFlag=flag;
	}
	public void setactnow(String now) {
		actnow=now;
	}
	public void setcountF(int countF) {
		WorkGui.countF =countF;
	}
	public void setcount(int count) {
		WorkGui.count =count;
	}
	public void setJP(int JP) {
		WorkGui.JP =JP;
	}
	public void setactFlag2(int flag2) {
		actFlag2=flag2;
	}
	public int getactFlag2() {
		return actFlag2;
	}
	public int getcount() {
		return count;
	}
	public int getcountF() {
		return countF;
	}
	public int getJP() {
		return JP;
	}
}