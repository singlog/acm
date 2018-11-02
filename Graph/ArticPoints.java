/*
 * Find articulation points in a graph.
 * Articulation points are those if removed, disconnects the graph.
 */
package Graph;

public class ArticPoints {
	private static int time = 1;
	
	public static void main(String[] args){
		int[][] g = {{1,2},{0,2,3,4,6},{0,1},{1,5},{1,5},{3,4},{1}};// adj list representation
		boolean[] ap = AP(7,g);
		
		for(int i = 0; i < ap.length;i++)
			if(ap[i]) System.out.print(i + " ");
		
	}
	
	

	public static boolean[] AP(int V, int[][] adj){
		int[] parent = new int[V];
		boolean[] visited = new boolean[V];
		int[] dfsInd = new int[V];
		int[] low = new int[V];
		boolean[] ap = new boolean[V];
		
		for(int i = 0; i < V;i++) parent[i] = -1;
		
		for(int i = 0; i < adj.length;i++)
			if(!visited[i]) findAp(i,parent,visited,dfsInd,low,ap,adj);
				
		return ap;
	}
	
	public static void findAp(int u,int[] parent,boolean[] visited,int[] dfsInd, int[] low, boolean[] ap, int[][] adj){
		visited[u] = true;
		dfsInd[u] = time;
		low[u] = time;
		time++;
		
		int children = 0;
		
		int[] adjList = adj[u];
		for(int i = 0; i < adjList.length;i++){	
			int v = adjList[i];
			if(!visited[v]){
				children++;
				parent[v] = u;
				findAp(v,parent,visited,dfsInd,low,ap,adj);
				
				low[u] = Math.min(low[u], low[v]);
				
				if(parent[u] == -1 && children > 1) ap[u] = true;
				if(parent[u] != -1 && dfsInd[u] <= low[v]) ap[u] = true;
			}else if(i != parent[u])
				low[u] = Math.min(low[u],dfsInd[v]);
		}
	}
}
