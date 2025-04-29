package com.fiipractic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiipractic.Controllers.RequestHistoryController;
import com.fiipractic.Entity.RequestHistory;
import com.fiipractic.Entity.User;
import com.fiipractic.Services.RequestHistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityTestConfig.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(RequestHistoryController.class)
public class RequestHistoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestHistoryService requestHistoryService;

    // Testele le-am facut dupa exemplul de pe linkul de pe slide, nu dupa exemplul de pe slide
    @Test
    public void testGetRequestHistory() throws Exception {
        when(requestHistoryService.getRequestHistoriesForCurrentUser(PageRequest.of(0, 10)))
                .thenReturn(Collections.emptyList());
        mockMvc.perform(get("/history"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateRequestHistory() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("defaultuser");
        RequestHistory requestHistory = new RequestHistory(null, "12", "12", false, false, false, 1, "raspuns", user);
        when(requestHistoryService.createRequestHistory(requestHistory)).thenReturn(requestHistory);
        String json = new ObjectMapper().writeValueAsString(requestHistory);
        mockMvc.perform(post("/history").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }
}
