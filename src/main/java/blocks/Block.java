package blocks;

import java.util.List;

public abstract class Block {
    List<List<Cell>> cells;
    private int state = 0;

    public void moveDown(Grid grid) {
        for (Cell cell : getCells()) if (!cell.canMoveDown(grid)) return;
        grid.remove(this);

        for (int i = 0; i < cells.size(); i++)
            for (Cell cell : cells.get(i))
                cell.moveDown();

        grid.add(this);
    }

    public void moveLeft(Grid grid) {
        for (Cell cell : getCells()) if (!cell.canMoveLeft(grid)) return;
        grid.remove(this);

        for (int i = 0; i < cells.size(); i++)
            for (Cell cell : cells.get(i))
                cell.moveLeft();

        grid.add(this);
    }

    public void moveRight(Grid grid) {
        for (Cell cell : getCells()) if (!cell.canMoveRight(grid)) return;
        grid.remove(this);

        for (int i = 0; i < cells.size(); i++)
            for (Cell cell : cells.get(i))
                cell.moveRight();

        grid.add(this);
    }

    public void rotate(Grid grid) {
        int newState = (state + 1) % cells.size();
        for (Cell cell : cells.get(newState)) {
            // check that new position is in bounds
            if (cell.getY() >= grid.getHeight() || cell.getX() < 0 || cell.getX() >= grid.getWidth()) return;
            else {// check that new position doesn't touch other blocks

                Cell gridCell = grid.get(cell.getX(), cell.getY());
                if (gridCell != null) System.out.println("!gridCell.isAlive() = " + !gridCell.isAlive());
                if (gridCell != null && !gridCell.isAlive()) return;
            }
        }

        grid.remove(this);

        state = newState;

        grid.add(this);
    }

    public void kill() {
        for (Cell cell : cells.get(state)) cell.kill();
    }

    public List<Cell> getCells() {
        return cells.get(state);
    }

    public abstract int getId();
}
