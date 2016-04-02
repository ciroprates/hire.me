package com.bemobi.shortener;

import com.bemobi.shortener.persistence.UrlRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShortenerApplication.class)
@WebAppConfiguration
public class ShortenerApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UrlRepository urlRepository;

    private MockMvc mockMvc;

    private final String TEST_ALIAS = "test";
    private final String TEST_URL = "http://google.com";


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        cleanup();
    }

    @After
    public void cleanup() {
        urlRepository.delete(TEST_ALIAS);
    }

    @Test
    public void creates() throws Exception {
        mockMvc.perform(
                post("/")
                        .param("url", TEST_URL))
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    public void redirects() throws Exception {

    }

    @Test
    public void createsWithTestAlias() throws Exception {
        mockMvc.perform(
                post("/")
                        .param("url", TEST_URL)
                        .param("alias", TEST_ALIAS))
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    public void redirectsTestAlias() throws Exception {
        createsWithTestAlias();
        mockMvc.perform(get("/" + TEST_ALIAS)).andExpect(status().is(HttpStatus.MOVED_TEMPORARILY.value()));
    }




}
