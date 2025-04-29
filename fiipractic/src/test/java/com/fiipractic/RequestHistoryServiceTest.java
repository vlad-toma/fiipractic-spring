package com.fiipractic;

import com.fiipractic.DTO.RequestHistoryDTO;
import com.fiipractic.Entity.RequestHistory;
import com.fiipractic.Entity.User;
import com.fiipractic.Repository.RequestHistoryRepository;
import com.fiipractic.Services.RequestHistoryService;
import com.fiipractic.Util.SecurityUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RequestHistoryServiceTest {
    @Mock
    private RequestHistoryRepository requestHistoryRepository;

    @Mock
    private SecurityUtil securityUtil;

    @InjectMocks
    private RequestHistoryService requestHistoryService;

    // Testele le-am facut dupa exemplul de pe linkul de pe slide, nu dupa exemplul de pe slide
    @Test
    public void testGetRequestHistory() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("defaultuser");
        RequestHistory requestHistory1 = new RequestHistory(1L, "12", "12", false, false, false, 1, "raspuns1", user);
        RequestHistory requestHistory2 = new RequestHistory(2L, "14", "14", false, false, false, 1, "raspuns2", user);

        when(securityUtil.getCurrentUser()).thenReturn(user);
        Page<RequestHistory> page = new PageImpl<>(Arrays.asList(requestHistory1, requestHistory2));
        when(requestHistoryRepository.findByUserId(1L, PageRequest.of(0, 2))).thenReturn(page);

        List<RequestHistoryDTO> requestHistoryList = requestHistoryService.getRequestHistoriesForCurrentUser(PageRequest.of(0, 2));

        assertEquals(requestHistoryList.size(), 2);
        assertEquals(requestHistoryList.get(0).getId(), requestHistory1.getId());
        assertEquals(requestHistoryList.get(1).getId(), requestHistory2.getId());
        assertEquals(requestHistoryList.get(0).getResponse(), requestHistory1.getResponse());
        assertEquals(requestHistoryList.get(1).getResponse(), requestHistory2.getResponse());
    }
}
