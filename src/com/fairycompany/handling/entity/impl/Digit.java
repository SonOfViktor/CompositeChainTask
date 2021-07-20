package com.fairycompany.handling.entity.impl;

import com.fairycompany.handling.entity.TextComponent;

public class Digit implements TextComponent {
    private char digit;

    public Digit(char letter) {
        this.digit = letter;
    }

    @Override
    public String operation() {
        return Character.toString(digit);
    }
}
