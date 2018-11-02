package exer;
//Bellman Ford Shortest Path
//input format same as DijShortestPath.java
/*
5 8
0 1 -1
0 2 4
1 2 3
1 4 2
1 3 2
3 2 5
4 3 -3
3 1 1
 */
import java.util.Arrays;
import java.util.Scanner;

public class BFShortestPath {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		int numV = s.nextInt();
		int numE = s.nextInt();
		Graph g = new Graph(numV, numE);
		
		int u,v,w;
		for(int i = 0; i < numE; i++){
			u = s.nextInt();
			v = s.nextInt();
			w = s.nextInt();
			g.addE(u, v, w);
		}
		
		if(g.findSP(0)){
			System.out.println(Arrays.toString(g.p));
			System.out.println(Arrays.toString(g.d));	
		}else
			System.out.println("Contains negative cycle");
		
		s.close();
	}
}

class Graph{
	Edge[] edges;
	int numV;
	int numE;
	int addednumE = 0;
	
	int[] d; //distance
	int[] p; //parent
	
	Graph(int numV, int numE){
		this.numV = numV;
		this.numE = numE;
		d = new int[numV];
		p = new int[numV];	
		edges = new Edge[numE];
	}
	
	void addE(int u, int v, int w){
		//assume directed graph
		edges[addednumE++] = new Edge(u,v,w);
	}
	
	boolean findSP(int s){
		for(int i = 0; i < numV; i++)
			d[i] = Integer.MAX_VALUE;
		
		d[s] = 0;
		
		for(int i = 0; i < numV-1; i++)
			for(Edge e: edges)
				if(d[e.v] > d[e.u] + e.w){
					d[e.v] = d[e.u] + e.w;
					p[e.v] = e.u;
				}
		
		for(Edge e:edges)
			if(d[e.v]> d[e.u] + e.w)
				return false; 
				
		return true;
	}
}

class Edge{
	int u;
	int v;
	int w;
	Edge(int u, int v, int w){
		this.u = u;
		this.v = v;
		this.w = w;
	}
}
