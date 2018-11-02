package exer;

public class ExtendedEuclid {

	public static void main(String[] args){
		Solution s = euclid(27,5);
		System.out.println(s.d + " " + s.x + " " + s.y );
	}
	
	public static Solution euclid(int a, int b){
		if(b == 0) return new Solution(a,1,0);
		else{
			Solution s = euclid(b, a%b);
			s.set(s.d, s.y, s.x - s.y * (a/b));
			return s;
		}
	}
}

class Solution{
	int d;
	int x;
	int y;
	
	Solution(int d, int x, int y){
		this.d = d;
		this.x = x;
		this.y = y;
	}
	
	void set(int d, int x, int y){
		this.d = d;
		this.x = x;
		this.y = y;
	}
	
}
