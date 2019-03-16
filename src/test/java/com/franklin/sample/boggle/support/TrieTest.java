package com.franklin.sample.boggle.support;

import com.fanklin.sample.boggle.support.SearchResult;
import com.fanklin.sample.boggle.support.Trie;
import com.fanklin.sample.boggle.support.TrieNode;
import com.google.common.collect.ImmutableList;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class TrieTest {

  public Object[] parametersForTestSearch() {
    return new Object[]{
            new Object[]{
                    "the",
                    true
            },
            new Object[]{
                    "these",
                    false
            },
            new Object[]{
                    "their",
                    true
            },
            new Object[]{
                    "thaw",
                    false
            }
    };
  }

  @Test
  @Parameters
  public void testSearch(String wordToFind, boolean expectedToBeFound) {
    List<String> keys = ImmutableList.of("the", "there", "their", "a", "answer", "any", "by", "bye");
    Trie trie = new Trie();
    keys.forEach(trie::insert);
    SearchResult isFound = trie.search(wordToFind);
    assertThat(isFound.isWord(), is(expectedToBeFound));
  }
}
