//Knuth-Morris-Pratt Algorithm for string search
//search a pattern string in a longer text
//runs in linear time

import java.util.ArrayList;

public class KMP {
	
	public static void main(String[] args){
		String pattern = "abbac";
		String text = "abbaabbacabbabaabbac";
		
		System.out.println(kmp(text,pattern));;
	}
	
	public static int[] get_pmt(String s){
		int[] pmt = new int[s.length()];
		int pm_len = 0;
		pmt[0] = 0;
		for(int i = 1; i < s.length(); i++){
			while(pm_len > 0 && s.charAt(i) != s.charAt(pm_len))
				pm_len = pmt[pm_len-1];
			
			if(pm_len > 0 || s.charAt(0) == s.charAt(i))
				pm_len++;
				
			pmt[i] = pm_len;
		}
		
		return pmt;
	}
	
	public static ArrayList<Integer> kmp(String text, String pt){
		int[] pmt = get_pmt(pt);
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		int num_match = 0;
		
		for(int i = 0; i < text.length(); i++){
			while(num_match >0 && text.charAt(i) != pt.charAt(num_match)){
				num_match = pmt[num_match-1];
			}
			if(text.charAt(i) == pt.charAt(num_match)) num_match++;
			
			if(num_match == pt.length()){
				indices.add(i-pt.length()+1);
				num_match = pmt[pt.length()-1];
			}
		}
		
		return indices;
	}
}
