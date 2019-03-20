package tree;

import java.util.ArrayList;
import java.util.List;

public class AverageOfLevelsInBinaryTree {
	/*
	 * Q: Given a non-empty binary tree, return the average value of the nodes on each level in the 
	 * form of an array.
	 * 
	 * The range of node's value is in the range of 32-bit signed integer.
	 * */
	
	/*
	 * Algorithm: DFS
	 *  Since I cannot record the average = sum / count at one time. But I can record the sum and count 
	 * at each layer respectively. I use a index as a marker to represent which layer I am visiting and 
	 * add corresponding value into the corresponding position or create a new position when I visit a
	 * new layer at the first time.
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(h) h is the height of the tree.
	 * */
	public List<Double> averageOfLevels(TreeNode root) {
        List<Integer> count = new ArrayList<>();
        List<Double> val = new ArrayList<>();
        dfs(root, count, val, 0);
        for (int i = 0; i < val.size(); ++i) {
            val.set(i, val.get(i) / count.get(i));
        }
        return val;
    }
    
    private void dfs(TreeNode root, List<Integer> cnt, List<Double> val, int idx) {
        // base case
        if (root == null) {
            return;
        }
        if (idx < val.size()) {
            val.set(idx, val.get(idx) + root.val * 1.0);
            cnt.set(idx, cnt.get(idx) + 1);
        } else {
            // new layer
            val.add(root.val * 1.0);
            cnt.add(1);
        }
        dfs(root.left, cnt, val, idx+1);
        dfs(root.right, cnt, val, idx+1);
    }
}
