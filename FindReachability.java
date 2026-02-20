import java.util.*;

public class Main {
    
    public static boolean dfs(int node,int end,  HashSet <Integer> set  , HashMap <Integer , List<Integer>> map){
        if(node == end){
            return  true;
        }
        
        set.add(node);
        List<Integer> neighbors = map.get(node);
        if (neighbors == null) return false;
        for(int child : neighbours){
            if(!set.contains(child)){
                if(dfs(child,end,set,map)){
                    return true;
                }
            }
        }
        return false;
    }
   
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        HashMap <Integer , List<Integer>> map = new HashMap<>();
        
        System.out.println("Developers : ");
        for(int i = 0 ; i < size ; i++){
            int developer = scan.nextInt();
            map.put(developer,new ArrayList<>());
        }
        
        System.out.println("Edges : ");
        int edges = scan.nextInt();
        for(int i = 0 ; i < edges ; i++){
            int from = scan.nextInt();
            int to = scan.nextInt();
            map.get(from).add(to);
            map.get(to).add(from);
        }
        
        HashSet <Integer> set = new HashSet<>();
        System.out.println("Start");
        int start = scan.nextInt();
        System.out.println("End");
        int end = scan.nextInt();
        
        if(dfs(start,end,set,map)){
            System.out.println("Can Reach");
            return;
        }
        
        System.out.println("Cannot Reach");
        
    }
    
}
