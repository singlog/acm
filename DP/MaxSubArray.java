//leetcode 53

class MaxSubArray {
    public static void main(String args[]){
	int[] arr = new int[]{0,1,-1,2};
	System.out.println(maxSubArray(arr));
    }

    public int maxSubArray(int[] nums) {
        int maxtoEnd = nums[0], maxSoFar = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            maxtoEnd = Math.max(nums[i], maxtoEnd + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxtoEnd);
        }
        
        return maxSoFar;
    }
}
