package com.fanklin.sample.boggle.support;

import java.util.LinkedHashMap;
import java.util.Map;

public class TrieNode {
  private boolean isLeaf = false;
  private Map<Character, TrieNode> nodes = new LinkedHashMap<>();

  TrieNode getNode(Character character) {
    return nodes.get(character);
  }

  void addNewCharacter(Character character) {
    nodes.put(character, new TrieNode());
  }

  public boolean isEndOfWord() {
    return isLeaf;
  }

  void markAsEndOfWord() {
    isLeaf = true;
  }

  boolean hasChildren() {
    return nodes.size() > 0;
  }
}