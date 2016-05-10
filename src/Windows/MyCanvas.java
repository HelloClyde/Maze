package Windows;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import Maze.Maze;
import Maze.Point;

public class MyCanvas extends Canvas{
	Maze map = null;
	int Size = 4;
	public void SendMaze(Maze m){
		map = m;
		if (m == null){
			return;
		}
		else{
			this.setSize(map.GetWidth() * Size, map.GetHeight() * Size);
		}
	}
	
	public void ChangeSize(int n){
		if (n >= 1)
			Size = n;
		else
			return;
	}
	
	public int GetSize(){
		return Size;
	}
		
	public void paint(Graphics g){
		if (map == null){
			return;
		}
		else{
			this.setSize(map.GetWidth() * Size, map.GetHeight() * Size);
			g.create(0, 0, map.GetWidth() * Size, map.GetHeight()*Size);
			for (int i = 0;i < map.GetHeight();i ++){
				for (int j = 0;j < map.GetWidth();j ++){
					if (map.GetMazeData(i, j)){
						g.setColor(new Color(0,0,0));
					}
					else{
						g.setColor(new Color(255,255,255));
					}
					g.fillRect(j * Size,i * Size, Size, Size);
				}
			}
			if (map.GetStartPoint() != null){
				//Draw StartPoint
				g.setColor(new Color(255,0,0));
				g.fillRect(map.GetStartPoint().getX() * Size, map.GetStartPoint().getY() * Size, Size, Size);
			}
			if (map.GetEndPoint() != null){
				//Draw EndPoint
				g.setColor(new Color(0,255,0));
				g.fillRect(map.GetEndPoint().getX() * Size, map.GetEndPoint().getY() * Size, Size, Size);
			}
			System.out.println(map.GetSolvePath());
			if (map.GetSolvePath() != null){
				g.setColor(new Color(0,0,255));
				while (!map.GetSolvePath().empty()){
					Point TempP = map.GetSolvePath().pop();
					//System.out.println("path:" + TempP.getX() + "," + TempP.getY());
					g.fillRect(TempP.getX() * Size, TempP.getY() * Size, Size, Size);
				}
			}
		}
		
	}
}
