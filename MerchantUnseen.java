import java.util.*;

public class Main{
    
    public static void printGrid(char [][] grid){
        int size =grid.length;
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        char [][] grid = new char[size][size];
        
        for(int i = 0 ; i < size ; i ++){
            for(int j = 0 ; j < size ; j++){
                grid[i][j] = scan.next().charAt(0);
            }
        }
        
        printGrid(grid);
        
        int [] dx = {-1, -1, -1, 0, 1, 1,  1, 0};
        int [] dy = {-1,  0,  1, 1, 1, 0, -1,-1};
        
        
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                if(grid[i][j] == 'G'){
                    for(int k = 0 ; k < 8 ;k++){
                        int newRow = i + dx[k];
                        int newCol = j + dy[k];
                        while(newRow >=0 && newRow < size && newCol >=0 &&newCol <size){
                            if( grid[newRow][newCol] == '#'){
                                break;
                            }
                            if(grid[newRow][newCol] == 'M'){
                                grid[newRow][newCol] = 'S';
                            }
                            newRow+=dx[k];
                            newCol+=dy[k];
                        }
                    }
                }
            }
        }
        int count = 0;
        for(int i = 0 ; i < size ; i ++){
            for(int j = 0 ; j < size ; j++){
                if(grid[i][j] == 'M'){
                    count++;
                }
            }
        }
        System.out.println(count);
    }}
