package tree;

public class UniqueBinarySearchTrees {
	/*
	 * Q: Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
	 * */
	
	/*
	 * Algorithm: Dynamic Programming
	 *  We want to find function G(n) meaning the number of different ways to construct BST out of n numbers.
	 *  And we can get by using F(i,n) meaning the number of different BST constructed by making ith node as
	 *  the root. Therefore, G(n) = SUM_i(F(i,n)) in which i is from 1 to n. Since if we make ith node
	 *  as the root, F(i,n) = G(i-1)* G(n-i). So G(n)=SUM_i(G(i-1)*G(n-i)). So we have:
	 *  base case: G[0] = 1, G[1] = 1;
	 *  induction rule: G[i] = SUM_j(G[j-1]*G[i-j]) in which 1<=j<=i and 2<=i<=n.
	 *  return G[n] eventually.
	 *  
	 * Complexity Analysis:
	 * T: O(n^2)
	 * S: O(n) since we use an array with size n to store the intermediate solutions.
	 * */
	public int numTrees(int n) {
        if (n <=1){
            return 1;
        }
        int[] G=new int[n+1];
        G[0] = 1;
        G[1] = 1;
        for (int i= 2; i <=n;++i){
            for (int j=1; j <= i; ++j) {
                G[i] += G[j-1] * G[i-j];
            }
        }
        return G[n];
    }
}
