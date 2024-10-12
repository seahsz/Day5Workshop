package gol;

import java.util.List;

public class Grid {

    private int height;
    private int length;
    private int startX;
    private int startY;
    private Cell[][] board;

    public Grid() {

    }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public int getStartX() { return startX; }
    public void setStartX(int startX) { this.startX = startX; }

    public int getStartY() { return startY;}
    public void setStartY(int startY) { this.startY = startY; }

    public Cell[][] getBoard() {
        return board;
    }

    // Method to get cell
    public Cell getCell(int y, int x) {
        return board[y][x];
    }

    // Method to create empty board of height x length, filled with Cells of "O" = dead
    public void emptyBoard() {
        this.board = new Cell[this.height][this.length];
        for (int rowIdx = 0; rowIdx < board.length; rowIdx++) {
            for (int colIdx = 0; colIdx < board[rowIdx].length; colIdx++) {
                board[rowIdx][colIdx] = new Cell(" ", colIdx, rowIdx);
            }
        }
    }    

    // Method to populate the board after "DATA" is given as a command
    public void initBoard(List<String[]> init) {

        int configRowIdx = 0;

        for (int rowIdx = startY; startY < height; rowIdx++) {
            if (configRowIdx == init.size())        // end the row iteration if it reaches the entire initial configuration
                break;
            String[] currConfigRow = init.get(configRowIdx);
            int configColIdx = 0;

            // First init row
            for (int colIdx = startX; startX < length; colIdx++) {
                if (configColIdx == currConfigRow.length)          // end the column iteration if it reaches end of that line (end of array)
                    break;

                Cell currCell = getCell(rowIdx, colIdx);

                if (currConfigRow[configColIdx].equals(CONSTANTS.DEAD)) {
                    currCell.setAlive(CONSTANTS.DEAD);
                } else {
                    currCell.setAlive(CONSTANTS.ALIVE);
                }
                configColIdx ++;
            }
            configRowIdx ++;
        }
    }

    public void interateBoard() {
        for (int rowIdx = 0; rowIdx < height; rowIdx++) {
            for (int colIdx = 0; colIdx < length; colIdx++) {
                Cell currCell = getCell(rowIdx, colIdx);
                neighCellAlive(currCell);
            }
        }

        for (int rowIdx = 0; rowIdx < height; rowIdx++) {
            for (int colIdx = 0; colIdx < length; colIdx++) {
                Cell currCell = getCell(rowIdx, colIdx);
                int neighAlive = currCell.getNeighAlive();

                // if cell alive & num neighbour is not 2 or 3 --> cell becomes dead
                if (currCell.getAlive().equals(CONSTANTS.ALIVE)) {
                    if (!(neighAlive == 2 || neighAlive == 3)) {
                        currCell.setAlive(CONSTANTS.DEAD);
                    }
                } else {
                    if (neighAlive == 3) {
                        currCell.setAlive(CONSTANTS.ALIVE);
                    }
                }
            }
        }

    }

    // Method that returns number of cells alive
    public void neighCellAlive(Cell currCell) {
        int cellXPos = currCell.getX();
        int cellYPos = currCell.getY();

        int[] range = {-1, 0 , 1};
        int neighAlive = 0;

        for (int rowDisplace : range) {
            for (int colDisplace : range) {
                int refXPos = cellXPos + colDisplace;
                int refYPos = cellYPos + rowDisplace;

                // skip iteration if out of bound or if reference is current cell
                if (refXPos == -1 | refYPos == -1 | refXPos == length | refYPos == height | (refXPos == cellXPos && refYPos == cellYPos))
                    continue;

                Cell refCell = getCell(refYPos, refXPos);
                if (refCell.getAlive().equals(CONSTANTS.ALIVE))
                    neighAlive++;
            }
        }
        currCell.setNeighAlive(neighAlive);
    }

    public void printGrid(int gen) {
        System.out.println("Generation " + gen);
        for (Cell[] row : board) {
            for (Cell cell : row) {
                System.out.printf(cell.getAlive());
            }
            System.out.printf("\n");
        }
        System.out.println();
    }

}
