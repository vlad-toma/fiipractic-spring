package com.fiipractic.Services;

import com.fiipractic.DTO.RequestHistoryDTO;
import com.fiipractic.Entity.GenericMapper;
import com.fiipractic.Entity.RequestHistory;
import com.fiipractic.Repository.RequestHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestHistoryService {
    @Autowired
    private RequestHistoryRepository requestHistoryRepository;

    public RequestHistory createRequestHistory(RequestHistory requestHistory) {
        return requestHistoryRepository.save(requestHistory);
    }

    public List<RequestHistoryDTO> getRequestHistoriesByUserId(Long userId, Pageable pageable) {
        return requestHistoryRepository.findByUserId(userId, pageable).map(GenericMapper::toRequestHistoryDTO).toList();
    }
}
