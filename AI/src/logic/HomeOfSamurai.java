package logic;

public class HomeOfSamurai {
	Map map = new Map();
    private int xOfHome;
    private int yOfHome;
    
    public static final int hero1OfCap =22;
    public static final int hero2OfCap =33;
    public static final int hero3OfCap =44;
    public static final int hero1OfIronMan =55;
    public static final int hero2OfIronMan = 66;
    public static final int hero3OfIronMan =77;
    
    public int getXOfHome(){
    	return this.xOfHome;
    }
    
    public int getYOfHome(){
    	return this.yOfHome;
    }
    
    public void setXOfHome(int info){
    	this.xOfHome=info;
    }
    
    public void setYOfHome(int info){
    	this.yOfHome=info;
    }
    
    public boolean isHero1OfCapHome(int x,int y){
    	if(map.getValueOfBlock(x, y)==HomeOfSamurai.hero1OfCap)
    		return true;
    	else 
    		return false;
    }
    
    public boolean isHero2OfCapHome(int x,int y){
    	if(map.getValueOfBlock(x, y)==HomeOfSamurai.hero2OfCap)
    		return true;
    	else
    		return false;
    }
    
    public boolean isHero3OfCapHome(int x,int y){
    	if(map.getValueOfBlock(x, y)==HomeOfSamurai.hero3OfCap)
    		return true;
    	else
    		return false;
    }
    
    public boolean isHero1OfIronManHome(int x,int y){
    	if(map.getValueOfBlock(x, y)==HomeOfSamurai.hero1OfIronMan)
    		return true;
    	else
    		return false;
    }
    
    public boolean isHero2OfIronManHome(int x,int y){
    	if(map.getValueOfBlock(x, y)==HomeOfSamurai.hero2OfIronMan)
    		return true;
    	else
    		return false;
    }
    
    public boolean isHero3OfIronManHome(int x,int y){
    	if(map.getValueOfBlock(x, y)==HomeOfSamurai.hero3OfIronMan)
    		return true;
    	else
    		return false;
    }
    
    //set the home of hero
    public void setHome(int x,int y,String heroNumber,String teamName){
    	if(teamName.equals("Cap")){
    		switch(heroNumber){
    		case "hero1":
    			if(map.getValueOfBlock(x, y)==Map.valueOfInitialize)
    	    		map.getBlock(x, y).setValue(HomeOfSamurai.hero1OfCap);
    			break;
    			
    		case "hero2":
    			if(map.getValueOfBlock(x, y)==Map.valueOfInitialize)
    				map.getBlock(x, y).setValue(HomeOfSamurai.hero2OfCap);
    			break;
    			
    		case "hero3":
    			if(map.getValueOfBlock(x, y)==Map.valueOfInitialize)
    				map.getBlock(x, y).setValue(HomeOfSamurai.hero3OfCap);
    			break;
    		}
    	}else if(teamName.equals("IronMan")){
    		switch(heroNumber){
    		case "hero1":
    			if(map.getValueOfBlock(x, y)==Map.valueOfInitialize)
    				map.getBlock(x, y).setValue(HomeOfSamurai.hero1OfIronMan);
    			break;
    			
    		case "hero2":
    			if(map.getValueOfBlock(x, y)==Map.valueOfInitialize)
    				map.getBlock(x, y).setValue(HomeOfSamurai.hero2OfIronMan);
    			break;
    			
    		case "hero3":
    			if(map.getValueOfBlock(x, y)==Map.valueOfInitialize)
    				map.getBlock(x, y).setValue(HomeOfSamurai.hero3OfIronMan);
    			break;
    		}
    	}
    }
}
