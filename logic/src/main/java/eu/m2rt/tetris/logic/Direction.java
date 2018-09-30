package eu.m2rt.tetris.logic;

import java.util.function.Consumer;
import java.util.function.Predicate;

enum Direction {
    LEFT(
            Cell::canMoveLeft,
            Cell::moveLeft
    ),
    RIGHT(
            Cell::canMoveRight,
            Cell::moveRight
    ),
    DOWN(
            Cell::canMoveDown,
            Cell::moveDown
    );

    public final Predicate<Cell> can;
    public final Consumer<Cell> move;

    Direction(Predicate<Cell> can, Consumer<Cell> move) {
        this.can = can;
        this.move = move;
    }
}
