import java.util.*;
public class Main
{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		String str = scan.next();
		
		TreeSet <Character> allowed = new TreeSet<>();
		for(char i : str.toCharArray()){
		    allowed.add(i);
		}
		
		char smaller = allowed.first();
		char greater = allowed.last();
		
		if(k > n){
		    StringBuilder result = new StringBuilder(str);
		    while(result.length() < k){
		        result.append(smaller);
		    }
		    System.out.println("Result : "+result);
		    return;
		}else{
		    char [] array = str.substring(0,k).toCharArray();
		    for(int i = k-1 ; i >= 0 ; i--){
		        Character next = allowed.higher(array[i]);
		        if(next != null){
		            array[i] = next;
		            for(int j = i +1 ; j < k ; j++){
		                array[j] = smaller;
		            }
		            break;
		        }
		    }
		    System.out.println("Result : "+ new String(array));
		} 
        
	}
}
