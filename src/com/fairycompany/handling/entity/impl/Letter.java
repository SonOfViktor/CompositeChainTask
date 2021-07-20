package com.fairycompany.handling.entity.impl;

import com.fairycompany.handling.entity.TextComponent;

public class Letter implements TextComponent {
    private char letter;

    public Letter(char letter) {
        this.letter = letter;
    }

    //todo equals hashcode

    @Override
    public String toString() {
        return Character.toString(letter);
    }
}
