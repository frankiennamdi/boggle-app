package com.franklin.sample.boggle.support;

import com.fanklin.sample.boggle.support.SampleDictionary;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SampleDictionaryTest {

  @Test
  public void testCanFindWordInDictionary() {
    SampleDictionary sampleDictionary = new SampleDictionary(ImmutableList.of("GEEKS", "FOR", "QUIZ", "GO"));
    assertThat(sampleDictionary.findWord("GEEKS").isWord(), is(true));
    assertThat(sampleDictionary.findWord("GE").isWord(), is(false));
    assertThat(sampleDictionary.findWord("QUIZ").isWord(), is(true));
  }
}
