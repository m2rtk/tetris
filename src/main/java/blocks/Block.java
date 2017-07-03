package blocks;

import java.util.List;

public abstract class Block {
    List<List<Cell>> cells;
    int state;

    public void moveDown(Grid grid) {
        for (Cell cell : getCells()) if (!cell.canMoveDown(grid)) return;
        grid.remove(this);
//        for (Cell cell : cells.get(state)) cell.moveDown();
        for (int i = 0; i < cells.size(); i++) for (Cell cell : cells.get(i)) cell.moveDown();
        grid.add(this);
    }

    public void moveLeft(Grid grid) {
        for (Cell cell : getCells()) if (!cell.canMoveLeft(grid)) return;
        grid.remove(this);
//        for (Cell cell : cells.get(state)) cell.moveLeft();
        for (int i = 0; i < cells.size(); i++) for (Cell cell : cells.get(i)) cell.moveLeft();
        grid.add(this);
    }

    public void moveRight(Grid grid) {
        for (Cell cell : getCells()) if (!cell.canMoveRight(grid)) return;
        grid.remove(this);
//        for (Cell cell : cells.get(state)) cell.moveRight();
        for (int i = 0; i < cells.size(); i++) for (Cell cell : cells.get(i)) cell.moveRight();
        grid.add(this);
    }

    public void rotate(Grid grid) {
        System.out.println("rotate state before: " + state);
        int newstate = (state + 1) % cells.size();
        System.out.println("rotate state after:  " + newstate);
        for (Cell cell : cells.get(newstate)) {
            // check that new position is in bounds
            System.out.println("cell.getY() >= grid.getHeight() = " + (cell.getY() >= grid.getHeight()));
            System.out.println("cell.getX() < 0 = " + (cell.getX() < 0));
            System.out.println("cell.getX() >= grid.getWidth() = " + (cell.getX() >= grid.getWidth()));
            if (cell.getY() >= grid.getHeight() || cell.getX() < 0 || cell.getX() >= grid.getWidth()) return;
            else {// check that new position doesn't touch other blocks

                Cell gridCell = grid.get(cell.getX(), cell.getY());
                System.out.println("gridCell != null = " + gridCell != null);
                if (gridCell != null) System.out.println("!gridCell.isAlive() = " + !gridCell.isAlive());
                if (gridCell != null && !gridCell.isAlive()) return;
            }
        }

        grid.remove(this);

        state = newstate;

        grid.add(this);
    }

    public void kill() {
        for (Cell cell : cells.get(state)) cell.kill();
    }

    public List<Cell> getCells() {
        return cells.get(state);
    }
}
