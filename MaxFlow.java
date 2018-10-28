package exer;

import java.util.Scanner;
//assume max number of nodes = 200

public class MaxFlow {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		
		int numE = s.nextInt();
		int numV = s.nextInt();
		Graph g = new Graph(numV);

		int u,v,w;
		for(int i = 0; i < numE; i++){
			u = s.nextInt();
			v = s.nextInt();
			w = s.nextInt();
			g.addE(--u,--v, w);
		}
		System.out.println(g.maxflow());
		
		s.close();
	}
}

class Graph{
	int[][] g = new int[201][201];
	boolean visited[] = new boolean[201];
	int numV;
	
	Graph(int numV){
		this.numV = numV;
	}
	
	
	protected void addE(int u, int v, int w){
		g[u][v] = w;
	}
	
	protected int maxflow(){
		int flow = 0;
		int f;
		while( (f = dfs(0,numV-1,Integer.MAX_VALUE)) != 0){
			for(int i = 0; i < numV; i++)
				visited[i] = false;
			flow += f;
		}
		return flow;
	}
	
	protected int dfs(int u, int t, int f){
		if(u == t) return f;
		int minf = f, subf;
		
		visited[u] = true;
		for(int v = 0; v < numV; v++){
			if(!visited[v] && g[u][v] > 0){
				minf = Math.min(f,g[u][v]);
				subf = dfs(v,t,minf);
				if(subf != 0){
					g[u][v] -= subf;
					g[v][u] += subf;
					return subf;
				}	
			}
		}

		visited[u] = false;
		return 0;
	}
	
}