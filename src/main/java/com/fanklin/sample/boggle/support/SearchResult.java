package com.fanklin.sample.boggle.support;

public class SearchResult {

  private final boolean isPrefix;

  private final boolean isWord;

  private final String searchString;

  public SearchResult(String searchString, boolean isPrefix, boolean isWord) {
    this.isPrefix = isPrefix;
    this.isWord = isWord;
    this.searchString = searchString;
  }

  public String getSearchString() {
    return searchString;
  }

  public boolean isPrefix() {
    return isPrefix;
  }

  public boolean isWord() {
    return isWord;
  }
}
