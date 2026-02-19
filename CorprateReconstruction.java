import java.util.*;

public class Main{
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        
        int [] parentArray = new int[size+1];
        int [] compilanceArray = new int[size+1];
        int [] numberOfCompilanceChildArray = new int[size+1];
        
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < size+1 ; i++) adj.add(new ArrayList<>());
        
        for(int i = 1 ; i < size+1 ; i++){
            int parent = scan.nextInt();
            int compilance = scan.nextInt();
            
            parentArray[i] = parent;
            compilanceArray[i] = compilance;
            
            if(parent != -1){
                adj.get(parent).add(i);
                if(compilance == 1){
                    numberOfCompilanceChildArray[parent]++;
                }
            }
        }
        PriorityQueue <Integer> queue = new PriorityQueue<>();
    
        for(int i = 1 ; i < size+1 ; i++){
            if(numberOfCompilanceChildArray[i] == 0 && compilanceArray[i] == 1){
                queue.add(i);
            }
        }
        
        ArrayList <Integer> result = new ArrayList<>();
        
        while(!queue.isEmpty()){
            int node = queue.poll();
            result.add(node);
            
            int parentNode = parentArray[node];
            
            for(int child : adj.get(node)){
                
                parentArray[child] = parentNode;
                
                if(parentNode != -1){
                    adj.get(parentNode).add(child);
                }
                
                if(compilanceArray[child] == 1){
                    numberOfCompilanceChildArray[parentNode]++;
                }
            }
            
            if(parentNode != -1){
                numberOfCompilanceChildArray[parentNode]--;
                if(numberOfCompilanceChildArray[parentNode] == 0 && compilanceArray[parentNode] == 1){
                    queue.offer(parentNode);
                }
            }
            
            
        }
        
        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int x : result) {
                System.out.print(x + " ");
            }
        }
        
        
    }
}
