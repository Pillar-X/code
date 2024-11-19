package model;//用于输出矩阵的参数和大小

/**
 * This class is to record the map of one game. For example:
 * matrix =
 * {1, 1, 1, 1, 1, 1},
 * {1, 0, 0, 0, 0, 1},
 * {1, 0, 20, 12, 0, 1},
 * {1, 0, 10, 2, 0, 1},
 * {1, 1, 1, 1, 1, 1}
 * The Unit digit number cannot be changed during one game.
 * 1 represents the wall
 * 0 represents the free space
 * 2 represents the target location
 * The Then digit number can be changed during one game.
 * Ten digit 1 represents the box
 * Ten digit 2 represents the hero/player
 * So that 12 represents a box on the target location and 22 represents the player on the target location.
 */
public class MapMatrix {
    int[][] matrix;


    public MapMatrix(int[][] matrix) {
        this.matrix = matrix;

    }

    public int getWidth() {
        return this.matrix[0].length;
    }//长

    public int getHeight() {
        return this.matrix.length;
    }//高

    public int getId(int row, int col) {
        return matrix[row][col];
    }//输入位置，返回矩阵此处的值

    public int[][] getMatrix() {
        return matrix;
    }//返回矩阵
}
