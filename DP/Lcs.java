public class Lcs {
	public static void main(String[] args){
		String s1 = "BCAABA";
		String s2 = "ABACB";
		
		System.out.println(LongestCommonSubsequence(s1,s2));
	}
	
	public static int LongestCommonSubsequence(String x, String y){
		
		//insert a ' ' at the beginning of the strings
		StringBuffer sb1 = new StringBuffer(x);
		sb1.insert(0, ' ');
		x = sb1.toString();
		StringBuffer sb2 = new StringBuffer(y);
		sb2.insert(0, ' ');
		y = sb2.toString();
		
		int[][] c = new int[x.length()+1][y.length()+1];
		char[][] b = new char[x.length()+1][y.length()+1]; //for tracking the common subsequence
		
		for(int i = 1; i < x.length(); i++){
			for(int j = 1; j < y.length(); j++){
				if(x.charAt(i) == y.charAt(j)){
					c[i][j] = c[i-1][j-1] + 1;
					b[i][j] = 'D';
				}else if(c[i-1][j] >= c[i][j-1]){
					c[i][j] = c[i-1][j];
					b[i][j] = 'U';
				}else {
					c[i][j] = c[i][j-1];
					b[i][j] = 'L';
				}
			}
		}
				
		return c[x.length()-1][y.length()-1];
	}
		
}
