package Maze;

import java.util.Random;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class Maze {
	boolean[][] Data = null;
	int w = 0;
	int h = 0;
	Point StartP = null;
	Point EndP = null;
	Stack<Point> SolvePath;
	
	public Maze(){
		w = 0;
		h = 0;
		Data = null;
		StartP = null;
		EndP = null;
		SolvePath = null;
	}
	
	public Maze(int ww,int hh,int per){
		w = ww;
		h = hh;
		Data = new boolean[h][w];
		StartP = null;
		EndP = null;
		SolvePath = null;
		Random RandomSeed = new Random();
		for (int i = 0;i < hh;i ++){
			for (int j = 0;j < ww;j ++){
				Data[i][j] = RandomSeed.nextInt(100) >= per;
			}
		}
	}
	
	public int GetWidth(){
		return w;
	}
	
	public int GetHeight(){
		return h;
	}
	
	public void SetStartPoint(Point t){
		StartP = t;
	}
	
	public void SetEndPoint(Point t){
		EndP = t;
	}
	
	public Point GetStartPoint(){
		return StartP;
	}
	
	public Point GetEndPoint(){
		return EndP;
	}
	
	public boolean GetMazeData(int i,int j){
		return Data[i][j];
	}
	
	public Stack<Point> GetShortestPath(){
		int dir[][]={{-1,0},{0,1},{1,0},{0,-1}};
		boolean Visited[][] = new boolean[h][w];
		for (int i = 0;i < h;i ++){
			for (int j = 0;j < w;j ++){
				Visited[i][j] = false;
			}
		}
		Stack<Point> TempPath = new Stack<Point>();
		Queue<StackNode> MyQueue = new LinkedList<StackNode>();
		MyQueue.offer(new StackNode(null,StartP,0));
		Visited[StartP.y][StartP.x] = true;
		StackNode TempNode;
		while ((TempNode = MyQueue.poll()) != null){
			//System.out.println(TempNode.NowPoint.x + "," + TempNode.NowPoint.y);
			if (TempNode.NowPoint.equals(EndP)){
				//Find The End Point
				//Save The Path
				TempNode = TempNode.PreviousPoint;
				while (TempNode.PreviousPoint != null){
					TempPath.push(TempNode.NowPoint);
					TempNode = TempNode.PreviousPoint;
				}
				SolvePath = TempPath;
				return TempPath;
			}
			else{
				for(int i=0;i<4;i++)
				{
					Point cur = new Point(TempNode.NowPoint.x,TempNode.NowPoint.y);
					cur.x += dir[i][0];
					cur.y += dir[i][1];
					//System.out.println("udlr" + cur.x + "," + cur.y);
					if (cur.x >= 0 && cur.x < w && cur.y >= 0 && cur.y < h){
						if (!Visited[cur.y][cur.x]){
							if (!Data[cur.y][cur.x]){
								MyQueue.offer(new StackNode(TempNode,cur,TempNode.Steps + 1));
								Visited[cur.y][cur.x] = true;
							}
						}
						
					}
				}
			}
		}
		SolvePath = null;
		return null;
	}
	
	public Stack<Point> GetSolvePath(){
		return SolvePath;
	}
}

class StackNode{
	StackNode PreviousPoint;
	Point NowPoint;
	int Steps;
	StackNode(StackNode p,Point pp,int s){
		PreviousPoint = p;
		NowPoint = pp;
		Steps = s;
	}
}
