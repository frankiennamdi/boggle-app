package com.franklin.sample.boggle.game;

import com.fanklin.sample.boggle.game.BoggleSolver;
import com.fanklin.sample.boggle.support.SampleDictionary;
import com.google.common.collect.ImmutableList;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;

public class BoggleSolverTest {

  @Test
  public void test3By3Solver() {

    char[][] board = {{'G', 'I', 'Z'},
            {'U', 'E', 'K'},
            {'Q', 'S', 'E'}};

    SampleDictionary sampleDictionary = new SampleDictionary(ImmutableList.of(
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

    SampleDictionary sampleDictionary = new SampleDictionary(boardSolution);

    BoggleSolver boggleSolver = new BoggleSolver(sampleDictionary);
    List<String> words = boggleSolver.solve(board);
    System.out.println(Arrays.toString(words.toArray()));
    assertThat(words.size(), is(boardSolution.size()));
  }
}
