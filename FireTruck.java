//UVA208
//Finding all paths from one node to another in a graph (using DFS)
//Using BFS to precheck accessiblity

package Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FireTruck {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		int a,b,i=1;
		FTSolver solver = new FTSolver();
		
		while(s.hasNext()){
			solver.target = s.nextInt()-1;
			solver.init();
			while((a = s.nextInt()) != 0){
				b = s.nextInt();
				solver.addEdge(--a,--b);
			}	
			
			System.out.printf("CASE %d:\n", i);
			if(solver.bfs(0)) solver.find(0, 0);
		
			System.out.printf("There are %d routes from the firestation to streetcorner %d.\n",solver.numpaths,solver.target+1); 	 	
		
			s.nextInt();
			i++;
		}
		
		s.close();
	}
}


class FTSolver{
	int[][] g =  new int[20][20];
	boolean[] visited = new boolean[20];
	boolean[] bfsvisited = new boolean[20];
	int[] path = new int[20];
	int numedges,target,numpaths;
	Queue<Integer> bfsq = new LinkedList<Integer>();
	
	protected void init(){
		for(int[] l:g)
			for(int i = 0;i < 20;i++)
				l[i] = 0;
		
		for(int i = 0;i < 20;i++)
			visited[i] = false;
		
		for(int i = 0;i < 20;i++)
			path[i] = 0;
		
		for(int i = 0;i < 20;i++)
			bfsvisited[i] = false;
		
		numedges = 0;
		numpaths = 0;
	}
	
	protected void addEdge(int u, int v){
		g[u][v] = 1;
		g[v][u] = 1;
		numedges++;
	}
	
	protected boolean bfs(int u){
		bfsq.add(u);
		while(!bfsq.isEmpty()){
			int node = bfsq.poll();
			for(int v = 0; v < 20; v++){
				if(g[node][v] == 1){
					if(v == target) return true;
					else if(bfsvisited[v] == false){
						bfsvisited[v] = true;
						bfsq.add(v);
					}
				}
			}
		}
		return false;
	}
	
	protected void find(int u, int dep){
		
		if(numedges == 0 && target != 0) return;
		
		path[dep] = u;
		if(u == target){
			numpaths++;
			for (int i = 0; i <= dep-1;i++)
				System.out.print(path[i]+1 + " ");
			System.out.print(target+1);
			System.out.println();
				
		}else{
			visited[u] = true;
			for(int v = 0; v < 20; v++){
				if(g[u][v] == 1 && !visited[v]){
					find(v,dep+1);
				} 
			}
			visited[u] = false;
		}
	}
	
}