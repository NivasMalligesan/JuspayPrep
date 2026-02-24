import java.util.*;
public class Main
{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();
		char [][] city = new char[2][size];

		for(int i = 0 ; i < 2 ; i++) {
			for(int j = 0 ; j < size ; j++) {
				city[i][j] = scan.next().charAt(0);
			}
		}

		int result = 0;
		for(int i = 0 ; i < 2 ; i++) {
			for(int j = 0 ; j < size ; j++) {
				if(city[i][j] == '.') {
					city[i][j] = 'X';
					int count = countParts(city,size);
					if(count == 3) {
						result++;
					}
			    	city[i][j]  = '.';  
				}
			}
		}

		System.out.println("Result : "+result);
	}

	public static int countParts(char [][] city, int size ) {
		int parts = 0;
		boolean [][] visited = new boolean[2][size];

		for(int i = 0  ; i < 2 ; i++) {
			for(int j = 0 ; j < size ; j++) {
				if(visited[i][j] == false && city[i][j] == '.') {
					dfs(i,j,city,visited);
					parts++;
				}
			}
		}
		return parts;
	}

	public static void dfs(int row, int col,char [][] city, boolean [][] visited) {

		int size = city[0].length;

		visited[row][col] = true;
		int [] dx = {-1,0,1,0};
		int [] dy = {0,1,0,-1};

		for(int i = 0 ; i < 4 ; i++) {
			int newRow = row+dx[i];
			int newCol = col+dy[i];

			if(newRow >= 0 && newRow < 2
			        && newCol >= 0 && newCol < size
			        && visited[newRow][newCol] == false
			        && city[newRow][newCol] == '.') {
				dfs(newRow,newCol,city,visited);
			}

		}

	}
}
