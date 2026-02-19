import java.util.*;

public class Main
{   
    static class Pair{
        int node;
        int weight;
        Pair(int node,int weight){
            this.node = node;
            this.weight = weight;
        }
    }
    
    static int totalCost =0;
    
    public static boolean findTotalCost(int node,int end,List<List<Pair>> adj,boolean [] visited ,int CurrentSum){
        if(node == end){
            totalCost += CurrentSum;
            return true;
        }
        
        visited[node] = true;
        
        for(Pair pair : adj.get(node)){
            int nextNode = pair.node;
            int weight = pair.weight;
            
            if(!visited[nextNode]){
               if(findTotalCost(nextNode,end,adj,visited,CurrentSum+weight)){
                   return true;
               }
            }
        }
        return false;
    }
    public static void update(int node,int end,List<List<Pair>> adj,int newWeight){
        for(Pair pair : adj.get(node)){
            if(pair.node == end){
                pair.weight = newWeight;
                break;
            } 
        }
        for(Pair pair : adj.get(end)){
            if(pair.node == node){
                pair.weight = newWeight;
                break;
            } 
        }
    }
    
	public static void main(String[] args) {
	    Scanner scan = new Scanner(System.in);
	    
		System.out.println("Enter Number of cities : ");
		int numberCities = scan.nextInt();
		System.out.println("Number of cities : "+numberCities);
		
		System.out.println("Enter Root : ");
		int root = scan.nextInt();
		System.out.println("Root : "+root);
		
		System.out.println("Enter Number of Edges : ");
		int edges = scan.nextInt();
		System.out.println("Edges : "+edges);
		
		List<List<Pair>> adj = new ArrayList<>();
		
		System.out.println("ArrayList Created : ");
		for(int i = 0 ; i <= numberCities ; i++){
		    adj.add(new ArrayList<>());
		}
		
		System.out.println("Enter Edges and weight : ");
		for(int i = 0 ; i < edges ; i++){
		   int u = scan.nextInt();
		   int v = scan.nextInt();
		   int w = scan.nextInt();
		   
		   adj.get(u).add(new Pair(v,w));
		   adj.get(v).add(new Pair(u,w));
		}
		
	
		System.out.println("Its Query time : ");
		System.out.println("Enter number of Query : ");
		int query = scan.nextInt();
		for(int i = 0 ; i < query ; i++){
		    int option = scan.nextInt();
		    int start = scan.nextInt();
		    int end = scan.nextInt();
		    int updation = scan.nextInt();
		    
		    if(option == 1){
		        
		        boolean [] visited = new boolean[numberCities+1];
		        findTotalCost(start,end,adj,visited,0);
		        
		    }else if(option == 2){
		        update(start,end,adj,updation);
		    }
	    }
		System.out.println(totalCost);
	}
}
