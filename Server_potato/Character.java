package Server_potato;

import java.util.ArrayList;


public abstract class Character {
	static final String LEFT = "LEFT";
	static final String RIGHT = "RIGHT";
	static final String FRONT = "FRONT";
	static final String BACK = "BACK";

	Double_parts getDoubleClass(String name){
		return new Double_parts(name);
	}
	Single_parts getSingleClass(String name){
			return new Single_parts(name);
		}

	/**
	 * 左右が存在する部位はDouble_partsで定義
	 * */
	class Double_parts{
		String name;
		ArrayList<Integer> l_partsList_x_L , l_partsList_y_L , r_partsList_x_L , r_partsList_y_L ;
		ArrayList<Integer> l_partsList_x_R , l_partsList_y_R , r_partsList_x_R , r_partsList_y_R ;
		ArrayList<Integer> l_partsList_x_F , l_partsList_y_F , r_partsList_x_F , r_partsList_y_F ;
		ArrayList<Integer> l_partsList_x_B , l_partsList_y_B , r_partsList_x_B , r_partsList_y_B ;
		Double_parts(String name){
			this.name=name;
		}
		/**list_size("x" もしくは "y" , "L" もしくは "R" , 前後左右のどこからの姿か(LEFT,RIGHT,FRONT,BACK))*/
		int list_size(String xy , String lr , String figure) {
			if(lr.equals("L")) {
				if(xy.equals("x")) {
					if(figure.equals(LEFT)) {
						return l_partsList_x_L.size();
					}else if(figure.equals(RIGHT)) {
						return l_partsList_x_R.size();
					}else if(figure.equals(FRONT)) {
						return l_partsList_x_F.size();
					}else if(figure.equals(BACK)) {
						return l_partsList_x_B.size();
					}
				}
				else if(xy.equals("y")) {
					if(figure.equals(LEFT)) {
						return l_partsList_y_L.size();
					}else if(figure.equals(RIGHT)) {
						return l_partsList_y_R.size();
					}else if(figure.equals(FRONT)) {
						return l_partsList_y_F.size();
					}else if(figure.equals(BACK)) {
						return l_partsList_y_B.size();
					}
				}
			}else if(lr.equals("R")) {
					if(xy.equals("x")) {
						if(figure.equals(LEFT)) {
							return r_partsList_x_L.size();
						}else if(figure.equals(RIGHT)) {
							return r_partsList_x_R.size();
						}else if(figure.equals(FRONT)) {
							return r_partsList_x_F.size();
						}else if(figure.equals(BACK)) {
							return r_partsList_x_B.size();
						}
					}
					else if(xy.equals("y")) {
						if(figure.equals(LEFT)) {
							return r_partsList_y_L.size();
						}else if(figure.equals(RIGHT)) {
							return r_partsList_y_R.size();
						}else if(figure.equals(FRONT)) {
							return r_partsList_y_F.size();
						}else if(figure.equals(BACK)) {
							return r_partsList_y_B.size();
						}
				}
			}
			return -1 ;
		}
		/**
		 * addParts( 左のx座標 , 左のy座標 , 右のx座標 , 右のy座標 , 前後左右のどこからの姿か) 
		 * 部位の左側の座標、、右側の座標を見られる方向で定義
		 * */
		void addParts( int xL , int yL , int xR , int yR , String figure) {//ある方向figureからの左目x,y、右目xyを定義
			if(figure.equals(LEFT)) {
				if(l_partsList_x_L==null||l_partsList_x_L.size()==0) {
					l_partsList_x_L = new ArrayList<Integer>();
				}
				if(l_partsList_y_L==null||l_partsList_y_L.size()==0) {
					l_partsList_y_L = new ArrayList<Integer>();
				}
				if(r_partsList_x_L==null||r_partsList_x_L.size()==0) {
					r_partsList_x_L = new ArrayList<Integer>();
				}
				if(r_partsList_y_L==null||r_partsList_y_L.size()==0) {
					r_partsList_y_L = new ArrayList<Integer>();
				}
				l_partsList_x_L.add(xL);
				l_partsList_y_L.add(yL);
				r_partsList_x_L.add(xR);
				r_partsList_y_L.add(yR);
			}else if(figure.equals(RIGHT)) {
				if(l_partsList_x_R==null||l_partsList_x_R.size()==0) {
					l_partsList_x_R = new ArrayList<Integer>();
				}
				if(l_partsList_y_R==null||l_partsList_y_R.size()==0) {
					l_partsList_y_R = new ArrayList<Integer>();
				}
				if(r_partsList_x_R==null||r_partsList_x_R.size()==0) {
					r_partsList_x_R = new ArrayList<Integer>();
				}
				if(r_partsList_y_R==null||r_partsList_y_R.size()==0) {
					r_partsList_y_R = new ArrayList<Integer>();
				}
				l_partsList_x_R.add(xL);
				l_partsList_y_R.add(yL);
				r_partsList_x_R.add(xR);
				r_partsList_y_R.add(yR);
			}else if(figure.equals(FRONT)) {
				if(l_partsList_x_F==null||l_partsList_x_F.size()==0) {
					l_partsList_x_F = new ArrayList<Integer>();
				}
				if(l_partsList_y_F==null||l_partsList_y_F.size()==0) {
					l_partsList_y_F = new ArrayList<Integer>();
				}
				if(r_partsList_x_F==null||r_partsList_x_F.size()==0) {
					r_partsList_x_F = new ArrayList<Integer>();
				}
				if(r_partsList_y_F==null||r_partsList_y_F.size()==0) {
					r_partsList_y_F = new ArrayList<Integer>();
				}
				l_partsList_x_F.add(xL);
				l_partsList_y_F.add(yL);
				r_partsList_x_F.add(xR);
				r_partsList_y_F.add(yR);
			}else if(figure.equals(BACK)) {
				if(l_partsList_x_B==null||l_partsList_x_B.size()==0) {
					l_partsList_x_B = new ArrayList<Integer>();
				}
				if(l_partsList_y_B==null||l_partsList_y_B.size()==0) {
					l_partsList_y_B = new ArrayList<Integer>();
				}
				if(r_partsList_x_B==null||r_partsList_x_B.size()==0) {
					r_partsList_x_B = new ArrayList<Integer>();
				}
				if(r_partsList_y_B==null||r_partsList_y_B.size()==0) {
					r_partsList_y_B = new ArrayList<Integer>();
				}
				l_partsList_x_B.add(xL);
				l_partsList_y_B.add(yL);
				r_partsList_x_B.add(xR);
				r_partsList_y_B.add(yR);
			}
		}
		/**
		 * setParts( 左のx座標 , 左のy座標 , 右のx座標 , 右のy座標 , Listの何番目か , 前後左右のどこからの姿か) 
		 * addした要素の座標をListの位置を指定して変更できる。
		 * 最初の要素はListの0番目
		 * */
		void setParts( int xL , int yL , int xR , int yR , int position , String figure) {//配列position番目の情報を設定
			if(figure.equals(LEFT)) {
				l_partsList_x_L.set(position,xL);
				l_partsList_y_L.set(position,yL);
				r_partsList_x_L.set(position,xR);
				r_partsList_y_L.set(position,yR);
			}else if(figure.equals(RIGHT)) {
				l_partsList_x_R.set(position,xL);
				l_partsList_y_R.set(position,yL);
				r_partsList_x_R.set(position,xR);
				r_partsList_y_R.set(position,yR);
				
			}else if(figure.equals(FRONT)) {
				l_partsList_x_F.set(position,xL);
				l_partsList_y_F.set(position,yL);
				r_partsList_x_F.set(position,xR);
				r_partsList_y_F.set(position,yR);
			}else if(figure.equals(BACK)) {
				l_partsList_x_B.set(position,xL);
				l_partsList_y_B.set(position,yL);
				r_partsList_x_B.set(position,xR);
				r_partsList_y_B.set(position,yR);
			}
		}
		/**
		 * getPartsL( 左のx座標かy座標 , Listの何番目か , 前後左右のどこからの姿か) 
		 * 左のxかyの座標を取り出す
		 * 最初の要素はListの0番目
		 * 返り値はint型
		 * */
		int getPartsL(String xy , int position , String figure) {
			if(figure.equals(LEFT)) {
				if(xy.equals("x")) {
					return l_partsList_x_L.get(position);
				}else if(xy.equals("y")){
					return l_partsList_y_L.get(position);
				}
			}else if(figure.equals(RIGHT)) {
				if(xy.equals("x")) {
					return l_partsList_x_R.get(position);
				}else if(xy.equals("y")){
					return l_partsList_y_R.get(position);
				}
			}else if(figure.equals(FRONT)) {
				if(xy.equals("x")) {
					return l_partsList_x_F.get(position);
				}else if(xy.equals("y")){
					return l_partsList_y_F.get(position);
				}
			}else if(figure.equals(BACK)) {
				if(xy.equals("x")) {
					return l_partsList_x_B.get(position);
				}else if(xy.equals("y")){
					return l_partsList_y_B.get(position);
				}
			}
			else System.out.println("error");
			return 0;
		}
		/**
		 * getPartsR( 右のx座標かy座標 , Listの何番目か , 前後左右のどこからの姿か) 
		 * 右のxかyの座標を取り出す
		 * 最初の要素はListの0番目
		 * 返り値はint型
		 * */
		int getPartsR(String xy , int position , String figure) {
			if(figure.equals(LEFT)) {
				if(xy.equals("x")) {
					return r_partsList_x_L.get(position);
				}else if(xy.equals("y")){
					return r_partsList_y_L.get(position);
				}
			}else if(figure.equals(RIGHT)) {
				if(xy.equals("x")) {
					return r_partsList_x_R.get(position);
				}else if(xy.equals("y")){
					return r_partsList_y_R.get(position);
				}
			}else if(figure.equals(FRONT)) {
				if(xy.equals("x")) {
					return r_partsList_x_F.get(position);
				}else if(xy.equals("y")){
					return r_partsList_y_F.get(position);
				}
			}else if(figure.equals(BACK)) {
				if(xy.equals("x")) {
					return r_partsList_x_B.get(position);
				}else if(xy.equals("y")){
					return r_partsList_y_B.get(position);
				}
			}
			else System.out.println("error");
			return 0;
		}
	}
	/**
	 * 左右が存在しない部位はSingle_partsで定義
	 * */
	class Single_parts{
			String name;
			ArrayList<Integer> partsList_x_L , partsList_y_L ;
			ArrayList<Integer> partsList_x_R , partsList_y_R ;
			ArrayList<Integer> partsList_x_F , partsList_y_F ;
			ArrayList<Integer> partsList_x_B , partsList_y_B ;
			Single_parts(String name){
				this.name=name;
			}
			/**list_size("x" もしくは "y" , 前後左右のどこからの姿か(LEFT,RIGHT,FRONT,BACK))*/
			int list_size(String xy , String figure) {
				if(xy.equals("x")) {
					if(figure.equals(LEFT)) {
						return partsList_x_L.size();
					}else if(figure.equals(RIGHT)) {
						return partsList_x_R.size();
					}else if(figure.equals(FRONT)) {
						return partsList_x_F.size();
					}else if(figure.equals(BACK)) {
						return partsList_x_B.size();
					}
				}
				else if(xy.equals("y")) {
					if(figure.equals(LEFT)) {
						return partsList_y_L.size();
					}else if(figure.equals(RIGHT)) {
						return partsList_y_R.size();
					}else if(figure.equals(FRONT)) {
						return partsList_y_F.size();
					}else if(figure.equals(BACK)) {
						return partsList_y_B.size();
					}
				}
				return -1 ;
			}
			/**
			 * addParts( x座標 , y座標 , 前後左右のどこからの姿か) 
			 * 部位の座標を見られる方向で定義
			 * */
			void addparts( int x , int y , String figure) {//ある方向figureからの左目x,y、右目xyを定義
				if(figure.equals(LEFT)) {
					if(partsList_x_L==null||partsList_x_L.size()==0) {
						partsList_x_L = new ArrayList<Integer>();
					}
					if(partsList_y_L==null||partsList_y_L.size()==0) {
						partsList_y_L = new ArrayList<Integer>();
					}
					partsList_x_L.add(x);
					partsList_y_L.add(y);
				}else if(figure.equals(RIGHT)) {
					if(partsList_x_R==null||partsList_x_R.size()==0) {
						partsList_x_R = new ArrayList<Integer>();
					}
					if(partsList_y_R==null||partsList_y_R.size()==0) {
						partsList_y_R = new ArrayList<Integer>();
					}
					partsList_x_R.add(x);
					partsList_y_R.add(y);
				}else if(figure.equals(FRONT)) {
					if(partsList_x_F==null||partsList_x_F.size()==0) {
						partsList_x_F = new ArrayList<Integer>();
					}
					if(partsList_y_F==null||partsList_y_F.size()==0) {
						partsList_y_F = new ArrayList<Integer>();
					}
					partsList_x_F.add(x);
					partsList_y_F.add(y);
				}else if(figure.equals(BACK)) {
					if(partsList_x_B==null||partsList_x_B.size()==0) {
						partsList_x_B = new ArrayList<Integer>();
					}
					if(partsList_y_B==null||partsList_y_B.size()==0) {
						partsList_y_B = new ArrayList<Integer>();
					}
					partsList_x_B.add(x);
					partsList_y_B.add(y);
				}
			}
			/**
			 * setParts( x座標 , y座標 , Listの何番目か , 前後左右のどこからの姿か) 
			 * addした要素の座標をListの位置を指定して変更できる。
			 * 最初の要素はListの0番目
			 * */
			void setparts( int x , int y , int position , String figure) {//配列position番目の情報を設定
				if(figure.equals(LEFT)) {
					partsList_x_L.set(position,x);
					partsList_y_L.set(position,y);
				}else if(figure.equals(RIGHT)) {
					
					partsList_x_R.set(position,x);
					partsList_y_R.set(position,y);
					
				}else if(figure.equals(FRONT)) {
					partsList_x_F.set(position,x);
					partsList_y_F.set(position,y);
				}else if(figure.equals(BACK)) {
					partsList_x_B.set(position,x);
					partsList_y_B.set(position,y);
				}
			}
			/**
			 * getPartsL( x座標かy座標 , Listの何番目か , 前後左右のどこからの姿か) 
			 * xかyの座標を取り出す
			 * 最初の要素はListの0番目
			 * 返り値はint型
			 * */
			int getparts(String xy , int position , String figure) {
				if(figure.equals(LEFT)) {
					if(xy.equals("x")) {
						return partsList_x_L.get(position);
					}else if(xy.equals("y")){
						return partsList_y_L.get(position);
					}
				}else if(figure.equals(RIGHT)) {
					if(xy.equals("x")) {
						return partsList_x_R.get(position);
					}else if(xy.equals("y")){
						return partsList_y_R.get(position);
					}
				}else if(figure.equals(FRONT)) {
					if(xy.equals("x")) {
						return partsList_x_F.get(position);
					}else if(xy.equals("y")){
						return partsList_y_F.get(position);
					}
				}else if(figure.equals(BACK)) {
					if(xy.equals("x")) {
						return partsList_x_B.get(position);
					}else if(xy.equals("y")){
						return partsList_y_B.get(position);
					}
				}
				else System.out.println("error");
				return 0;
			}
		}
}
