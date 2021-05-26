package com.Mastermind;

public class Token {

    private String color;
    private int position;

    public Token(String color, int position) {
        this.color = color;
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public int getPosition() {
        return position;
    }

    public String toString() {
        return "Color: " + color + " Position: " + position;
    }

    public boolean equals(Token other) {
        if (this.getColor() == other.getColor() && this.getPosition() == other.getPosition()) {
            return true;
        }
        return false;
    }
}
