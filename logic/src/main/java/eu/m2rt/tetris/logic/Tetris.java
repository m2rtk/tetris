package eu.m2rt.tetris.logic;

import eu.m2rt.tetris.logic.blocks.*;

import java.util.*;

public class Tetris {
    private static final Random rand = new Random();

    private static final List<Class> blocks = Arrays.asList(
            BlockBlock.class, LineBlock.class,
            LLBlock.class, RLBlock.class,
            SBlock.class, TBlock.class,
            ZBlock.class
    );

    private int score;
    private Grid grid;
    private Block block, nextBlock;

    public Tetris(Grid grid) {
        this.grid  = grid;
        this.score = 0;
        setNextBlock();
    }

    public boolean update() {
        if (block.canMoveDown(grid)) {
            block.moveDown(grid);
        } else {
            block.kill();
            score += grid.checkLines();
            setNextBlock();
            if (!block.canMoveDown(grid)) return false;
        }
        return true;
    }

    private void setNextBlock() {
        if (nextBlock == null) nextBlock = construct(blocks.get(rand.nextInt(blocks.size())));
        block = nextBlock;
        grid.add(block);
        block.moveDown(grid);
        for (int i = 0; i < -2 + grid.getWidth() / 2; i++) block.moveRight(grid); // A+
        nextBlock = construct(blocks.get(rand.nextInt(blocks.size())));
    }

    public void moveRight() {
        if (block.canMoveRight(grid)) {
            block.moveRight(grid);
        }
    }

    public void moveLeft() {
        if (block.canMoveLeft(grid)) {
            block.moveLeft(grid);
        }
    }

    public void rotate() {
        if (block.canRotate(grid)) {
            block.rotate(grid);
        }
    }

    public void down() {
        while (block.canMoveDown(grid)) {
            block.moveDown(grid);
        }
        update();
    }

    public int getScore() {
        return score;
    }

    public Block getNextBlock() {
        return nextBlock;
    }

    private static Block construct(Class cls) {
        try {
            return (Block) cls.getDeclaredConstructors()[0].newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Dude pls");
        }
    }
}
