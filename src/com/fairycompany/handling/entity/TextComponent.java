package com.fairycompany.handling.entity;

import java.util.List;

public interface TextComponent {

    List<TextComponent> getComponents();

    void add(TextComponent component);

    void remove(TextComponent component);

    int size();
}
