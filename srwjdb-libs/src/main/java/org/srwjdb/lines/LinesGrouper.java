package org.srwjdb.lines;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.srwjdb.items.ParsedTextItem;

public class LinesGrouper implements Function<List<ParsedTextItem>, List<List<ParsedTextItem>>> {

    @Override
    public List<List<ParsedTextItem>> apply(List<ParsedTextItem> src) {
        final List<List<ParsedTextItem>> result = new ArrayList<>();
        final List<ParsedTextItem> accumulator = new ArrayList<>();
        src.forEach(item -> {
            if (item.getText().equals("\n")) {
                if (!accumulator.isEmpty()) {
                    result.add(new ArrayList<>(accumulator));
                    accumulator.clear();
                }
            } else {
                accumulator.add(item);
            }
        });
        if (!accumulator.isEmpty()) {
            result.add(new ArrayList<>(accumulator));
        }
        return result;
    }

}
