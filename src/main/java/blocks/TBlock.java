package blocks;

import java.util.Arrays;

/**
 *  O
 * OOO
 */
public class TBlock extends Block{
    private static final int id = 5;

    public TBlock() {
        cells = Arrays.asList(
                Arrays.asList(
                                             new Cell(1, -2, id),
                        new Cell(0, -1, id), new Cell(1, -1, id), new Cell(2, -1, id)
                ),
                Arrays.asList(
                                             new Cell(1, -2, id),
                                             new Cell(1, -1, id), new Cell(2, -1, id),
                                             new Cell(1,  0, id)
                ),
                Arrays.asList(
                        new Cell(0, -1, id), new Cell(1, -1, id), new Cell(2, -1, id),
                                             new Cell(1,  0, id)
                ),
                Arrays.asList(
                                             new Cell(1, -2, id),
                        new Cell(0, -1, id), new Cell(1, -1, id),
                                             new Cell(1,  0, id)
                )
        );
    }

    @Override
    public int getId() {
        return id;
    }
}
