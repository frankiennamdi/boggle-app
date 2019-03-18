package com.franklin.sample.boggle.game;

import com.fanklin.sample.boggle.game.Boggle;
import com.fanklin.sample.boggle.model.BoggleBoard;
import com.fanklin.sample.boggle.model.BoggleResult;
import com.fanklin.sample.boggle.support.Dictionary;
import com.fanklin.sample.boggle.support.SampleDictionary;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoggleTest {

  @Test
  public void test3By3Solver() {

    char[][] board = {{'G', 'I', 'Z'},
            {'U', 'E', 'K'},
            {'Q', 'S', 'E'}};

    Dictionary sampleDictionary = new SampleDictionary(ImmutableList.of("GEEKS", "FOR", "QUIZ", "GO"));

    List<String> boardSolution = ImmutableList.of("GEEKS", "QUIZ");
    Boggle boggle = new Boggle(sampleDictionary);
    BoggleResult result = boggle.solve(BoggleBoard.newBoggleBoard(board));
    assertThat(result.getWordsInBoard().size(), is(2));
    assertThat(result.getWordsInBoard(), everyItem(isIn(boardSolution)));
  }

  @Test
  public void test4By4Solver() {

    char[][] board = {
            {'Q', 'S', 'L', 'R'},
            {'M', 'U', 'T', 'E'},
            {'O', 'N', 'A', 'T'},
            {'R', 'F', 'R', 'M'}};

    List<String> boardSolution = ImmutableList.of("EAUS", "ELS", "ELT", "ELUANTS", "ETA", "ELTS");

    Dictionary sampleDictionary = new SampleDictionary(boardSolution);

    Boggle boggle = new Boggle(sampleDictionary);
    BoggleResult result = boggle.solve(BoggleBoard.newBoggleBoard(board));
    assertThat(result.getWordsInBoard().size(), is(boardSolution.size()));
    assertThat(result.getWordsInBoard(), everyItem(isIn(boardSolution)));
  }

  @Test
  public void testMismatchedBoard() {
    char[][] board = {{'G', 'I', 'Z'},
            {'U', 'E'},
            {'Q', 'S', 'E'}};

    Dictionary sampleDictionary = new SampleDictionary(ImmutableList.of("GEEKS", "FOR", "QUIZ", "GO"));

    try {
      Boggle boggle = new Boggle(sampleDictionary);
      boggle.solve(BoggleBoard.newBoggleBoard(board));
      fail();
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage(), is("board must be proportional"));
    }
  }

  @Test
  public void tesBoard_withNotSupportedCharacter() {
    char[][] board = {{'G', 'I', 'Z'},
            {'U', 'E', 'G'},
            {'Q', 'S', 'E'}};

    Dictionary mockDictionary = mock(Dictionary.class);
    when(mockDictionary.characterSet()).thenReturn(ImmutableSet.of('A'));
    Boggle boggle = new Boggle(mockDictionary);
    try {
      boggle.solve(BoggleBoard.newBoggleBoard(board));
      fail();
    } catch (RuntimeException e) {
      assertThat(e.getMessage(), containsString("Board Contains Illegal Characters for the dictionary"));
    }
  }

}
