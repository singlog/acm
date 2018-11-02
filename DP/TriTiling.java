//UVA10918
//Math,DP
package exer;
import java.util.Scanner;

public class TriTiling {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		int n,tmax = 1; 
		int[] t = new int[16], sum = new int[16];
		t[0] = 1; t[1] = 3;
		sum[0] = 1;sum[1] = 4;
		
		while((n = s.nextInt()) != -1){
			if(n%2 == 1) System.out.println(0);
			else{
				
				for(int i = tmax+1 ; i <= n>>1;i++){
					t[i] = sum[i-2]*2 + t[i-1]*3;
					sum[i] = sum[i-1] + t[i];
				}
				System.out.println(t[n>>1]);
			}	
		}
		s.close();
	}
}
