import java.util.*;

public class Main {
    static int count = 0;
    public static void dfs(int node , int parent ,int currentHazzard,
    int maxConsecutive,int [] hazzard,List<List<Integer>> adj ){
        
        if(hazzard[node] == 1){
            currentHazzard++;
        }else{
            currentHazzard = 0;
        }
        
        if(currentHazzard > maxConsecutive) return ;
        
        boolean isLeaf = true;
        for(int child : adj.get(node)){
            if(child != parent){
                dfs(child,node,currentHazzard,maxConsecutive,hazzard,adj);
                isLeaf = false;
            }
        }
        
        if(isLeaf) count++;
        
    }
   
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of Nodes :");
        int numberOfNode = scan.nextInt();
        System.out.print("Enter number of Hazzards :");
        int maxConsecutive = scan.nextInt();
        
        List<List<Integer>> adj = new ArrayList<>();
        
        for(int i = 0 ; i <= numberOfNode ; i++){
            adj.add(new ArrayList<>());
        }
        
        int[] hazzard = new int[numberOfNode+1];
        for(int i = 1 ; i <= numberOfNode ; i++){
            hazzard[i] = scan.nextInt();
        }
        
        for(int i = 0 ; i < numberOfNode-1 ; i++){
            int u = scan.nextInt();
            int v = scan.nextInt();
            
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        dfs(1,-1,0,maxConsecutive,hazzard,adj);
        System.out.println("count :" +count);
        
    }
    
}
