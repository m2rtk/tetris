package eu.m2rt.tetris.logic;

public class Cell {
    public final Block block;
    private final Grid grid;

    private int x, y;
    private boolean alive;

    Cell(Grid grid, Block block, int x, int y) {
        this.grid = grid;
        this.block = block;
        this.x = x;
        this.y = y;
        this.alive = true;
    }

    boolean isAlive() {
        return alive;
    }

    void kill() {
        this.alive = false;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    boolean canMoveDown() {
        if (y < 0) return true;
        return (y + 1 < grid.getHeight()) && (grid.get(x, y + 1) == null || grid.get(x, y + 1).alive);
    }

    void moveDown() {
        y++;
    }

    boolean canMoveLeft() {
        if (y < 0) return true;
        return x > 0 && (grid.get(x - 1, y) == null || grid.get(x - 1, y).alive);
    }

    void moveLeft() {
        x--;
    }

    boolean canMoveRight() {
        if (y < 0) return true;
        return x + 1 < grid.getWidth() && (grid.get(x + 1, y) == null || grid.get(x + 1, y).alive);
    }

    void moveRight() {
        x++;
    }

    @Override
    public String toString() {
        return alive ? "A" : "D";
    }
}
