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
    AlphabetSupport alphabetSupport = new AlphabetSupport();
    characterSet = String.valueOf(alphabetSupport.getAlphabet(true))
            .chars().mapToObj(e -> (char) e).collect(Collectors.toSet());
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
