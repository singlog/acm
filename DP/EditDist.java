/*
 * leetcode 72
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
You have the following 3 operations permitted on a word:
Insert a character
Delete a character
Replace a character
 */

package exer;

public class EditDist {
	public static void main(String[] args){
		System.out.println(minDistance("horse","rose"));
	}
	
	public static int minDistance(String word1, String word2){
		
		int[][] D = new int[word2.length()+1][word1.length()+1];
		
		for(int i = 0; i <= word1.length();i++)
			D[0][i] = i;
		
		for(int i = 0; i <= word2.length();i++)
			D[i][0] = i;
		
		for(int i = 1; i <= word2.length();i++){
			for(int j = 1; j <= word1.length();j++){
				int dist = word1.charAt(j-1) == word2.charAt(i-1) ? 0:1;
				D[i][j] = min3(D[i-1][j-1]+dist, 1+D[i-1][j], 1+D[i][j-1]);
			}
		}
		
		return D[word2.length()][word1.length()];
	}
	
	private static int min3(int a, int b, int c){
		if(a < b){
			if(a < c) return a;
			else return c;
		}else{
			if(b < c) return b;
			else return c;
		}
	}
}
