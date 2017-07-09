package blocks;

import java.util.List;

public abstract class Block {
    List<List<Cell>> cells;
    private int state = 0;

    public void moveDown(Grid grid) {
        grid.remove(this);

        for (int i = 0; i < cells.size(); i++)
            for (Cell cell : cells.get(i))
                cell.moveDown();

        grid.add(this);
    }

    public boolean canMoveDown(Grid grid) {
        for (Cell cell : getCells()) if (!cell.canMoveDown(grid)) return false;
        return true;
    }

    public void moveLeft(Grid grid) {
        grid.remove(this);

        for (int i = 0; i < cells.size(); i++)
            for (Cell cell : cells.get(i))
                cell.moveLeft();

        grid.add(this);
    }

    public boolean canMoveLeft(Grid grid) {
        for (Cell cell : getCells()) if (!cell.canMoveLeft(grid)) return false;
        return true;
    }

    public void moveRight(Grid grid) {
        grid.remove(this);

        for (int i = 0; i < cells.size(); i++)
            for (Cell cell : cells.get(i))
                cell.moveRight();

        grid.add(this);
    }

    public boolean canMoveRight(Grid grid) {
        for (Cell cell : getCells()) if (!cell.canMoveRight(grid)) return false;
        return true;
    }

    public void rotate(Grid grid) {
        grid.remove(this);

        state = (state + 1) % cells.size();

        grid.add(this);
    }

    public boolean canRotate(Grid grid) {
        for (Cell cell : cells.get((state + 1) % cells.size())) {
            if (cell.getY() >= grid.getHeight() || cell.getX() < 0 || cell.getX() >= grid.getWidth()) return false;
            else {// check that new position doesn't touch other blocks
                Cell gridCell = grid.get(cell.getX(), cell.getY());
                if (gridCell != null && !gridCell.isAlive()) return false;
            }
        }
        return true;
    }

    public void kill() {
        for (Cell cell : cells.get(state)) cell.kill();
    }

    public List<Cell> getCells() {
        return cells.get(state);
    }

    public abstract int getId();
}
