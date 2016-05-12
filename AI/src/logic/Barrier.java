package logic;

public class Barrier extends Block{
    public static final int valueOfBarrier =17;
    
    
    //set barrier in map
    public void setBarrier(int x,int y){
  	  if(this.getValue()==Map.valueOfInitialize){
          this.setValue(Barrier.valueOfBarrier);
  	  }
    }
    
    //identify the point is Barrier or not
    public boolean isBarrier(int x, int y){
  	  if(this.getValue()==Barrier.valueOfBarrier)
  		  return true;
  	  else
  		  return false;
    }
    
}
