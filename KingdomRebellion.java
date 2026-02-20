import java.util.*;

public class Main {
   
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        
        int [] numberloyal = new int[size+1];
        int [] loyalty = new int[size+1];
        
        List<List<Integer>> adj = new ArrayList<>();
        
        for(int i = 0 ; i <= size ; i++){
            adj.add(new ArrayList<>());
        }
        
        for(int i = 1 ; i <= size ; i++){
            int parent = scan.nextInt();
            int loyal = scan.nextInt();
            loyalty[i] = loyal;
            if(parent != -1){
                adj.get(parent).add(i);
                if(loyal == 1){
                    numberloyal[parent]++;
                }
            }
        }
        
        Queue <Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        
        for(int i =1 ; i <= size ; i++){
            if(numberloyal[i] == 0 && loyalty[i] == 1){
                queue.add(i);
            }
        }
        
        while(!queue.isEmpty()){
            int node = queue.poll();
            result.add(node);
            for(int child : adj.get(node)){
                if(numberloyal[child] == 0 && loyalty[child] == 1){
                    queue.add(child);
                }
            }
        }
        
        
        System.out.println("Not Loyal Child Removed");
        for(int num : result){
            System.out.print(num+" ");
        }
        
    }
    
}
