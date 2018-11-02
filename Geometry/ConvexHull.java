//UVA 11626
package geo;

import java.util.Arrays;
import java.util.Scanner;

public class ConvexHull {
	
	public static void main(String[] args){
		int max = 100000;
		Scanner s = new Scanner(System.in);
		int numcase = s.nextInt();
		
		int[] stack = new int[max];
		Point[] P = new Point[max];
		int nump,numch,x,y,min=0,top=0;
		String c;
		
		for(int i = 0;i < numcase;i++){
			nump = s.nextInt();
			numch = 0;
			
			for(int j = 0;j<nump;j++){
				x = s.nextInt();
				y = s.nextInt();
				c = s.next();
				if(c.equals("Y")){
					if(P[numch] == null) P[numch] = new Point(x,y);
					else{
						P[numch].x = x;
						P[numch].y = y;
					}
					numch++;
				}
			}
			
			for(int j = 1; j<numch;j++)
				if(P[j].y < P[min].y) min = j;
			
			
			for(int j = 0;j<numch;j++){
				if(j == min){
					P[j].cos = 1.1;
					continue;
				}
				P[j].cos = cos(P[j].x-P[min].x,P[j].y-P[min].y,1,0);
			}
				
			Arrays.sort(P,0,numch);
			
			stack[top++] = 0;
			stack[top++] = 1;
			stack[top++] = 2;
			
			for(int j = 3; j < numch; j++){
				while(cos(P[stack[top]].x-P[stack[top-1]].x,P[stack[top]].y-P[stack[top-1]].y,1,0) <=
					  cos(P[j].x-P[stack[top]].x,P[j].y-P[stack[top]].y,1,0)){
					top--;
				}
				stack[top++] = j;
			}
			
			System.out.println(numch);
			while(top != 0){
				System.out.print(P[stack[--top]].x + " " + P[stack[top]].y);
				System.out.println();
			}
		}
		
		s.close();
	}
	
	static double cos(int x1, int y1, int x2, int y2){
		return (x1*x2 + y1*y2)/Math.pow( (Math.pow(x1, 2)+Math.pow(y1, 2)) * (Math.pow(x2, 2)+Math.pow(y2, 2)),0.5);
	}
	
	
}


class Point implements Comparable<Point>{
	int x;
	int y;
	double cos;

	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int compareTo(Point o) {
		if(o.cos-this.cos < 0) return -1;
		else if(o.cos == this.cos) return 0;
		else return 1;
	}
}
