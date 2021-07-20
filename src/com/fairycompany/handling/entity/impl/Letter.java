package com.fairycompany.handling.entity.impl;

import com.fairycompany.handling.entity.TextComponent;

public class Letter implements TextComponent {
    private char letter;

    public Letter(char letter) {
        this.letter = letter;
    }

    @Override
    public String operation() {
        return Character.toString(letter);
    }
}
