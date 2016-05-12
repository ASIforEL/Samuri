package logic;

public class Game implements Runnable{
	public static final int PLAYER_NUM = 6;			//武士总数
	public static final int turns = 1008;			//回合总数
	public static final int maxCure = 24;			//恢复周期
	public volatile static int samuraiNum = 0;		//此时运行到的武士的编�?
	public int side;								//队伍ID�?0为TeamCap�?1为TeamIronMan�?
	public int weapon;								//武士ID，用武器标识
	public int width, height;						//战场大小
	public Samurai[] samurais;						//武士�?
	public int action;								//行动指令
	
	
	public Game(int i) {
		samuraiNum++;
		switch (i) {
		case 1:			//战队模式
			samurais[0] = new CaptainAmerica();
			samurais[1] = new Hulk();
			samurais[2] = new Hawkeye();
			
			samurais[3] = new IronMan();
			samurais[4] = new Thor();		//或�?�使用黑寡妇
			samurais[5] = new SpiderMan();
			break;

		case 2:			//自�?�模式，加入监听自己选择
			
		}
	}
	
	
	
	/**
	 * check whether there is a side having been the winner
	 * @return
	 */
	public boolean cheakWin() {
		int numOfDeadCap = 0;
		int numOfDeadIronMan = 0;
		for (int i = 0; i < samurais.length; i++) {
			if (samurais[i].isKilled()) {
				if (samurais[i].getSide() == 0) {
					numOfDeadCap++;
				}
				if (samurais[i].getSide() == 1) {
					numOfDeadIronMan++;
				}
			}
		}
		if ((numOfDeadCap == PLAYER_NUM/2 && numOfDeadIronMan < PLAYER_NUM/2) ||
				(numOfDeadIronMan == PLAYER_NUM/2 && numOfDeadCap < PLAYER_NUM/2)){
			return true;
		}
		return false;
	}
	
	/**
	 * get the winning side
	 * @return
	 */
	public int winSide() {
		int numOfDeadCap = 0;
		int numOfDeadIronMan = 0;
		for (int i = 0; i < samurais.length; i++) {
			if (samurais[i].isKilled()) {
				if (samurais[i].getSide() == 0) {
					numOfDeadCap++;
				}
				if (samurais[i].getSide() == 1) {
					numOfDeadIronMan++;
				}
			}
		}
		if ((numOfDeadCap == PLAYER_NUM/2 && numOfDeadIronMan < PLAYER_NUM/2)){
			return 1;
		}
		if ((numOfDeadIronMan == PLAYER_NUM/2 && numOfDeadCap < PLAYER_NUM/2)) {
			return 0;
		}
		return -1;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = samuraiNum % 6 - 1;			//武士编号
		samurais[i].doAction(this.action);
		
	}
}
