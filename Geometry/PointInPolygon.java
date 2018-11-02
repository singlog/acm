package geo;

import java.util.Scanner;

/*
 input:
 target_x target_y
 numV
 x y
 ...
 Assuming in order and at least 3 pairs. If not, use Convex Hull
 
5 6
4
0 0
5 10
10 0
5 5
 */
public class PointInPolygon {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		double x = s.nextDouble();
		double y = s.nextDouble();
		
		int numV = s.nextInt();		
		//storing the coords:	
		double X[] = new double[numV];
		double Y[] = new double[numV];
		
		for(int i = 0; i < numV;i++){
			X[i] = s.nextDouble();
			Y[i] = s.nextDouble();
		}
		
		System.out.println(isInPolygon(X,Y,x,y));
		s.close();
	}
	
	public static boolean isInPolygon(double[] X, double[] Y, double x, double y){
		
		int c = 0; //number of intersections
		
		//the line segment with end points (x,y) and (inf,y)
		Point p0 = new Point(x,y);
		Point pe = new Point(Double.MAX_VALUE,y); 
		
		for(int i = 0; i < X.length -1; i++){
			if(Intersect.doIntersect(p0, pe, new Point(X[i],Y[i]), new Point(X[i+1],Y[i+1])))
				c++;
		}
		
		if(Intersect.doIntersect(p0, pe, new Point(X[X.length-1],Y[Y.length-1]), new Point(X[0],Y[0])))
			c++;
		
		return c%2 == 0? false:true;
	}
}
