import java.util.*;

public class Main {
    static HashMap<String,Integer> map = new HashMap<>();
    
    public static String uniqueKey(int x , int y){
        int max = Math.max(x,y);
        int min = Math.min(x,y);
        return max + "-"+ min;
    }
    
    public static void add(int x , int y,int fees){
        while(x != y){
            if(x > y){
                String key = uniqueKey(x,x/2);
                map.put(key,map.getOrDefault(key,0) + fees);
                x/=2;
            }else{
                String key = uniqueKey(y,y/2);
                map.put(key,map.getOrDefault(key,0) + fees);
                y/=2;
            }
        }
    }
    public static int get(int x , int y){
       int cost = 0;
       while(x != y){
           if(x > y){
                String key = uniqueKey(x,x/2);
                cost += map.getOrDefault(key,0);
                x/=2;
            }else{
                String key = uniqueKey(y,y/2);
                cost += map.getOrDefault(key,0);
                y/=2;
            }
       }
       return cost;
    }
   
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of Queries :");
        int size = scan.nextInt();
        
        for(int i = 0 ; i < size ; i++){
            int choice = scan.nextInt();
            int start = scan.nextInt();
            int end = scan.nextInt();
            int fees = scan.nextInt();
            if(choice == 1){
                System.out.println(start + " - " + end +": Updated : "+fees);
                add(start,end,fees);
            }else{
                int distance = get(start,end);
                System.out.println(start + " - " + end +": "+distance);
            }
        }
        
    }
    
}
