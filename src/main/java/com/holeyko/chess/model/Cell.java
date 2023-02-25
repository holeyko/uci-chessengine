package com.holeyko.chess.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Cell {
    private int digit;
    private char letter;

    private static final int MIN_DIGIT = 1;
    private static final int MAX_DIGIT = 8;
    private static final char MIN_LETTER = 'a';
    private static final char MAX_LETTER = 'h';

    public Cell(char letter, int digit) {
        validLetter(letter);
        validDigit(digit);

        this.letter = letter;
        this.digit = digit;
    }

    public void setDigit(int digit) {
        validDigit(digit);
        this.digit = digit;
    }

    public void setLetter(char letter) {
        validLetter(letter);
        this.letter = letter;
    }

    private void validDigit(int digit) {
        if (digit < MIN_DIGIT || MAX_DIGIT < digit) {
            throw new IllegalArgumentException("Digit must be between %d and %d [digit=%s]"
                    .formatted(MIN_DIGIT, MAX_DIGIT, digit));
        }
    }

    private void validLetter(char letter) {
        if (letter < MIN_LETTER || MAX_LETTER < letter) {
            throw new IllegalArgumentException("Letter must be between %s and %s [letter=%s]"
                    .formatted(MIN_LETTER, MAX_LETTER, letter));
        }
    }

    @Override
    public String toString() {
        return Character.toString(letter) + digit;
    }
}
