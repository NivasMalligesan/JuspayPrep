import java.util.*;

public class Main{
    static int largestCycleSum =0;
    
    public static void largestSum(int node,int [] arr , int n,boolean [] visited){
        visited[node] = true;
        int nextNode = arr[node];
        
        if(!visited[nextNode]){
            largestSum(nextNode,arr,n,visited);
        }else{
            int cycleSum = 0;
            int currentNode = nextNode;
            do{
                cycleSum+=currentNode;
                currentNode = arr[currentNode];
            }while(currentNode != nextNode);
            
            largestCycleSum = Math.max(largestCycleSum,cycleSum);
        }
    }
    
	public static void main(String[] args) {
		int [] edges = {1, 2, 0, 4, 5, 3};
		int n = edges.length;
		boolean visited [] = new boolean[n];
		for(int i = 0 ; i < n ; i++){
		    if(!visited[i]){
		        largestSum(i,edges,n,visited);
		    }
		}
		System.out.println(largestCycleSum);
	}
}
