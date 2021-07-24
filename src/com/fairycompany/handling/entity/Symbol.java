package com.fairycompany.handling.entity;

import java.util.List;

public abstract class Symbol implements TextComponent {
    private char symbol;

    public Symbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public List<TextComponent> getComponents() {
        throw new UnsupportedOperationException("Method getComponents is unsupported for class " + getClass());
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Method add is unsupported for class " + getClass());
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("Method remove is unsupported for class " + getClass());
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Method size is unsupported for class " + getClass());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Symbol symbol1)) return false;

        return symbol == symbol1.symbol;
    }

    @Override
    public int hashCode() {
        return symbol;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}
