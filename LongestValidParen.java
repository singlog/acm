/*leetcode 32
Given a string containing just the characters '(' and ')', 
find the length of the longest valid (well-formed) parentheses substring.
*/

package exer;

public class LongestValidParen {
	public static void main(String[] args){
		System.out.println(longestValidParentheses("(()())"));
	}
	
	public static int longestValidParentheses(String s){
		int[] maxtill = new int[s.length()];
		
        int max=0, maxtillcur = 0, numleft = 0;
        
        for(int i = 0; i < s.length();i++){
        	if(s.charAt(i) == '('){
        		numleft++;
        		maxtillcur = 0;
        	}else{
        		if(numleft != 0){
        			numleft--;
        			maxtillcur += 2;
        		}else maxtillcur = 0;
        		        		
        		if(i - maxtillcur >=0 ) maxtillcur += maxtill[i - maxtillcur];
        		max = Math.max(max, maxtillcur);       			
        	}       	
        	maxtill[i] = maxtillcur;
        }
		return max;
    }
}
