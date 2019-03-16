package com.franklin.sample.boggle.game;

import com.fanklin.sample.boggle.game.BoggleSolver;
import com.fanklin.sample.boggle.support.Dictionary;
import com.fanklin.sample.boggle.support.SampleDictionary;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoggleSolverTest {

  @Test
  public void test3By3Solver() {

    char[][] board = {{'G', 'I', 'Z'},
            {'U', 'E', 'K'},
            {'Q', 'S', 'E'}};

    Dictionary sampleDictionary = new SampleDictionary(ImmutableList.of(
            "GEEKS", "FOR", "QUIZ", "GO"
    ));

    BoggleSolver boggleSolver = new BoggleSolver(sampleDictionary);
    List<String> words = boggleSolver.solve(board);
    assertThat(words.size(), is(2));
    assertThat(words, everyItem(isIn(Lists.newArrayList("GEEKS", "QUIZ"))));
    System.out.println(Arrays.toString(words.toArray()));
  }

  @Test
  public void test4By4Solver() {

    char[][] board = {
            {'Q', 'S', 'L', 'R'},
            {'M', 'U', 'T', 'E'},
            {'O', 'N', 'A', 'T'},
            {'R', 'F', 'R', 'M'}};

    List<String> boardSolution = ImmutableList.of(
            "EAUS", "ELS", "ELT", "ELUANTS", "ETA", "ELTS");

    Dictionary sampleDictionary = new SampleDictionary(boardSolution);

    BoggleSolver boggleSolver = new BoggleSolver(sampleDictionary);
    List<String> words = boggleSolver.solve(board);
    System.out.println(Arrays.toString(words.toArray()));
    assertThat(words.size(), is(boardSolution.size()));
  }

  @Test
  public void testMismatchedBoard() {
    char[][] board = {{'G', 'I', 'Z'},
            {'U', 'E'},
            {'Q', 'S', 'E'}};

    Dictionary sampleDictionary = new SampleDictionary(ImmutableList.of(
            "GEEKS", "FOR", "QUIZ", "GO"
    ));

    try {
      BoggleSolver boggleSolver = new BoggleSolver(sampleDictionary);
      boggleSolver.solve(board);
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
    BoggleSolver boggleSolver = new BoggleSolver(mockDictionary);
    try {
      boggleSolver.solve(board);
      fail();
    } catch (RuntimeException e) {
      assertThat(e.getMessage(), containsString("Board Contains Illegal Characters for the dictionary"));
    }
  }

}
