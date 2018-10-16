package A2;
import java.io.*;
import java.util.*;


/****************************
*
* COMP251 template file
*
* Assignment 2, Question 1
*
*****************************/


public class DisjointSets {

    private int[] par;
    private int[] rank;
    
    /* contructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSets(int n) {
        if (n>0) {
            par = new int[n];
            rank = new int[n];
            for (int i=0; i<this.par.length; i++) {
                par[i] = i;
            }
        }
    }
    
    public String toString(){
        int pari,countsets=0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i=0; i<this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari]==null) {
                setstrings[pari] = String.valueOf(i);
                countsets+=1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i=0; i<this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }
    
    /* find representative of element i */
    public int find(int i) {
    
        /* Fill this method (The statement return 0 is here only to compile) */
    	if(par[i]==i)
    		return i;	//Root and Rep found
    	else{
    		par[i]=find(par[i]);
    		return par[i];
    	}  
    }

    /* merge sets containing elements i and j */
    public int union(int i, int j) {
    	int new_rep = -1;
        /* Fill this method (The statement return 0 is here only to compile) */
    	if(find(i)== find(j)){	//Make sure not already in the same set
    		return find(i);	//Both have same rep, so arbitrarily return one rep
    	}  	
    	if(rank[find(i)]>rank[find(j)]){	//i is in a taller tree, merge j into i   		
			par[find(j)] = find(i);	//Make j's rep point to i's rep    			
			rank[find(j)]=rank[find(i)];	//Make j's rank equal to i's
			new_rep=find(i);
    	}
    	else if(rank[find(i)]<rank[find(j)]){	//j is in taller tree or they are equal height, merge i into j
			par[find(i)]=find(j);	//Make i's rep point to j's rep
			rank[find(i)]=rank[find(j)];	//Make i's rank equal to j's
			new_rep=find(j); 		   		
    	}      
    	else{	//ranks are equal, merge into j. Same as prev case, just incr rank
			rank[find(j)] = rank[find(j)]+1;	//Add one to the rank of the new rep
    		par[find(i)]=find(j);	//Make i's rep point to j's rep
			new_rep=find(j); 	
    	}
    	return new_rep;	
    }
    
    public static void main(String[] args) {
        
        DisjointSets myset = new DisjointSets(6);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2,3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 1");
        myset.union(2,1);
        System.out.println(myset);
        System.out.println("-> Union 4 and 5");
        myset.union(4,5);
        System.out.println(myset);
        System.out.println("-> Union 3 and 1");
        myset.union(3,1);
        System.out.println(myset);
        System.out.println("-> Union 2 and 4");
        myset.union(2,4);
        System.out.println(myset);
        
    }

}
