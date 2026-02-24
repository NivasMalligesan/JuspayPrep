import java.util.*;
public class Main
{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		    int count = 0;
		    String str = scan.next();
		    Stack<Character> stack = new Stack<>();
		    
		    for(char i : str.toCharArray()){
		        if(!stack.isEmpty()){
		            char top = stack.peek();
		            if(top == 'X' && i == 'Y' || top == 'Y' && i == 'X'){
		                stack.pop();
		                count++;
		                continue;
		            } 
		        }
		        stack.push(i);
		    }
		    System.out.println("Result : "+ count);
	}
}
