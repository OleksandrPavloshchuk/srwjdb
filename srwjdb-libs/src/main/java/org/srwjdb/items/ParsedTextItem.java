package org.srwjdb.items;

import java.util.Objects;

/**
 * Item of parsed text
 */
public class ParsedTextItem {

    public enum Type {
        BLANK, COMMENT, STRING, CHAR, KEYWORD, DELIMITER, IDENTIFIER, NUMBER, META
    }

    private final String text;
    private final Type type;

    public ParsedTextItem(Type type, String text) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return this.text;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "{" + type.name() + ": '" + text + "'}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, text);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParsedTextItem other = (ParsedTextItem) obj;
        return Objects.equals(type, other.type)
                && Objects.equals(text, other.text);
    }

}
