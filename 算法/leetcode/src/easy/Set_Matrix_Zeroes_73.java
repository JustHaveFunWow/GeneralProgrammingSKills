package easy;

/*Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

        Follow up:
        Did you use extra space?
        A straight forward solution using O(mn) space is probably a bad idea.
        A simple improvement uses O(m + n) space, but still not the best solution.
        Could you devise a constant space solution?*/
public class Set_Matrix_Zeroes_73 {
    public static void setZeroes(int[][] matrix){
        if (matrix == null||matrix[0]==null )
            return;

        //这里主要考虑最终的不开辟额外空间的方式
        //使用数组的第一行、第一列来存储 列、行的置零信息
        //在使用前，用2个变量记录首行首列是否会被置为零
        int m = matrix.length;//行数
        int n = matrix[0].length;//列数
        boolean rowZero = false;
        boolean colZero = false;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i]==0){
                rowZero = true;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] ==0)
                colZero =true;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] ==0){
                    matrix[0][j] =0;
                    matrix[i][0] =0 ;
                }
            }
        }

        //记录归零信息完毕
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] ==0 ||matrix[0][j] ==0)
                    matrix[i][j] =0;
            }
        }
        if (rowZero){
            for (int i = 0; i < n; i++) {
                matrix[0][i] =0;
            }
        }
        if (colZero){
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }

    }

    public static void main(String[] args) {
//        int[][] matrix = new int[][]{{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
        int[][] matrix = new int[][]{{0,1}};
        setZeroes(matrix);

    }
}
