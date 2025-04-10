package com.fiipractic.Services;

import com.fiipractic.Entity.RequestHistory;
import com.fiipractic.Repository.RequestHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RequestHistoryService {
    @Autowired
    private RequestHistoryRepository requestHistoryRepository;

    public RequestHistory createRequestHistory(RequestHistory requestHistory) {
        return requestHistoryRepository.save(requestHistory);
    }

    public Page<RequestHistory> getRequestHistoriesByUserId(Long userId, Pageable pageable) {
        return requestHistoryRepository.findByUserId(userId, pageable);
    }
}
