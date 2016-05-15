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

