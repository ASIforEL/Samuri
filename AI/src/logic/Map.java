package logic;

public class Map {
	public static final int valueOfInitialize=7;
	private int width;
	private int height;
	private Block[][] map = new Block[height][width];//heightΪ���򷽿��������widthΪ���򷽿������?
	private Block interior = new Block();
	//initialize the map in order to make every value of point equal 7����������г�ʼ����δ��ռ�������Ϊ��ʼ����ֵ��
	public void initializeMap(){
		for(int i=0;i<this.height;i++){
			for(int j=0;j<this.width;j++){
				this.map[i][j].setValue(Map.valueOfInitialize);
			}
		}
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	  
	public void setWidth(int info){
		this.width=info;
	}
	
	public void setHeight(int info){
		this.height=info; 
	}
	
	public Block getBlock(double x, double y) {
		int tempX = (int)(x/interior.getHeightOfBlock());
		int tempY = (int)(x/interior.getWidthOfBlock());
		return this.map[tempX][tempY];
	}
	
	public int getValueOfBlock(double x, double y){
		int tempX = (int)(x/interior.getHeightOfBlock());
		int tempY = (int)(x/interior.getWidthOfBlock());
		return this.map[tempX][tempY].getValue();
	}
  
	
}