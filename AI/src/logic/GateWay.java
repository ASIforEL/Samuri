package logic;

public class GateWay extends Block {
	public static final int valueOfGateWay =10;
	
	
	//set gateway in map
	public void setGateway(int x,int y){
		if(this.getValue()==Map.valueOfInitialize){
			this.setValue(GateWay.valueOfGateWay);
		} 
	}
	
	//judge the point is GateWay
	public boolean isGateWay(int x,int y){
		if(this.getValue()==GateWay.valueOfGateWay)
			   return true;
		else
			return false; 
	}
}
