package com.franklin.sample.boggle.controller;

import com.fanklin.sample.boggle.Application;
import com.fanklin.sample.boggle.controller.SampleBoggleController;
import com.fanklin.sample.boggle.model.BoggleBoard;
import com.fanklin.sample.boggle.model.BoggleResult;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SampleBoggleControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true).build();
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.getObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    converter.setSupportedMediaTypes(ImmutableList.of(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
    restTemplate.getRestTemplate().setMessageConverters(Arrays.asList(converter, new FormHttpMessageConverter()));
  }

  @Test
  public void testBoggleController_withRestTemplate() {

    List<List<Character>> board = ImmutableList.of(
            newList('G', 'I', 'Z'),
            newList('U', 'E', 'K'),
            newList('Q', 'S', 'E'));

    BoggleBoard boggleBoard = new BoggleBoard();
    boggleBoard.setBoard(board);
    HttpEntity<BoggleBoard> request = new HttpEntity<>(boggleBoard);
    ResponseEntity<BoggleResult> rateResponse = restTemplate.exchange(SampleBoggleController.ENDPOINT,
            HttpMethod.POST, request, BoggleResult.class);
    BoggleResult boggleResult = rateResponse.getBody();
    assertThat(boggleResult, notNullValue());
    assertThat(boggleResult.getWordsInBoard().size(), is(2));
    assertThat(boggleResult.getWordsInBoard(), everyItem(isIn(Lists.newArrayList("GEEKS", "QUIZ"))));
  }


  @Test
  public void testBoggleController_withJson() throws Exception {
    String boggleBoardJson = "{\"board\":[[\"G\",\"I\",\"Z\"],[\"U\",\"E\",\"K\"],[\"Q\",\"S\",\"E\"]]}";
    this.mockMvc.perform(post(SampleBoggleController.ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON)
            .content(boggleBoardJson))
            .andExpect(status().isOk())
            .andExpect(content().json("{\"wordsInBoard\":[\"GEEKS\",\"QUIZ\"]}"));
  }

  private List<Character> newList(char... chars) {
    return Chars.asList(chars);
  }
}
