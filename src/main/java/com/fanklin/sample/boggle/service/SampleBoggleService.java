package com.fanklin.sample.boggle.service;

import com.fanklin.sample.boggle.game.Boggle;
import com.fanklin.sample.boggle.model.BoggleBoard;
import com.fanklin.sample.boggle.model.BoggleResult;
import com.fanklin.sample.boggle.support.SampleDictionary;
import com.google.common.io.Resources;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class SampleBoggleService {

  private final Boggle boggle;

  public SampleBoggleService() {
    List<String> dictionaryWords;
    try {
      dictionaryWords = Resources.readLines(Resources.getResource("sample_dictionary.txt"), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    SampleDictionary sampleDictionary = new SampleDictionary(dictionaryWords);
    boggle = new Boggle(sampleDictionary);
  }

  public BoggleResult solveBoard(BoggleBoard boggleBoard) {
    return boggle.solve(boggleBoard);
  }
}
