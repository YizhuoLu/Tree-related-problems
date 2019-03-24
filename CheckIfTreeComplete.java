package tree;

import java.util.LinkedList;
import java.util.Queue;

public class CheckIfTreeComplete {
	/*
	 * Q: Given a binary tree, determine if it is a complete binary tree.
	 *  In a complete binary tree every level, except possibly the last, is completely filled, and all 
	 *  nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive 
	 *  at the last level h.
	 *  
	 * The tree will have between 1 and 100 nodes.
	 * */
	
	/*
	 * Algorithm: BFS + flag
	 *  I set a boolean variable to be false at first. It marks if we have found a node before the current one
	 *  that is lack of one child. We visit the tree nodes in level order manner. Each time, if we found the
	 *  current node has children but flag == true, we just return false at once and so on so forth.
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(n) since I use a queue, O(n) is in the worst case.
	 * */
	public boolean isCompleteTree(TreeNode root) {
        // corner case
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                if (flag){
                    return false;
                } 
                queue.offer(cur.left);
            } else {
                flag = true;
            }
            if (cur.right != null){
                if (flag){
                    return false;
                }
                queue.offer(cur.right);
            } else{
                flag=true;
            }
        }
        return true;
    }
}
