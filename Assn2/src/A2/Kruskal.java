package A2;
import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

        /* Fill this method (The statement return null is here only to compile) */
    	DisjointSets sets = new DisjointSets(g.getNbNodes());
    	ArrayList<Edge> edges = g.listOfEdgesSorted();
    	WGraph mst = new WGraph();
    	
    	for(int i=0; i<edges.size(); i++){
    		if(IsSafe(sets,edges.get(i))){	//Check that the edge crosses a cut respecting the mst we are building
    			mst.addEdge(edges.get(i));	//Add the edge
    			sets.union(edges.get(i).nodes[0], edges.get(i).nodes[1]);	//Merge the 2 sets
    		}	   				
    	}        
        return mst;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){

        /* Fill this method (The statement return 0 is here only to compile) */
    	int repSet1 = p.find(e.nodes[0]);
    	int repSet2 = p.find(e.nodes[1]);
    	if(repSet1 == repSet2)	//Check if adding the edge would create a cycle
    		return false;
    	
        return true;
    
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
