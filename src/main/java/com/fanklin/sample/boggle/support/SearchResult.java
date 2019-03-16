package com.fanklin.sample.boggle.support;

public class SearchResult {

  private boolean isPrefix;

  private boolean isWord;

  public SearchResult(boolean isPrefix, boolean isWord) {
    this.isPrefix = isPrefix;
    this.isWord = isWord;
  }

  public boolean isPrefix() {
    return isPrefix;
  }

  public boolean isWord() {
    return isWord;
  }
}
