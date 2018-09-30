package eu.m2rt.tetris.logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Block {
    BLOCK(List.of("xx\nxx")),
    LINE(List.of(" x\n x\n x\n x", "    \nxxxx")),
    Z(List.of("xx\n xx", " x\nxx\nx")),
    S(List.of(" xx\nxx", "x\nxx\n x")),
    T(List.of(" x\nxxx", "x\nxx\nx", "xxx\n x", " x\nxx\n x")),
    LL(List.of("x\nxxx", "xx\nx\nx", "xxx\n  x", " x\n x\nxx")),
    RL(List.of("  x\nxxx", "x\nx\nxx", "xxx\nx", "xx\n x\n x"));

    public static final List<Block> all = Stream.of(values()).collect(Collectors.toList());

    public final List<List<Point>> cells;

    Block(List<String> cells) {
        this.cells = cells.stream().map(this::parseRotation).collect(Collectors.toList());
    }

    public BlockInstance instance(Grid grid) {
        return new BlockInstance(grid, this);
    }

    private List<Point> parseRotation(String rotation) {
        List<String> lines = rotation.lines().collect(Collectors.toList());
        List<Point> output = new ArrayList<>();
        String line;
        char c;
        for (int i = 0; i < lines.size(); i++) {
            line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                c = line.charAt(j);
                if (c == 'x') {
                    output.add(new Point(i - 1, j));
                }
            }
        }

        return output;
    }
}
