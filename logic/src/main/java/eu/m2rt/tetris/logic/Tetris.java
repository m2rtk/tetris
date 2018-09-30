package eu.m2rt.tetris.logic;

import java.util.Random;

import static eu.m2rt.tetris.logic.Direction.*;

public class Tetris {
    private static final Random rand = new Random();
    private final Grid grid;

    private int score;
    private BlockInstance block, nextBlock;

    public Tetris(Grid grid) {
        this.grid = grid;
        this.score = 0;
        setNextBlock();
    }

    public boolean update() {
        if (block.canMove(DOWN)) {
            block.move(DOWN);
        } else {
            block.kill();
            score += grid.checkLines();
            setNextBlock();
            if (!block.canMove(DOWN)) return false;
        }
        return true;
    }

    private void setNextBlock() {
        if (nextBlock == null) {
            nextBlock = newBlock();
        }
        block = nextBlock;
        grid.add(block);
        for (int i = 0; i < -2 + grid.getWidth() / 2; i++) {
            block.move(RIGHT); // A+
        }
        nextBlock = newBlock();
    }

    public void moveRight() {
        if (block.canMove(RIGHT)) {
            block.move(RIGHT);
        }
    }

    public void moveLeft() {
        if (block.canMove(LEFT)) {
            block.move(LEFT);
        }
    }

    public void rotate() {
        if (block.canRotate()) {
            block.rotate();
        }
    }

    public void down() {
        while (block.canMove(DOWN)) {
            block.move(DOWN);
        }
        update();
    }

    public int getScore() {
        return score;
    }

    public BlockInstance getNextBlock() {
        return nextBlock;
    }

    private BlockInstance newBlock() {
        return Block.values()[rand.nextInt(Block.values().length)].instance(grid);
    }
}
