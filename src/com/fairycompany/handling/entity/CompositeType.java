package com.fairycompany.handling.entity;

public enum CompositeType {
    PARAGRAPH("\t", "\b\n\r"),
    SENTENCE(),
    LEXEME(" "),
    WORD(""),
    SYMBOL();

    private String prefix = "";
    private String postfix = "";

    CompositeType() {
    }

    CompositeType(String postfix) {
        this.postfix = postfix;
    }

    CompositeType(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostfix() {
        return postfix;
    }
}
