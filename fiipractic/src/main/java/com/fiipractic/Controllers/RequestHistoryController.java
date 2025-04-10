package com.fiipractic.Controllers;

import com.fiipractic.Entity.RequestHistory;
import com.fiipractic.Services.RequestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("history")
public class RequestHistoryController {
    @Autowired
    private RequestHistoryService requestHistoryService;

    @PostMapping
    public RequestHistory createRequestHistory(@RequestBody RequestHistory requestHistory) {
        return requestHistoryService.createRequestHistory(requestHistory);
    }

    @GetMapping("/{userId}")
    public Page<RequestHistory> getRequestHistoriesByUserId(@PathVariable Long userId, Pageable pageable) {
        return requestHistoryService.getRequestHistoriesByUserId(userId, pageable);
    }
}
