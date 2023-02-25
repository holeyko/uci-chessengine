package com.holeyko.chess.model;

public abstract class AbstractMove implements Move {

    protected final Cell from;
    protected final Cell to;

    public AbstractMove(Cell from, Cell to) {
        this.from = from;
        this.to = to;
    }

    public AbstractMove(String moveStr) {
        Move parsedMove = parseString(moveStr);
        this.from = parsedMove.getFrom();
        this.to = parsedMove.getTo();
    }

    protected abstract Move parseString(String moveStr);

    @Override
    public Cell getFrom() {
        return from;
    }

    @Override
    public Cell getTo() {
        return to;
    }
}
