package cn.edu.nju.logic;

public class Configure {
	public static final int WIDTH = 60;
	public static final int HEIGHT = 25;
	public static final int DEVIATION = 12;

	//of map[][] / mapfield[][][]
	public static final int ROAD=1;
	public static final int MOUN=2;
	public static final int TRANS1=31;
	public static final int TRANS2=32;
	public static final int TRANS3=33;
	public static final int TRANS4=34;
	public static final int TRANS5=35;
	public static final int TRANS6=36;
	public static final int TRANS7=37;
	public static final int TRANS8=38;
	public static final int TREE=4;
	public static final int HOME_OF_CAP=5;
	public static final int HOME_OF_IRON_MAN=6;
	
	//of samuraiField[][]
	public static final int CAPTAIN_AMERICA = 11;
	public static final int HULK = 22;
	public static final int HAWKEYE = 33;
	public static final int IRON_MAN = 44;
	public static final int BLACK_WIDOW = 55;
	public static final int SPIDER_MAN = 66;
	
	public static int CAPTAIN_AMERICA_WEST = 1101;
	public static int CAPTAIN_AMERICA_EAST = 1102;
	public static int CAPTAIN_AMERICA_NORTH = 1103;
	public static int CAPTAIN_AMERICA_SOUTH = 1104;
	public static int CAPTAIN_AMERICA_WEST_HIDE = 1111;
	public static int CAPTAIN_AMERICA_EAST_HIDE = 1112;
	public static int CAPTAIN_AMERICA_NORTH_HIDE = 1113;
	public static int CAPTAIN_AMERICA_SOUTH_HIDE = 1114;	
		
	public static int HULK_WEST = 2201;
	public static int HULK_EAST = 2202;
	public static int HULK_NORTH = 2203;
	public static int HULK_SOUTH = 2204;
	public static int HULK_WEST_HIDE = 2211;
	public static int HULK_EAST_HIDE = 2212;
	public static int HULK_NORTH_HIDE = 2213;
	public static int HULK_SOUTH_HIDE = 2214;

	public static int HAWKEYE_WEST = 3301;
	public static int HAWKEYE_EAST = 3302;
	public static int HAWKEYE_NORTH = 3303;
	public static int HAWKEYE_SOUTH = 3304;
	public static int HAWKEYE_WEST_HIDE = 3311;
	public static int HAWKEYE_EAST_HIDE = 3312;
	public static int HAWKEYE_NORTH_HIDE = 3313;
	public static int HAWKEYE_SOUTH_HIDE = 3314;
		
	public static int IRON_MAN_WEST = 4401;
	public static int IRON_MAN_EAST = 4402;
	public static int IRON_MAN_NORTH = 4403;
	public static int IRON_MAN_SOUTH = 4404;
	public static int IRON_MAN_WEST_HIDE = 4411;
	public static int IRON_MAN_EAST_HIDE = 4412;
	public static int IRON_MAN_NORTH_HIDE = 4413;
	public static int IRON_MAN_SOUTH_HIDE = 4414;

	public static int BLACK_WIDOW_WEST = 5501;
	public static int BLACK_WIDOW_EAST = 5502;
	public static int BLACK_WIDOW_NORTH = 5503;
	public static int BLACK_WIDOW_SOUTH = 5504;
	public static int BLACK_WIDOW_WEST_HIDE = 5511;
	public static int BLACK_WIDOW_EAST_HIDE = 5512;
	public static int BLACK_WIDOW_NORTH_HIDE = 5513;
	public static int BLACK_WIDOW_SOUTH_HIDE = 5514;	
		
	public static int SPIDER_MAN_WEST = 6601;
	public static int SPIDER_MAN_EAST = 6602;
	public static int SPIDER_MAN_NORTH = 6603;
	public static int SPIDER_MAN_SOUTH = 6604;
	public static int SPIDER_MAN_WEST_HIDE = 6611;
	public static int SPIDER_MAN_EAST_HIDE = 2212;
	public static int SPIDER_MAN_NORTH_HIDE = 6613;
	public static int SPIDER_MAN_SOUTH_HIDE = 6614;
	
	//of HitRangeField[][][]
	public static final int CAP_ATTACK = 1;
	public static final int HULK_ATTACK = 2;
	public static final int HAWKEYE_ATTACK = 3;
	public static final int IRON_MAN_ATTACK = 4;
	public static final int BLACK_WIDOW_ATTACK = 5;
	public static final int SPIDER_MAN_ATTACK = 6;
	
	//of scoreFielf[][]
	public static final int SIDE_OF_CAP = 1;
	public static final int SIDE_OF_IRON_MAN = 2;
	
	public static int[][] map = new int[12][12];
	public static int[][][] hitRangeField = new int[6][12][12];
	
	public static int[][] scoreField = new int[][]{
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0},
	};
	
	
	public static int[][] samuraiField=new int[][]{
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{1102,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,4401},
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{2202,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,5501},
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{3302,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,6601},
		{0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0},
	};
	
	public static int[][][] mapField=new int[][][]{
		{
			{1,1,2,1,1,1,1,1,1,2,1,1},
			{1,1,2,31,1,1,1,1,34,2,1,1},
			{5,1,4,2,2,1,1,2,2,4,1,1},
			{1,1,4,1,1,1,1,1,1,4,1,6},
			{1,1,1,1,1,4,4,1,1,1,1,1},
			{5,1,1,1,1,2,2,1,1,1,1,1},
			{1,1,1,1,1,2,2,1,1,1,1,6},
			{1,1,1,1,1,4,4,1,1,1,1,1},
			{5,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,2,1,1,1,1,1,1,2,1,6},
			{1,1,2,1,1,2,2,1,1,2,1,1},
			{1,2,33,1,1,1,1,1,1,32,2,1}
		},
		{
			{1,1,2,1,1,1,1,1,1,2,1,1},
			{1,1,2,31,1,1,1,1,34,2,1,1},
			{5,1,4,2,2,1,1,2,2,4,1,1},
			{1,1,4,1,1,1,1,1,1,4,1,6},
			{1,1,1,1,1,1,1,1,1,1,1,1},
			{5,1,1,2,1,4,2,1,2,1,1,1},
			{1,1,1,1,1,4,2,1,1,1,1,6},
			{1,1,1,1,1,2,4,1,1,1,1,1},
			{5,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,2,1,1,2,2,1,1,2,1,6},
			{1,1,4,1,1,1,1,1,1,4,1,1},
			{1,2,33,1,1,2,2,1,1,32,2,1}
		},
		{	
			{1,1,2,1,1,1,1,1,1,2,1,1},
			{1,1,2,31,1,1,1,1,34,2,1,1},
			{5,1,4,2,2,1,1,2,2,4,1,1},
			{1,1,4,1,1,1,1,1,1,4,1,6},
			{1,1,1,1,1,1,1,1,1,1,1,1},
			{5,1,1,1,1,2,2,1,1,1,1,1},
			{1,1,1,1,1,2,2,1,1,1,1,6},
			{1,1,1,1,1,35,1,1,1,1,1,1},
			{5,1,1,1,1,1,36,1,1,1,1,1},
			{1,1,2,2,1,1,1,1,4,2,1,6},
			{1,1,2,1,1,1,1,1,1,2,1,1},
			{1,2,33,1,1,4,2,1,1,32,2,1}
		},
		{
			{1,1,2,1,1,1,1,1,1,2,1,1},
			{1,1,2,31,1,1,1,1,34,2,1,1},
			{5,1,4,2,2,1,1,2,2,4,1,1},
			{1,1,4,1,1,1,1,1,1,4,1,6},
			{1,1,1,1,1,1,1,1,1,1,1,1},
			{5,1,1,4,1,2,2,1,4,1,1,1},
			{1,1,1,1,1,2,2,1,1,38,1,6},
			{1,1,1,4,1,1,1,1,4,1,1,1},
			{5,1,1,1,37,1,1,1,1,1,1,1},
			{1,1,2,1,1,1,1,1,1,2,1,6},
			{1,1,2,1,1,2,2,1,1,2,1,1},
			{1,2,33,1,1,1,1,1,1,32,2,1}
		}
	};

}

