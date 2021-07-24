package com.fairycompany.handling.entity.impl;

import com.fairycompany.handling.entity.Symbol;
import com.fairycompany.handling.entity.TextComponent;

public class Letter extends Symbol implements TextComponent {

    public Letter(char letter) {
        super(letter);
    }
}
