package cn.edu.nju.logic;

public class MainTest {

	public static int totalScore;
	public static int[][] rootHitRangeField;
	
	public Game game;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		rootHitRangeField = new int[][]{
			{0, 0, -1, 0, 0, 0, 0, 0, 0, -1, 0, 0 },
			{0, 0, -1, -1, 0 ,0, 0, 0, -1, -1, 0, 0 },
			{-1, 0, -1, -1, -1, 0, 0, -1, -1, -1, 0, 0 },
			{0, 0, -1, 0, 0, 0, 0, 0, 0, -1, 0, -1 },
			{0, 0, 0, 0, 0, -1, -1, 0, 0, 0, 0, 0},
			{-1, 0, 0, 0, 0, -1, -1, 0, 0, 0, 0, 0 },
			{0, 0, 0, 0, 0 ,-1 ,-1 ,0 ,0 ,0 ,0 ,-1 },
			{0, 0 ,0, 0 ,0 ,-1, -1, 0, 0, 0, 0, 0 },
			{-1, 0, 0, 0, 0, 0, 0, 0 ,0 ,0, 0 ,0 },
			{0, 0, -1, 0, 0, 0, 0, 0, 0, -1, 0, -1}, 
			{0 ,0 ,-1, 0 ,0 ,-1 ,-1, 0, 0, -1, 0 ,0} ,
			{0, -1, -1, 0 ,0 ,0 ,0 ,0 ,0, -1, -1, 0 }
		};
		
		totalScore = getTotalScore(rootHitRangeField);
		System.out.println(totalScore);
		
		
	}
	
	private static int getTotalScore(int[][] rootHitRangeField) {
		for (int i = 0; i < rootHitRangeField.length; i++) {
			for (int j = 0; j < rootHitRangeField[0].length; j++) {
				if (rootHitRangeField[i][j] != -1) {
					totalScore++;
				}
			}
		}
		return totalScore;
	}

}
