package blocks;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private Cell[][] grid;

    public Grid(int height, int width) {
        this.grid = new Cell[height][width];
    }

    public Cell get(int x, int y) {
        return grid[y][x];
    }

    public void deleteLine(int y) {
        for (; y > 0; y--) grid[y] = grid[y - 1];
        grid[0] = new Cell[getWidth()];
    }

    public boolean isFull(int y) {
        for (int x = 0; x < getWidth(); x++)
            if (grid[y][x] == null || grid[y][x].isAlive())
                return false;

        return true;
    }

    public void checkLines() {
        for (int y = 0; y < getHeight(); y++) {
            if (isFull(y)) deleteLine(y);
        }
    }

    public void add(Block block) {
        for (Cell cell : block.getCells()) {
            if (cell.getY() > -1) {
                grid[cell.getY()][cell.getX()] = cell;
            }
        }
    }

    public void remove(Block block) {
        for (Cell cell : block.getCells()) {
            if (cell.getY() > -1) grid[cell.getY()][cell.getX()] = null;
        }
    }

    public int getHeight() {
        return grid.length;
    }

    public int getWidth() {
        return grid[0].length;
    }

    public List<Cell> getCells() {
        List<Cell> cells = new ArrayList<>();
        for (int x = 0; x < getWidth(); x++) for (int y = 0; y < getHeight(); y++) cells.add(grid[y][x]);
        return cells;
    }
}
