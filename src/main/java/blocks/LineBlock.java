package blocks;

import java.util.Arrays;
/**
 * O
 * O
 * O
 * O
 */
public class LineBlock extends Block {
    private static int v = 2;

    public LineBlock() {
        cells = Arrays.asList(
                    Arrays.asList(
                        new Cell(0,-4,v),
                        new Cell(0,-3,v),
                        new Cell(0,-2,v),
                        new Cell(0,-1,v)
                    ),
                    Arrays.asList(
                        new Cell(-1, -1, v), new Cell(0, -1, v),new Cell(1, -1, v),new Cell(2, -1, v)
                    )
        );
    }

    @Override
    public int getId() {
        return 2;
    }
}
