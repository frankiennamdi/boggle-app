package com.fanklin.sample.boggle.service;

import com.fanklin.sample.boggle.game.Boggle;
import com.fanklin.sample.boggle.model.BoggleBoard;
import com.fanklin.sample.boggle.model.BoggleResult;
import com.fanklin.sample.boggle.support.SampleDictionary;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Component;

@Component
public class SampleBoggleService {

  private final Boggle boggle;

  public SampleBoggleService() {
    SampleDictionary sampleDictionary = new SampleDictionary(ImmutableList.of(
            "GEEKS", "FOR", "QUIZ", "GO"
    ));
    boggle = new Boggle(sampleDictionary);
  }

  public BoggleResult solveBoard(BoggleBoard boggleBoard) {
    return boggle.solve(boggleBoard);
  }
}
