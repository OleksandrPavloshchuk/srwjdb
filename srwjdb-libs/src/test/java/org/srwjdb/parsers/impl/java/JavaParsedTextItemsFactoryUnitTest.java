package org.srwjdb.parsers.impl.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import org.srwjdb.items.ParsedTextItem;
import org.srwjdb.parsers.CharsProcessor;

public class JavaParsedTextItemsFactoryUnitTest {

    @Test
    public void checkSource1() throws IOException {
        final List<ParsedTextItem> actualItems = getItems("src/test/resources/source-samples/source1.txt");
        Assert.assertNotNull(actualItems);
        Assert.assertEquals(20, actualItems.size());
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.BLANK, ""), actualItems.get(0));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.KEYWORD, "package"), actualItems.get(1));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.BLANK, " "), actualItems.get(2));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.IDENTIFIER, "bebebe"), actualItems.get(3));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.DELIMITER, ";"), actualItems.get(4));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.BLANK, ""), actualItems.get(5));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.BLANK, "\n"), actualItems.get(6));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.COMMENT, "// Comment"), actualItems.get(7));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.BLANK, "\n"), actualItems.get(8));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.KEYWORD, "public"), actualItems.get(9));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.BLANK, " "), actualItems.get(10));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.KEYWORD, "class"), actualItems.get(11));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.BLANK, " "), actualItems.get(12));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.IDENTIFIER, "Main"), actualItems.get(13));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.BLANK, " "), actualItems.get(14));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.DELIMITER, "{"), actualItems.get(15));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.BLANK, ""), actualItems.get(16));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.BLANK, "\n"), actualItems.get(17));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.DELIMITER, "}"), actualItems.get(18)); 
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.BLANK, ""), actualItems.get(19)); 
    }

    private List<ParsedTextItem> getItems(String filePath) throws IOException {
        final JavaParsedTextItemsFactory parsedTextItemFactory = new JavaParsedTextItemsFactory();
        try (final InputStream inputStream = new FileInputStream(filePath)) {
            new CharsProcessor(JavaParserState.CODE_BLANK, parsedTextItemFactory).loopByChars(inputStream);
        }
        return parsedTextItemFactory.getItems();
    }

}
