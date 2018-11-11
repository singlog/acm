//leetcode 124

public class MaxPathSum{
  public static void main(String args[]){
    
  }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }

class Pair{
    int max;
    int max_s;
    
    Pair(int x, int y){
        max = x;
        max_s = y;
    }
}

class Solution {
    public int maxPathSum(TreeNode root) {
        if(root.left == null && root.right == null) return root.val;
        return maxHelper(root).max;
    }
    
    private Pair maxHelper(TreeNode node){
        if(node.left == null && node.right == null) return new Pair(node.val,Math.max(node.val,0));
        
        Pair l = null,r = null;
        if(node.right != null) r = maxHelper(node.right);
        if(node.left != null) l = maxHelper(node.left);
        
        if(l == null || r == null){
            Pair p = l == null ? r:l;
            return new Pair(max3(p.max_s+node.val, node.val, p.max),  max3(p.max_s+node.val,node.val,0));
        }
        
        int s = max3(l.max_s + node.val, r.max_s+node.val,node.val);
        return new Pair(max3(s,l.max_s + r.max_s + node.val,Math.max(r.max,l.max)), Math.max(s,0));
        
    }
    
    private static int max3(int a, int b, int c){
        if(a >= b && a >= c) return a;
        else if(b >= c) return b;
        else return c;
    }
}
