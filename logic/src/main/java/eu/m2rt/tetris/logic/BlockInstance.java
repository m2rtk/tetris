package eu.m2rt.tetris.logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BlockInstance {
    public final Block block;
    private final Grid grid;
    private final List<List<Cell>> cells;
    private int state;

    BlockInstance(Grid grid, Block block) {
        this.grid = grid;
        this.block = block;
        this.cells = copyCells(block);
        this.state = 0;
    }

    void rotate() {
        grid.remove(this);
        state = (state + 1) % cells.size();
        grid.add(this);
    }

    boolean canRotate() {
        for (Cell cell : cells.get((state + 1) % cells.size())) {
            if (cell.getY() >= grid.getHeight() || cell.getX() < 0 || cell.getX() >= grid.getWidth()) return false;
            else {// check that new position doesn't touch other blocks
                Cell gridCell = grid.get(cell.getX(), cell.getY());
                if (gridCell != null && !gridCell.isAlive()) return false;
            }
        }
        return true;
    }

    void kill() {
        cells().forEach(Cell::kill);
    }

    private Stream<Cell> allCells() {
        return cells.stream().flatMap(List::stream);
    }

    Stream<Cell> cells() {
        return cells.get(state).stream();
    }

    boolean canMove(Direction direction) {
        return cells().allMatch(direction.can);
    }

    void move(Direction direction) {
        grid.remove(this);
        allCells().forEach(direction.move);
        grid.add(this);
    }

    private List<List<Cell>> copyCells(Block block) {
        List<List<Cell>> cells = new ArrayList<>();

        for (List<Point> list : block.cells) {
            List<Cell> c = new ArrayList<>();
            for (Point cell : list) {
                c.add(new Cell(grid, block, cell.y, cell.x));
            }
            cells.add(c);
        }

        return cells;
    }
}
