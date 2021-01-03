package org.srwjdb.parsers;

import java.util.Collection;

/**
 * Information about the language
 */
public interface LanguageInfo {
    Collection<Integer> getDelimiters();
    
    Collection<String> getKeywords();    
}
