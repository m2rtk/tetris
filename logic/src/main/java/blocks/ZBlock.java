package blocks;

import java.util.Arrays;

/**
 * OO
 *  OO
 */
public class ZBlock extends Block {
    private static int id = 3;

    public ZBlock() {
        cells = Arrays.asList(
                Arrays.asList(
                        new Cell(0,-2, id), new Cell(1,-2, id),
                                            new Cell(1,-1, id), new Cell(2,-1, id)
                ),
                Arrays.asList(
                                            new Cell(1,-3, id),
                        new Cell(0, -2, id),new Cell(1, -2, id),
                        new Cell(0, -1, id)
                )
        );
    }

    @Override
    public int getId() {
        return id;
    }
}
