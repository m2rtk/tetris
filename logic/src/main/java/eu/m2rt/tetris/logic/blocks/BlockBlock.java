package eu.m2rt.tetris.logic.blocks;

import java.util.Arrays;
/**
 *  OO
 *  OO
 */
public class BlockBlock extends Block {
    private static int v = 1;

    public BlockBlock() {
        cells = Arrays.asList(
                    Arrays.asList(
                        new Cell(0,-2,v), new Cell(1,-2,v),
                        new Cell(0,-1,v), new Cell(1,-1,v)
                    )
        );
    }

    @Override
    public int getId() {
        return 1;
    }
}
