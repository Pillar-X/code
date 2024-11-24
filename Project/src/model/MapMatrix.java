package model;

public class MapMatrix {
    int[][] matrix;
    private int[][] copymatrix;

    public MapMatrix(int[][] matrix) {
        this.matrix = matrix;
        copymatrix = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){//把matrix按值复制给copyMatrix
            System.arraycopy(matrix[i], 0, copymatrix[i], 0, matrix[0].length);
        }

    }

    public void copy(){
        for(int i=0;i<copymatrix.length;i++){
            System.arraycopy(copymatrix[i], 0, matrix[i], 0, copymatrix[0].length);
        }

    }

    public int getWidth() {
        return this.matrix[0].length;
    }

    public int getHeight() {
        return this.matrix.length;
    }

    public int getId(int row, int col) {
        return matrix[row][col];
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
