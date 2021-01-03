package org.srwjdb.parsers.impl.java;

import org.srwjdb.parsers.ParserState;

public enum JavaParserState implements ParserState {

    // from tab or space inside the code:
    CODE_BLANK {
        @Override
        public ParserState next(int c) {
            switch (c) {
                case '/':
                    return COMMENT_START;
                case '"':
                    return STRING;
                case '\'':
                    return CHAR;
                case ' ':
                case '\t':
                case '\n':
                case END_OF_FILE:
                    return CODE_BLANK;
                default:
                    return CODE_LITERAL;
            }
        }

    },
    // from the literal in code:
    CODE_LITERAL {
        @Override
        public ParserState next(int c) {
            switch (c) {
                case '/':
                    return COMMENT_START;
                case '"':
                    return STRING;
                case '\'':
                    return CHAR;
                case ' ':
                case '\t':
                case '\n':
                case END_OF_FILE:
                    return CODE_BLANK;
                default:
                    return CODE_LITERAL;
            }
        }

    },
    // from '/' possibly comment start:
    COMMENT_START {
        @Override
        public ParserState next(int c) {
            switch (c) {
                case '/':
                    return LINE_COMMENT;
                case '*':
                    return BLOCK_COMMENT;
                case ' ':
                case '\t':
                    // There was '/' literal:
                    return CODE_BLANK;
                default:
                    // There was '/' literal:
                    return CODE_LITERAL;
            }
        }
    },
    // from line comment:
    LINE_COMMENT {
        @Override
        public ParserState next(int c) {
            if ('\n' == c || END_OF_FILE == c) {
                return CODE_BLANK;
            } else {
                return this;
            }
        }
    },
    // from multiline block comment:
    BLOCK_COMMENT {
        @Override
        public ParserState next(int c) {
            switch (c) {
                case '*':
                    return ASTERISK;
                default:
                    return BLOCK_COMMENT;
            }
        }

    },
    // from '*' character in the block comment
    ASTERISK {
        @Override
        public ParserState next(int c) {
            switch (c) {
                case '/':
                    return BLOCK_COMMENT_END;
                case '*':
                    return ASTERISK;
                default:
                    return BLOCK_COMMENT;
            }
        }
    },
    // from the multiline block comment end
    BLOCK_COMMENT_END {
        @Override
        public ParserState next(int c) {
            switch (c) {
                case '/':
                    return COMMENT_START;
                default:
                    return CODE_BLANK;
            }
        }
    },
    // inside the string:
    STRING {
        @Override
        public ParserState next(int c) {
            switch (c) {
                case '\\':
                    return PROTECTED_CHAR_IN_STRING;
                case '"':
                    return CODE_BLANK;
                default:
                    return STRING;
            }
        }
    },
    // next char in the string is protected by preceding '\':
    PROTECTED_CHAR_IN_STRING {
        @Override
        public ParserState next(int c) {
            return STRING;
        }
    },
    // inside the char:
    CHAR {
        @Override
        public ParserState next(int c) {
            switch (c) {
                case '\\':
                    return PROTECTED_CHAR_IN_CHAR;
                case '\'':
                    return CODE_BLANK;
                default:
                    return CHAR;
            }
        }
    },
    // next char in the char is protected by preceding '\':
    PROTECTED_CHAR_IN_CHAR {
        @Override
        public ParserState next(int c) {
            return CHAR;
        }
    };

    private static final int END_OF_FILE = -1;

}
