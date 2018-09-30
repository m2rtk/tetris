package eu.m2rt.tetris.logic;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private final Cell[][] grid;
    private final List<Cell> cells;

    public Grid(int height, int width) {
        this.grid = new Cell[height][width];
        this.cells = new ArrayList<>();
    }

    public Cell get(int x, int y) {
        if (y < 0 || x < 0) return null;
        return grid[y][x];
    }

    private void deleteLine(int y) {
        for (; y > 0; y--) grid[y] = grid[y - 1];
        grid[0] = new Cell[getWidth()];
    }

    private boolean isFull(int y) {
        for (int x = 0; x < getWidth(); x++)
            if (grid[y][x] == null || grid[y][x].isAlive())
                return false;

        return true;
    }

    int checkLines() {
        int c = 0;
        for (int y = 0; y < getHeight(); y++) {
            if (isFull(y)) {
                deleteLine(y);
                c++;
            }
        }
        return c;
    }

    void add(BlockInstance block) {
        block.cells().filter(cell -> cell.getY() > -1).forEach(this::add);
    }

    private void add(Cell cell) {
        cells.add(cell);
        grid[cell.getY()][cell.getX()] = cell;
    }

    void remove(BlockInstance block) {
        block.cells().filter(cell -> cell.getY() > -1).forEach(this::remove);
    }

    private void remove(Cell cell) {
        cells.remove(cell);
        grid[cell.getY()][cell.getX()] = null;
    }

    public int getHeight() {
        return grid.length;
    }

    public int getWidth() {
        return grid[0].length;
    }

    public List<Cell> getCells() {
        return cells;
    }
}
