package com.fanklin.sample.boggle.support;

import java.util.Set;

public interface Dictionary {

  Set<Character> characterSet();

  SearchResult findWord(String word);
}
