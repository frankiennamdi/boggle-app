package com.fanklin.sample.boggle.support;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SampleDictionary implements Dictionary {

  private final Trie dictionary;

  private Set<Character> characterSet;

  public SampleDictionary(List<String> words) {
    dictionary = new Trie();
    words.forEach(dictionary::insert);
    char[] englishChars = AlphabetSupport.englishAlphabet().getAlphabet(true);
    characterSet = String.valueOf(englishChars).chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
  }

  @Override
  public Set<Character> characterSet() {
    return characterSet;
  }

  @Override
  public SearchResult findWord(String word) {
    return dictionary.search(word);
  }
}
