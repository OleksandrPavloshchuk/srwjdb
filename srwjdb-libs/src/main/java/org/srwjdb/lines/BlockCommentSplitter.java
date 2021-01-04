package org.srwjdb.lines;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import org.srwjdb.items.ParsedTextItem;

public class BlockCommentSplitter implements Function<List<ParsedTextItem>, List<ParsedTextItem>> {

    @Override
    public List<ParsedTextItem> apply(List<ParsedTextItem> src) {
        final List<ParsedTextItem> result = new ArrayList<>();
        src.forEach(item -> {
            if (item.getType() == ParsedTextItem.Type.COMMENT) {
                result.addAll(splitCommentByLines(item));
            } else {
                result.add(item);
            }
        });
        return result;
    }

    private List<ParsedTextItem> splitCommentByLines(ParsedTextItem src) {
        final List<ParsedTextItem> result = new ArrayList<>();
        final String[] splitted = src.getText().split("[\n]");
        for( int i=0; i<splitted.length; i++ ) {
            if( i>0 ) {
                result.add(new ParsedTextItem(ParsedTextItem.Type.COMMENT, "\n"));
            }
            result.add(new ParsedTextItem(ParsedTextItem.Type.COMMENT, splitted[i]));
        }
        return result;
    }

}
