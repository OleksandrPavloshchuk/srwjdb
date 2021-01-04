package org.srwjdb.lines;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.srwjdb.items.ParsedTextItem;

public class BlockCommentSplitterUnitTest {

    @Test
    public void emptyShouldBeEmpty() {
        final List<ParsedTextItem> actualResult = new BlockCommentSplitter().apply(Collections.emptyList());
        Assert.assertNotNull(actualResult);
        Assert.assertTrue(actualResult.isEmpty());
    }

    @Test
    public void listWithoutBlocksShouldNotBeChanged() {
        final List<ParsedTextItem> actualResult = new BlockCommentSplitter().apply(
                Arrays.asList(new ParsedTextItem(ParsedTextItem.Type.CHAR, "'v'"),
                        new ParsedTextItem(ParsedTextItem.Type.KEYWORD, "switch"),
                        new ParsedTextItem(ParsedTextItem.Type.COMMENT, "/* bebebe */"),
                        new ParsedTextItem(ParsedTextItem.Type.STRING, "1")));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.CHAR, "'v'"), actualResult.get(0));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.KEYWORD, "switch"), actualResult.get(1));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.COMMENT, "/* bebebe */"), actualResult.get(2));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.STRING, "1"), actualResult.get(3));
    }

    @Test
    public void multilineBlockCommentsShouldBeSplitted() {
        final List<ParsedTextItem> actualResult = new BlockCommentSplitter().apply(
                Arrays.asList(new ParsedTextItem(ParsedTextItem.Type.NUMBER, "706"),
                        new ParsedTextItem(ParsedTextItem.Type.COMMENT, "/*\n1\n2\n*/")));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.NUMBER, "706"), actualResult.get(0));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.COMMENT, "/*"), actualResult.get(1));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.COMMENT, "\n"), actualResult.get(2));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.COMMENT, "1"), actualResult.get(3));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.COMMENT, "\n"), actualResult.get(4));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.COMMENT, "2"), actualResult.get(5));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.COMMENT, "\n"), actualResult.get(6));
        Assert.assertEquals(new ParsedTextItem(ParsedTextItem.Type.COMMENT, "*/"), actualResult.get(7));
    }

}
