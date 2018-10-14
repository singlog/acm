//UVA10157
//Counting, DP

package exer;
import java.math.BigInteger;
import java.util.Scanner;

public class Expressions {
	public static void main(String[] args){
		int n,d;
		BigInteger[][] t = new BigInteger[151][151];
		
		for(int i = 0; i <= 150; i++){
			for(int j = 0; j <= 150; j++)
				t[i][j] = BigInteger.valueOf(0);
		} 
		t[0][0] = BigInteger.valueOf(1);
		
		BigInteger sum = BigInteger.valueOf(0);
		BigInteger u = BigInteger.valueOf(0);
		BigInteger x = BigInteger.valueOf(0);
		BigInteger v = BigInteger.valueOf(0);
		BigInteger w = BigInteger.valueOf(0);
		
		for(n = 1; n <= 150; n++){
			for(d = 1; d <= n; d++){
			    sum = BigInteger.valueOf(0);
				for(int k = 1; k <= n; k++){
					v = BigInteger.valueOf(0);
					w = BigInteger.valueOf(0);
					u = t[k-1][d-1];
					x = t[n-k][d];
					for(int i = 0; i <= d; i++)
						v = v.add(t[n-k][i]);
					for(int i = 0; i < d-1; i++)
						w = w.add(t[k-1][i]);
					sum = sum.add(u.multiply(v).add(w.multiply(x)));
				}
				t[n][d] = sum;
			}
		}
	
		
		Scanner s = new Scanner(System.in);
		while(s.hasNext()){
			n = s.nextInt();
			d = s.nextInt();
			if(n%2 == 1 || d*2 > n) System.out.println("0");
			else System.out.println(t[n/2][d]);
		}
		s.close();
	}
}
