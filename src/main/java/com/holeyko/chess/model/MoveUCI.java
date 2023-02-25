package com.holeyko.chess.model;

public class MoveUCI extends AbstractMove {
    public MoveUCI(Cell from, Cell to) {
        super(from, to);
    }

    public MoveUCI(String moveStr) {
        super(moveStr);
    }

    protected Move parseString(String moveStr) {
        if (moveStr.length() != 4) {
            throw new IllegalArgumentException("String of move must have length=4 [moveStr=%s]".formatted(moveStr));
        }
        if (!Character.isDigit(moveStr.charAt(1))) {
            throw new IllegalArgumentException("Second character of move string must be digit [moveStr=%s]".formatted(moveStr));
        }
        if (!Character.isDigit(moveStr.charAt(3))) {
            throw new IllegalArgumentException("Forth character of move string must be digit [moveStr=%s]".formatted(moveStr));
        }

        return new MoveUCI(
                new Cell(moveStr.charAt(0), Character.digit(moveStr.charAt(1), 10)),
                new Cell(moveStr.charAt(0), Character.digit(moveStr.charAt(3), 10))
        );
    }

    @Override
    public String toString() {
        return from.toString() + to.toString();
    }
}
