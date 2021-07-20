package com.fairycompany.handling.entity.impl;

import com.fairycompany.handling.entity.TextComponent;

public class Punctuation implements TextComponent {
    private char punctuation;

    public Punctuation(char letter) {
        this.punctuation = letter;
    }

    @Override
    public String operation() {
        return Character.toString(punctuation);
    }
}
