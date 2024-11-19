package model;

public enum Direction {
    LEFT(0, -1), UP(-1, 0), RIGHT(0, 1), DOWN(1, 0),
    ;
    private final int row;//一旦在构造函数中被赋值就不可改变
    private final int col;

    Direction(int row, int col) {//构造函数用于给自己的上下左右变量传值
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}