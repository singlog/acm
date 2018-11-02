//poj 2084
//math
package exer;

import java.math.BigInteger;
import java.util.Scanner;

public class GameofConn {
	public static void main(String[] args){
		BigInteger[] N = new BigInteger[102];
		N[0] = BigInteger.valueOf(1);
		N[1] = BigInteger.valueOf(1);
		int n;
		int cur_max = 1;
		
		Scanner s = new Scanner(System.in);
		while((n = s.nextInt()) != -1){
			if(n <= cur_max){
				System.out.println(N[n]);
				continue;
			}
			for(int i = cur_max + 1; i <= n; i++){
				BigInteger sum = BigInteger.valueOf(0);
				for(int j = 0; j < i; j++){
					sum = sum.add( N[j].multiply(N[i-1-j]) );
				}
				N[i] = sum;
			}
			System.out.println(N[n]);
		}
		s.close();
	}
}
