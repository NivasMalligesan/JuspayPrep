import java.util.*;

public class Main{
    public static boolean canBePlaced(int [][] station , int distance){
        int lastPlaceStation = station[0][0];
        
        for(int i = 1 ; i < station.length ; i++){
            int start = station[i][0];
            int end = station[i][1];
            
            int nextPosition = Math.max(start , lastPlaceStation+distance);
            
            if(nextPosition > end){
                return false;
            }
            
            lastPlaceStation = nextPosition;
        }
        
        return true;
    }
    
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int [][] station = new int[size][2];
        
        for(int i = 0 ; i < size ; i++){
            int l = scan.nextInt();
            int u = scan.nextInt();
            
            station[i][0] = l;
            station[i][1] = u;
        }
        
        Arrays.sort(station,(a,b)->a[0]-b[0]);
        
        int low = station[0][0];
        int high = station[size-1][1] - station[0][0];
        
        int index = 0;
        
        
        while(low <= high){
            int mid = low + (high-low)/2;
            if(canBePlaced(station,mid)){
                index = mid;
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        System.out.println(index);
    }
}
