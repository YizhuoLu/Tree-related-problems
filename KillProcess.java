package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KillProcess {
	/*
	 * Q: Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
	 * Each process only has one parent process, but may have one or more children processes. This is just 
	 * like a tree structure. Only one process has PPID that is 0, which means this process has no parent 
	 * process. All the PIDs will be distinct positive integers.
	 * We use two list of integers to represent a list of processes, where the first list contains PID 
	 * for each process and the second list contains the corresponding PPID.
	 * Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs 
	 * of processes that will be killed in the end. You should assume that when a process is killed, all 
	 * its children processes will be killed. No order is required for the final answer.
	 * 
	 * 1. The given kill id is guaranteed to be one of the given PIDs.
	 * 2. n >= 1.
	 * */
	
	/*
	 * Algorithm: DFS + HashMap
	 *  First we get the kill which is the root node of the subtree. we can use DFS to traverse this 
	 *  sub-tree and add all the visited nodes into the resultant list in the process. I use the HashMap
	 *  to store the current root nodes value mapping the index of that in ppid list which will be very 
	 *  convenient for use to do recursive visit in the DFS later on.
	 *  
	 * Complexity Analysis:
	 * T: O(n) 
	 * S: O(h) h is the height of the sub-tree rooted with the given kill.
	 * */
	
	public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < ppid.size(); ++i) {
            int key= ppid.get(i);
            if (!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(i);
        }
        dfs(pid, map, res, kill);
        return res;
    }
    
    private void dfs(List<Integer> pid,  Map<Integer, List<Integer>> map, 
                    List<Integer> res, int kill) {
        // base case
        if (!map.containsKey(kill))  {
            res.add(kill);
            return;
        }
        res.add(kill);
        for (int idx : map.get(kill)) {
            dfs(pid, map, res, pid.get(idx));
        }
    }
}
