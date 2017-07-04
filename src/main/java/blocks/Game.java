package blocks;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {
    private static final Random rand = new Random();
    private Grid grid;
    private List<Class> blocks;

    private int score;

    private State state;
    private Block block;

    private enum State {
        NEW, OK
    }

    public Game(Grid grid) {
        this.grid = grid;
        this.blocks = Arrays.asList(BlockBlock.class, LineBlock.class, LLBlock.class, RLBlock.class, SBlock.class, TBlock.class, ZBlock.class);
        this.state = State.NEW;
    }

    public void update() {
        switch (state) {
            case NEW:
                block = construct(blocks.get(rand.nextInt(blocks.size())));
                grid.add(block);
                state = State.OK;
                break;

            case OK:
                boolean hit = false;
                for (Cell cell : block.getCells()) {
                    System.out.println(cell.canMoveDown(grid));
                    if (!cell.canMoveDown(grid)) {
                        hit = true;
                        break;
                    }
                }
                if (hit) {
                    block.kill();
                    grid.checkLines();
                    // add a new block
                    block = construct(blocks.get(rand.nextInt(blocks.size())));
                    grid.add(block);
                } else {
                    block.moveDown(grid);
                }
                break;

            default:
                throw new RuntimeException();
        }
    }

    public void moveRight() {
        block.moveRight(grid);
    }

    public void moveLeft() {
        block.moveLeft(grid);
    }

    public void rotate() {
        block.rotate(grid);
    }

    private static Block construct(Class cls) {
        try {
            return (Block) cls.getDeclaredConstructors()[0].newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Dude pls");
        }
    }
}
