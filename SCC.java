/*
 * Find the strong connected component in a graph
 * Find the scc root for each node
 */

package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class SCC {
	public static void main(String[] args){
		GraphSCC g = new GraphSCC(7);
		int[][] edges = new int[][]{{0,1},{1,2},{2,0},{1,3},{1,4},{1,6},{3,5},{4,5}};
		for(int[] e: edges)
			g.addEdge(e[0], e[1]);
		
		//For debugging:
		//System.out.println("Adjacency list of graph:");
		//g.printGraph();
		g.dfs(0);
		System.out.println("\nThe SCC root for each node:");
		System.out.println(Arrays.toString(g.scc));
	}
	
}

class GraphSCC{
	ArrayList<LinkedList<Integer>> g;
	Stack<Integer> stack;
	int[] scc,disc,low;
	boolean[] visited,instack;
	int count = 0,size;
	
	protected GraphSCC(int n){
		size = n;
		
		g = new ArrayList<LinkedList<Integer>>(size);
		
		for(int i = 0;i < size;i++)
			g.add(new LinkedList<Integer>());
		
		stack = new Stack<Integer>();
		scc = new int[size];
		disc = new int[size];
		low = new int[size];
		visited = new boolean[size];
		instack = new boolean[size];
	}
	
	protected void addEdge(int u, int v){
		g.get(u).add(v);
	}
	
	protected void printGraph(){
		int i = 0;
		for(LinkedList<Integer> l:g){
			System.out.print(i + ":");
			for(int u:l) System.out.print(u + " ");
			System.out.println();
			i++;
		}	
	}
	
	protected void dfs(int u){
		stack.push(u);
		instack[u] = true;
		disc[u] = count;
		low[u] = count;
		count++;
		visited[u] = true;
		
		for(int v:g.get(u)){
			if(!visited[v]){
				dfs(v);								//v not visited			
				low[u] = Math.min(low[u], low[v]);
			}else if(instack[v]){
				low[u] = Math.min(low[u], disc[v]); //v has been visited
			}
		}
		
		int x;
		if(low[u] == disc[u]){			//if low[u] == disc[u], the node is a root
			do{
				x = stack.pop();		//pop from stack to get the nodes in the SCC of this root
				scc[x] = u;
				instack[x] = false;
			}while(x != u);
		}
	}

}
