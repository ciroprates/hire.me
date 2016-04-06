package com.bemobi.shortener;

import com.bemobi.shortener.persistence.Url;
import com.bemobi.shortener.persistence.UrlRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    public void shortenWithGeneratedAlias() throws Exception {
        mockMvc.perform(
                post("/")
                        .param("url", TEST_URL))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.alias", is(findLastGeneratedAlias())));
    }

    @Test
    public void redirectsToGeneratedAlias() throws Exception {
        String alias = findLastGeneratedAlias();
        redirects(alias);
    }

    @Test
    public void shortenWithTestAlias() throws Exception {
        mockMvc.perform(
                post("/")
                        .param("url", TEST_URL)
                        .param("alias", TEST_ALIAS))
                .andExpect(status().isCreated());
    }

    @Test
    public void redirectsToTestAlias() throws Exception {
        shortenWithTestAlias();
        redirects(TEST_ALIAS);
    }

    private void redirects(String alias) throws Exception {
        mockMvc.perform(get("/" + alias)).andExpect(status().isFound());
    }

    private String findLastGeneratedAlias() throws Exception {
        String alias = urlRepository.findTopByOrderByCreationDateDesc()
                .map(Url::getAlias)
                .orElseThrow(() -> new Exception("Database is empty"));
        return alias;
    }

    // TODO shortenWithAlreadyRegisteredAlias
    // TODO redirectsToUnknownAlias
    // TODO checkRedirectCount
}
