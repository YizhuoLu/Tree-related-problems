package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFrequentSubtreeSum {
	/*
	 * Q: Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree 
	 * sum of a node is defined as the sum of all the node values formed by the subtree rooted at that 
	 * node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, 
	 * return all the values with the highest frequency in any order.
	 * 
	 * You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
	 * */
	
	/*
	 * Algorithm 1: 
	 *  Use post-order traversal to visit the tree and calculate the sum accordingly. Then find the items
	 *  with the highest frequency from the map and add them into the result.
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(n)
	 * */
	public static int[] findFrequentTreeSum(TreeNode root) {
        // corner case
        if (root == null) {
            return new int[]{};
        }
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(null, 0);
        post(root, map);
        map.remove(null);
//        for (Map.Entry<TreeNode, Integer> entry :map.entrySet()) {
//        	System.out.println(entry.getKey().val + " " + entry.getValue());
//        }
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        int maxFreq = 0;
        for (int val : map.values()) {
            freq.put(val, freq.getOrDefault(val, 0) + 1);
        }
//        for (Map.Entry<Integer, Integer> entry :freq.entrySet()) {
//        	System.out.println(entry.getKey() + " " + entry.getValue());
//        }
        for (int val : freq.values()) {
            maxFreq = Math.max(maxFreq, val);
        }
        List<Integer> list = new ArrayList<>();
        for (int key: freq.keySet()) {
            int val = freq.get(key);
            if (val == maxFreq) list.add(key);
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (int val : list) {
            res[i++] = val;
        }
        return res;
    }
    
    private static void post(TreeNode root, Map<TreeNode, Integer> map){
        // base case
        if (root == null){
            return;
        }
        post(root.left,map);
        post(root.right, map);
        int sum = map.get(root.left) + map.get(root.right)+ root.val;
        map.put(root, sum);
    }
    
    /*
     * Algorithm 2: Do all the job in the helper function (make it return sum each time)
     * 
     * Complexity Analysis:
     * T: O(n)
     * S: O(n)
     * */
    static Map<Integer, Integer> map = new HashMap<>();
    static int maxCount, freq;
    public static int[] findFrequentTreeSumII(TreeNode root) {
        if (root == null){
            return new int[0];
        }
        postII(root);
//	      for (Map.Entry<Integer, Integer> entry :map.entrySet()) {
//	    	System.out.println(entry.getKey() + " " + entry.getValue());
//	      }
        int[] res = new int[freq];
        int i = 0;
        for (int key : map.keySet()) {
        	int val = map.get(key);
            if (val == maxCount) res[i++] = key;
        }
        return res;
    }
    
    private static int postII(TreeNode root){
        int leftVal = 0, rightVal = 0, sum = 0;
        if (root == null) {
            return 0;
        }
        if (root.left != null) {
            leftVal = postII(root.left);
        }
        if (root.right != null) {
            rightVal = postII(root.right);
        }
        sum = leftVal + rightVal + root.val;
        int count = map.getOrDefault(sum, 0) + 1;
        if (maxCount < count) {
            maxCount = count;
            freq = 1;
        } else if (maxCount == count) {
            freq++;
        }
        map.put(sum, count);
        return sum;
    }
    
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(5);
    	root.left = new TreeNode(2);
    	root.right = new TreeNode(-3);
    	root.left.left = new TreeNode(1);
    	int[] res = findFrequentTreeSumII(root);
    	for (int x : res) {
    		System.out.println(x);
    	}
    }
}
