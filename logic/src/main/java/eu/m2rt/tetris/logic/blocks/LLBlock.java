package eu.m2rt.tetris.logic.blocks;

import java.util.Arrays;

/**
 *  O
 *  O
 * OO
 */
public class LLBlock extends Block {
    private static final int id = 6;

    public LLBlock() {
        cells = Arrays.asList(
                Arrays.asList(
                        new Cell(0, -2, id),
                        new Cell(0, -1, id), new Cell(1, -1, id), new Cell(2, -1, id)
                ),
                Arrays.asList(
                        new Cell(1, -2, id), new Cell(2, -2, id),
                        new Cell(1, -1, id),
                        new Cell(1,  0, id)
                ),
                Arrays.asList(
                        new Cell(0, -1, id), new Cell(1, -1, id), new Cell(2, -1, id),
                                                                  new Cell(2,  0, id)
                ),
                Arrays.asList(
                                             new Cell(1, -2, id),
                                             new Cell(1, -1, id),
                        new Cell(0,  0, id), new Cell(1,  0, id)
                )
        );
    }

    @Override
    public int getId() {
        return id;
    }
}

