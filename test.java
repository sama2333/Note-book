import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        int [][] grid = new int[][]{{1,2,5},{3,2,1}};
        int n = maxValue(grid);
    }


    static int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] db = new int[n+1];
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                db[j] = Math.max(db[j], db[j-1]) + grid[i-1][j-1];
                System.out.println(grid[i-1][j-1]);
                System.out.println(db[n]);
                System.out.println("\n");
            }
        }
        return db[n];
    }
}
