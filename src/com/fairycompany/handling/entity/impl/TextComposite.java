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

    public void add(TextComponent component) {
        components.add(component);
    }

    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public String operation() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent element : components) {
            stringBuilder.append(element.operation());
        }

        return stringBuilder.toString();
    }
}
