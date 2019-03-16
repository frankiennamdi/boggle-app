package com.fanklin.sample.boggle.controller;

import com.fanklin.sample.boggle.model.BoggleBoard;
import com.fanklin.sample.boggle.model.BoggleResult;
import com.fanklin.sample.boggle.service.SampleBoggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SampleBoggleController.ENDPOINT)
public class SampleBoggleController {

  public static final String ENDPOINT = "/sample";

  private final SampleBoggleService sampleBoggleService;

  @Autowired
  public SampleBoggleController(SampleBoggleService sampleBoggleService) {
    this.sampleBoggleService = sampleBoggleService;
  }

  @PostMapping
  public BoggleResult solveBoard(@RequestBody BoggleBoard boggleBoard) {
    return sampleBoggleService.solveBoard(boggleBoard);
  }
}
