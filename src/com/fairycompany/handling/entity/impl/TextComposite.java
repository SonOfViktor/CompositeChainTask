package com.fairycompany.handling.entity.impl;

import com.fairycompany.handling.entity.CompositeType;
import com.fairycompany.handling.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private List<TextComponent> components = new ArrayList<>();
    private CompositeType type;

    public TextComposite(CompositeType type) {
        this.type = type;
    }

    public List<TextComponent> getComponents() {
        return new ArrayList<>(components);
    }

    public void add(TextComponent component) {
        components.add(component);
    }

    public void remove(TextComponent component) {
        components.remove(component);
    }

    public int size() {
        return components.size();
    }

    public CompositeType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextComposite composite)) return false;
        if (components != null ? !components.equals(composite.components) : composite.components != null) return false;

        return type == composite.type;
    }

    @Override
    public int hashCode() {
        int result = components != null ? components.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (TextComponent element : components) {
            stringBuilder.append(type.getPrefix())
                         .append(element.toString())
                         .append(type.getPostfix());
        }

        return stringBuilder.toString();
    }
}
