package com.fanklin.sample.boggle.service;

import com.fanklin.sample.boggle.model.BoggleBoard;
import com.fanklin.sample.boggle.model.BoggleResult;
import com.fanklin.sample.boggle.game.BoggleSolver;
import com.fanklin.sample.boggle.support.SampleDictionary;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Component;

@Component
public class SampleBoggleService {

  private final BoggleSolver boggleSolver;

  public SampleBoggleService() {
    SampleDictionary sampleDictionary = new SampleDictionary(ImmutableList.of(
            "GEEKS", "FOR", "QUIZ", "GO"
    ));
    boggleSolver = new BoggleSolver(sampleDictionary);
  }

  public BoggleResult solveBoard(BoggleBoard boggleBoard) {
    BoggleResult boggleResult = new BoggleResult();
    boggleResult.setWordsInBoard(boggleSolver.solve(boggleBoard.toCharArray()));
    return boggleResult;
  }
}
