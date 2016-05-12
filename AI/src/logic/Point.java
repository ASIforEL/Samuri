package logic;

public class Point {

	private double x;
	private double y;
	
	//getters & setters
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public void setX(double info) {
		this.x = info;
	}
	
	public void setY(double info) {
		this.y = info;
	}

	public void setPoint(double info1, double info2) {
		this.x = info1;
		this.y = info2;
	}
	
}
