package geo;

import java.util.Scanner;

/*assume ordered pairs of coordinates guaranteed to form a polygon
 *could be counterclockwise/clockwise
 input:
 numV
 x y
 ...
 ...
3
0 5
5 -5
-5 -5
*/

public class AreaPolygon {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		int numV = s.nextInt();
		
		//storing coords:
		double X[] = new double[numV];
		double Y[] = new double[numV];
		
		for(int i = 0; i < numV; i++){
			X[i] = s.nextDouble();
			Y[i] = s.nextDouble();
		}
		
		System.out.printf("%.2f",Area(X,Y));
		s.close();
	}
	
	public static double Area(double[] X, double[] Y){
		double a = 0;
		Point o = new Point(0,0); //origin
		for(int i = 0; i <= X.length-2; i++){
			a += Intersect.crossProduct(o, new Point(X[i],Y[i]), new Point(X[i+1],Y[i+1]));
		}
		
		a += Intersect.crossProduct(o, new Point(X[X.length-1],Y[Y.length-1]), new Point(X[0],Y[0]));
		return Math.abs(a/2);
	}
}
