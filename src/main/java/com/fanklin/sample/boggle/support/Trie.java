package com.fanklin.sample.boggle.support;

public class Trie {
  private TrieNode root = new TrieNode();

  public void insert(String word) {
    String wordUpper = word.toUpperCase();
    TrieNode curr = root;
    for (int i = 0; i < wordUpper.length(); i++) {
      char currChar = wordUpper.charAt(i);

      TrieNode trieNode = curr.getNode(currChar);
      if (trieNode == null) {
        curr.addNewCharacter(currChar);
      }
      curr = curr.getNode(currChar);
    }
    curr.markAsEndOfWord();
  }

  public TrieNode find(String word) {
    String wordUpper = word.toUpperCase();
    TrieNode curr = root;
    for (int i = 0; i < wordUpper.length(); i++) {
      char currChar = wordUpper.charAt(i);
      curr = curr.getNode(currChar);
      if (curr == null) {
        return null;
      }
    }
    return curr;
  }

  public SearchResult search(String word) {
    TrieNode trieNode = find(word);
    return new SearchResult((trieNode != null) && trieNode.hasChildren(),
            (trieNode != null) && trieNode.isEndOfWord());
  }
}
