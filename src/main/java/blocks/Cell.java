package blocks;

public class Cell {
    private int x, y;
    private boolean alive;
    private int value;

    public Cell(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.alive = true;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        this.alive = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    public boolean canMoveDown(Grid grid) {
        if (y < 0) return true;
        return (y + 1 < grid.getHeight()) && (grid.get(x, y + 1) == null || grid.get(x, y + 1).alive);
    }

    public void moveDown() {
        y++;
    }

    public boolean canMoveLeft(Grid grid) {
        if (y < 0) return true;
        return x > 0 && (grid.get(x - 1, y) == null || grid.get(x - 1, y).alive);
    }

    public void moveLeft() {
        x--;
    }

    public boolean canMoveRight(Grid grid) {
        if (y < 0) return true;
        return x + 1 < grid.getWidth() && (grid.get(x + 1, y) == null || grid.get(x + 1, y).alive);
    }

    public void moveRight() {
        x++;
    }

    @Override
    public String toString() {
        return alive ? "A" : "D";
    }
}
