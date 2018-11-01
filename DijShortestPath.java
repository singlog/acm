package exer;

import java.util.Arrays;

/*input:
 * numN numV
 * u v w 
 * u v w
 * ...
 * ...
 */

/*
 9 14
 0 1 4
 0 7 8
 1 7 11
 1 2 8
 2 3 7
 3 4 9
 4 5 10
 5 6 2
 6 7 1
 7 8 7
 2 8 2
 8 6 6
 3 5 14
 2 5 4
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class DijShortestPath {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		int u,v,w;
		int numV = s.nextInt();
		int numE = s.nextInt();
		Graph g = new Graph(numV);
		
		for(int i = 0 ; i < numE; i++){
			u = s.nextInt();
			v = s.nextInt();
			w = s.nextInt();
			g.addE(u, v, w);
		}
		
		g.findSP(0);
		
		System.out.println(Arrays.toString(g.p));
		System.out.println(Arrays.toString(g.d));
		
		s.close();
	}
}

class Graph{
	final static int N = 100; //maximum number of vertices
	int n;				  //number of vertices
	int[][] g = new int[N][N];
	int[] p ; //parent
	int[] d ; //distance
	boolean[] r ;
	
	Queue<Node> q = new PriorityQueue<Node>(N, new Comparator<Node>(){
			public int compare(Node n1, Node n2) {
				return n1.d - n2.d;
			}
		}
	);
	
	Graph(int n){
		this.n = n;
		p = new int[n+1];
		d = new int[n+1];
		r = new boolean[n+1];
	}
	
	void addE(int u, int v, int w){
		//assuming undirected
		g[u][v] = w;
		g[v][u] = w;
	}
		
	//find shortest distance from s to every node;
	void findSP(int s){
		for(int i = 0; i < n; i++)
			d[i] = Integer.MAX_VALUE;
		
		d[s] = 0;
		int num_reach = 0; // the start vertex is reached
		r[s] = true;
		q.add(new Node(s,0));
		
		Node nd;
		
		do{
			nd = q.remove();
			System.out.println(nd.u);
			r[nd.u] = true;
			num_reach++;
			for(int v = 0; v < n; v++){
				if(!r[v] && g[nd.u][v]!= 0 && d[v] > d[nd.u]+g[nd.u][v]){
					p[v] = nd.u;
					d[v] = d[nd.u]+g[nd.u][v];
					q.add(new Node(v,d[v]));
				}
			}
		}while(num_reach < n);
	}
}

class Node{
	Node(int u, int d){
		this.u = u;
		this.d = d;
	}
	int u;
	int d;
}