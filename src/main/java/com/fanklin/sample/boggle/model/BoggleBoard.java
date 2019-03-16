package com.fanklin.sample.boggle.model;

import com.google.common.base.Joiner;

import java.util.List;
import java.util.stream.Collectors;

public class BoggleBoard {

  private List<List<Character>> board;

  public List<List<Character>> getBoard() {
    return board;
  }

  public void setBoard(List<List<Character>> board) {
    this.board = board;
  }

  public char[][] toCharArray() {
    List<char[]> temp = board.stream().map(list -> Joiner.on("").join(list)).map(String::toCharArray).collect(Collectors.toList());

    char[][] conversion = new char[temp.size()][];
    for (int i = 0; i < temp.size(); i++) {
      conversion[i] = temp.get(i);
    }
    return conversion;
  }
}
