package com.fiipractic.Services;

import com.fiipractic.DTO.RequestHistoryDTO;
import com.fiipractic.Entity.GenericMapper;
import com.fiipractic.Entity.RequestHistory;
import com.fiipractic.Repository.RequestHistoryRepository;
import com.fiipractic.Util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestHistoryService {
    private final RequestHistoryRepository requestHistoryRepository;
    private final SecurityUtil securityUtil;

    @Autowired
    public RequestHistoryService(RequestHistoryRepository requestHistoryRepository, SecurityUtil securityUtil) {
        this.requestHistoryRepository = requestHistoryRepository;
        this.securityUtil = securityUtil;
    }

    public RequestHistory createRequestHistory(RequestHistory requestHistory) throws Exception {
        if (requestHistory.getId() != null) {
            if (requestHistoryRepository.findById(requestHistory.getId()).isPresent()) {
                throw new Exception("409");
            }
        }

        if (requestHistory.getLat() == null || requestHistory.getLon() == null || requestHistory.getResponse() == null) {
            throw new Exception("422");
        }

        if (requestHistory.getUser() == null) {
            requestHistory.setUser(securityUtil.getCurrentUser());
        }

        return requestHistoryRepository.save(requestHistory);
    }

    public List<RequestHistoryDTO> getRequestHistoriesForCurrentUser(Pageable pageable) throws Exception {
        Long userId = securityUtil.getCurrentUser().getId();
        List<RequestHistoryDTO> histories = requestHistoryRepository.findByUserId(userId, pageable)
                .map(GenericMapper::toRequestHistoryDTO)
                .toList();

        if (histories.isEmpty())
            throw new Exception("404");

        return histories;
    }
}