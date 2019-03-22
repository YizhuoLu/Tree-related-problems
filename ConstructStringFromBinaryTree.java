package tree;

public class ConstructStringFromBinaryTree {
	/*
	 * Q: You need to construct a string consists of parenthesis and integers from a binary tree with the 
	 * pre-order traversing way.
	 * The null node needs to be represented by empty parenthesis pair "()". And you need to omit all 
	 * the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string 
	 * and the original binary tree.
	 * */
	
	/*
	 * Algorithm: recursion
	 *  We first add the res containing root.val. then we call it for root.left and root.right. Then I judge if
	 *  left is null, we add a () between res and right. if right is null, we do not need to add extra ().
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(h) h is the height of tree. it's O(n) in the worst case.
	 * */
	public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        String res = t.val + "";
        String left = tree2str(t.left);
        String right = tree2str(t.right);
        if (left == "" && right== "") {
            return res;
        }
        if (left == "") {
            return res + "()" + "(" + right + ")";
        }
        if (right == "") {
            return res + "(" + left + ")";
        }
        return res + "(" + left + ")" + "(" + right + ")";
    }
	
	/*
	 * A more concise edition.
	 * */
	public String tree2strII(TreeNode t) {
		if (t == null)
			return "";
		if (t.left == null && t.right == null)
			return t.val + "";
		if (t.right == null)
			return t.val + "(" + tree2strII(t.left) + ")";
		return t.val + "(" + tree2strII(t.left) + ")(" + tree2strII(t.right) + ")";
	}
}
