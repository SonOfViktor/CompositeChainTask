package com.fairycompany.handling.parser;

import com.fairycompany.handling.entity.TextComponent;
import com.fairycompany.handling.exception.CompositeException;

public interface TextParser {

    TextComponent parse(String text) throws CompositeException;

}
