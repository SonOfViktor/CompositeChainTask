package com.fairycompany.handling.entity.impl;

import com.fairycompany.handling.entity.TextComponent;

public class Punctuation implements TextComponent {
    private char punctuation;

    public Punctuation(char letter) {
        this.punctuation = letter;
    }

    // todo equals hashcode

    @Override
    public String toString() {
        return Character.toString(punctuation);
    }
}
