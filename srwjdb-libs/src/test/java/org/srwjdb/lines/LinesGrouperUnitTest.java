package org.srwjdb.lines;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.srwjdb.items.ParsedTextItem;
import org.srwjdb.parsers.CharsProcessor;
import org.srwjdb.parsers.impl.java.JavaParsedTextItemsFactory;
import org.srwjdb.parsers.impl.java.JavaParserState;

public class LinesGrouperUnitTest {

    @Test
    public void itemsInLineShouldBeGrouped() throws IOException {
        final List<ParsedTextItem> src = getItems("src/test/resources/source-samples/source1.txt");
        final List<List<ParsedTextItem>> actual = new LinesGrouper().apply(src);
        Assert.assertEquals(4, actual.size());
        Assert.assertEquals(6, actual.get(0).size());
        Assert.assertEquals(1, actual.get(1).size());        
        Assert.assertEquals(8, actual.get(2).size());        
        Assert.assertEquals(2, actual.get(3).size());        
    }

    private List<ParsedTextItem> getItems(String filePath) throws IOException {
        final JavaParsedTextItemsFactory parsedTextItemFactory = new JavaParsedTextItemsFactory();
        try (final InputStream inputStream = new FileInputStream(filePath)) {
            new CharsProcessor(JavaParserState.CODE_BLANK, parsedTextItemFactory).loopByChars(inputStream);
        }
        return parsedTextItemFactory.getItems();
    }

}
