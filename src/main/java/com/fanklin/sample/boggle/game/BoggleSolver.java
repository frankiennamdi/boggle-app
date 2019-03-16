package com.fanklin.sample.boggle.game;

import com.fanklin.sample.boggle.support.Dictionary;
import com.fanklin.sample.boggle.support.SearchResult;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.tuple.Pair;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Set;

public class BoggleSolver {

  private static final Logger logger = LoggerFactory.getLogger(BoggleSolver.class);

  private static final List<Pair<Integer, Integer>> CELL_NEIGHBOURS
          = ImmutableList.of(
          pos(-1, -1),
          pos(-1, 0),
          pos(-1, 1),
          pos(0, -1),
          pos(0, 1),
          pos(1, -1),
          pos(1, 0),
          pos(1, 1)
  );

  private final Dictionary dictionary;

  public BoggleSolver(Dictionary dictionary) {
    this.dictionary = dictionary;
  }

  private static Pair<Integer, Integer> pos(int y, int x) {
    return Pair.of(y, x);
  }

  public List<String> solve(char[][] board) {
    Stopwatch stopWatch = Stopwatch.createStarted();
    try {
      List<String> results = Lists.newArrayList();
      Set<Pair<Integer, Integer>> usedCells = Sets.newHashSet();

      int m = board.length;
      if (m == 0) {
        throw new IllegalArgumentException("rows must be a positive integer");
      }

      int n = board[0].length;
      if (n == 0) {
        throw new IllegalArgumentException("columns must be a positive integer");
      }

      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
          if (board[i].length != n) {
            throw new IllegalArgumentException("board must be proportional");
          }
          char currentChar = isValidCharInAlphabet(board[i][j]);
          results.addAll(findWords(board, new StringBuilder(String.valueOf(currentChar)), i, j, usedCells));
        }
      }
      return results;
    } finally {
      logger.info("time: {}", stopWatch);
    }
  }

  private Set<String> findWords(char[][] board, StringBuilder currentString, int y, int x, Set<Pair<Integer, Integer>> usedCells) {
    Set<String> foundWords = Sets.newHashSet();
    for (Pair<Integer, Integer> pos : CELL_NEIGHBOURS) {
      int nextY = pos.getLeft() + y;
      int nextX = pos.getRight() + x;
      Pair<Integer, Integer> nextPos = pos(nextY, nextX);
      if (isValidPosition(board, nextY, nextX) && !usedCells.contains(nextPos)) {
        usedCells.add(nextPos);
        char currentChar = isValidCharInAlphabet(board[nextY][nextX]);
        currentString.append(currentChar);
        SearchResult searchResult = dictionary.findWord(currentString.toString());
        if (searchResult.isWord()) {
          foundWords.add(currentString.toString());
        }
        if (searchResult.isPrefix()) {
          foundWords.addAll(findWords(board, currentString, nextY, nextX, usedCells));
        }
        usedCells.remove(nextPos);
        currentString.setLength(currentString.length() - 1);
      }
    }
    return foundWords;
  }

  private boolean isValidPosition(char[][] board, int y, int x) {
    return y >= 0 && x >= 0 && y < board.length && x < board[y].length;
  }

  private char isValidCharInAlphabet(char character) {
    if (dictionary.characterSet().contains(character)) {
      return character;
    }
    throw new RuntimeException("Board Contains Illegal Characters for the dictionary");
  }
}
