package ecs122;
//maximum common substring

public class Mcs {
	public static void main(String[] args){
		String x = "algorithm";
		String y = "pplogarithm";
		
		System.out.println(mcs(x,y));
	}
	
	public static int mcs(String xstr, String ystr){		
		//insert a ' ' at the beginning of the strings
		StringBuffer sb1 = new StringBuffer(xstr);
		sb1.insert(0, ' ');
		xstr = sb1.toString();
		StringBuffer sb2 = new StringBuffer(ystr);
		sb2.insert(0, ' ');
		ystr = sb2.toString();
		
		char[] x = xstr.toCharArray();
		char[] y = ystr.toCharArray();
		
		int[][] m = new int[x.length][y.length]; //max match before current chars
		int[][] l = new int[x.length][y.length]; //max match containing current chars
		
		for(int i = 1; i < x.length;i++){
			for(int j = 1; j < y.length; j++){
				l[i][j]= (x[i] == y[j]) ? l[i-1][j-1] + 1 : 0;
				
				int a;
				if(x[i] == y[j]){
					a = (l[i-1][j-1] + 1 > m[i-1][j-1]) ? l[i-1][j-1] + 1 : m[i-1][j-1];
				}else{
					a = m[i-1][j-1];
				}
				
				m[i][j] = max3(m[i-1][j], m[i][j-1],a);
			}
		}
		
		//MatrixCh.latex_2d(m, 0, 0);
		
		//System.out.println();
		
		//MatrixCh.latex_2d(l,0,0);
		return m[x.length-1][y.length-1];
	}
	
	
	private static int max3(int a, int b, int c){
		if(a > b){
			if(a > c) return a;
			else return c;
		}else{
			if(b > c) return b;
			else return c;
		}
	}
}
