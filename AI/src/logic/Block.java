package logic;

public class Block {
	private double heightOfBlock;
	private double widthOfBlock;
//	Point block = new Point();			//pointָ�����ڵ�ͼ�е���������
    private int value;
    Manor blockManor = new Manor();			//��������side
    
    public double getHeightOfBlock(){
    	return this.heightOfBlock;
    }
    
    public void setHeightOfBlock(double info){
    	this.heightOfBlock=info;
    }
    
    public double getWidthOfBlock(){
    	return this.widthOfBlock;
    }
    
    public void setWidthOfBlock(double info){
    	this.widthOfBlock=info;
    }
    
    public int getValue() {
		return this.value;
	}
	
	public void setValue(int info){
		this.value = info;
	}
    
	//����Cap or IronMan  �����ϰ��ʹ����������������������?
	public void setManorOfCap(){
		if((this.getValue()==Manor.TeamIronMan||this.getValue()==Map.valueOfInitialize) && 
				(this.getValue() != Barrier.valueOfBarrier && this.getValue() != GateWay.valueOfGateWay))
			this.setValue(Manor.TeamCap);
	}
	     
	public void setManorOfIronMan(int x,int y){
		if((this.getValue()==Manor.TeamCap||this.getValue()==Map.valueOfInitialize) && 
				(this.getValue() != Barrier.valueOfBarrier && this.getValue() != GateWay.valueOfGateWay))
			this.setValue(Manor.TeamIronMan);
	}	
	
	//identify the side of Cap or IronMan
	public boolean isManorOfCap(int x,int y){
		if(this.getValue()==Manor.TeamCap){
			return true;
		}
		else{
			return false;  	
		}	
	}

	public boolean isManorOfIronMan(int x,int y){
		if(this.getValue()==Manor.TeamIronMan){
			return true;
		}
		else{
			return false;
		}
	}
}
