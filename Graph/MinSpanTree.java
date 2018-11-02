package Graph;
/* Kruskal's algorithm
 * Implements the Union-Find structure with minor modification
 * 
 * input: (standard input)
 * first line: n m
 * n is number of nodes, m is the number of edges
 * for the next m lines: w u v 
 * w: the weight ; u,v : the vertices of an edge
 * the vertices index start from 0
 * an example (this one is sorted by weight but is not necessary):
9 14
1         7      6
2         8      2
2         6      5
4         0      1
4         2      5
6         8      6
7         2      3
7         7      8
8         0      7
8         1      2
9         3      4
10        5      4
11        1      7
14        3      5
 *
 *output: lines of number of edges in mst
 *each line: u v the vertices of the edge
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MinSpanTree {
	public static void main(String[] args){
	 
		Scanner sc = new Scanner(System.in);
		int num_v = sc.nextInt();
		int num_edge = sc.nextInt();
		Graph_MST graph = new Graph_MST(num_v);
		int w,u,v;
		
		for(int i = 0; i < num_edge; i++){
			w = sc.nextInt();
			u = sc.nextInt();
			v = sc.nextInt();
			graph.addEdge(u, v, w);
		}
		
		ArrayList<Edge> mst = graph.find_mst();
		for(Edge e : mst)
			System.out.println(e.u + " " + e.v);
		
		sc.close();
	}
}

class Edge implements Comparable<Edge>{
	int u;
	int v;
	int w;
	Edge(int u, int v, int w){
		this.u = u;
		this.v = v;
		this.w = w;
	}
	
	public int compareTo(Edge e) {
		return this.w - e.w;
	}
}

class Graph_MST{
	static int max_v = 100;  //max number of vertices
	static int max_e = 10000;
	Edge[] edges = new Edge[max_e];
	int num_e = 0;
	int num_v;
	
	Graph_MST(int num_v){
		this.num_v = num_v;
	}
	
	void addEdge(int u, int v, int w){
		edges[num_e++] = new Edge(u,v,w);
	}
	
	ArrayList<Edge> find_mst(){
		ArrayList<Edge> mst = new ArrayList<Edge>();
		UnionFind uf = new UnionFind(num_v);
		Arrays.sort(edges,0,num_e);
		boolean span;
		
		for(int i = 0; i < num_e; i++){
			if(uf.find(edges[i].u) != uf.find(edges[i].v)){
				span = uf.union(edges[i].u, edges[i].v);
				mst.add(edges[i]);
				if(span) break;
			}
		}
		return mst;
	}
}

class UnionFind{
	static int max_l = 1000;
	int l;
	int[][] L = new int[max_l][2];
	
	UnionFind(int l){
		this.init(l);
		this.l = l;
	}
	
	void init(int l){
		for(int i = 0;i < l; i++){
			L[i][0] = i;
			L[i][1] = 1;
		}
			
	}
	
	int find(int x){
		if(L[x][0] == x) return x;
		int root = find(L[x][0]);
		L[x][0] = root;
		return root;
	}
	
	//return true if a single set includes all elements
	boolean union(int x, int y){
		int ry = find(y);
		int rx = find(x);
		if(ry != rx){
			L[ry][1] += L[rx][1];
			L[rx][0] = ry;
		} 
		return L[ry][1] == l ? true : false;
	}
}
