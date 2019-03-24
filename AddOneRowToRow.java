package tree;

import java.util.LinkedList;
import java.util.Queue;

public class AddOneRowToRow {
	/*
	 * Q: Given the root of a binary tree, then value v and depth d, you need to add a row of nodes 
	 * with value v at the given depth d. The root node is at depth 1.
	 * The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, 
	 * create two tree nodes with value v as N's left subtree root and right subtree root. And N's original 
	 * left subtree should be the left subtree of the new left subtree root, its original right subtree should 
	 * be the right subtree of the new right subtree root. If depth d is 1 that means there is no depth d-1 at
	 *  all, then create a tree node with value v as the new root of the whole original tree, and the original 
	 *  tree is the new root's left subtree.
	 * 
	 * 1. The given d is in range [1, maximum depth of the given tree + 1].
	 * 2. The given binary tree has at least one tree node.
	 * */
	
	/*
	 * Algorithm: BFS
	 *  Level order traversal, when we reach the (d-1) levels, we do the operation.
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(n)
	 * */
	public TreeNode addOneRow(TreeNode root, int v, int d) {
        // corner case
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        Queue<TreeNode> queue= new LinkedList<>();
        queue.offer(root);
        int dep=1;
        while (!queue.isEmpty()) {
            int size =queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = queue.poll();
                if (dep == d-1) {
                    TreeNode left = cur.left;
                    TreeNode right = cur.right;
                    TreeNode node1 = new TreeNode(v);
                    TreeNode node2 = new TreeNode(v);
                    cur.left = node1;
                    cur.right = node2;
                    node1.left = left;
                    node2.right = right;
                } else {
                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }
                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                }
            }
            if (dep == d) break;
            dep++;
        }
        return root;
    }
	
	/*
	 * Algorithm 2: DFS
	 * */
	public TreeNode addOneRowII(TreeNode root, int v, int d) {
        if (d==1) {
            TreeNode node= new TreeNode(v);
            node.left =root;
            return node;
        }
        insert(root, v, 1, d);
        return root;
    }
    
    private void insert(TreeNode root, int v, int dep, int d){
        // base case
        if (root == null){
            return;
        }
        if (dep == d - 1) {
            TreeNode t = root.left;
            root.left= new TreeNode(v);
            root.left.left = t;
            t = root.right;
            root.right = new TreeNode(v);
            root.right.right = t;
        } else {
            insert(root.left, v, dep + 1, d);
            insert(root.right, v,dep+1,d);
        }
    }
}
