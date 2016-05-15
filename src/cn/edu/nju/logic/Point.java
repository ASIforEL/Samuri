package cn.edu.nju.logic;

public class Point {

	private int x;
	private int y;
	
	public Point(int info1, int info2) {
		this.x = info1;
		this.y = info2;
	}
	
	//getters & setters
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX(int info) {
		this.x = info;
	}
	
	public void setY(int info) {
		this.y = info;
	}
	
}
