package Maze;

public class Point {
	int x;
	int y;
	
	public Point(){
		x = 0;
		y = 0;
	}
	
	public Point(int xx,int yy){
		x = xx;
		y = yy;
	}
	
	public int getX(){
		return x;
	}
	
	public boolean equals(Object p){
		if (x == ((Point)p).getX() && y == ((Point)p).getY()){
			return true;
		}
		else{
			return false;
		}
	}

	public int getY(){
		return y;
	}
}
