package com.fiipractic.Controllers;

import com.fiipractic.DTO.RequestHistoryDTO;
import com.fiipractic.Entity.RequestHistory;
import com.fiipractic.Services.RequestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("history")
public class RequestHistoryController {
    private final RequestHistoryService requestHistoryService;

    @Autowired
    public RequestHistoryController(RequestHistoryService requestHistoryService) {
        this.requestHistoryService = requestHistoryService;
    }

    @PostMapping
    public ResponseEntity<?> createRequestHistory(@RequestBody RequestHistory requestHistory) {
        try {
            RequestHistory saved = requestHistoryService.createRequestHistory(requestHistory);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return switch (e.getMessage()) {
                case "409" -> new ResponseEntity<>("Conflict", HttpStatus.CONFLICT);
                case "422" -> new ResponseEntity<>("Unprocessable entity", HttpStatus.UNPROCESSABLE_ENTITY);
                default -> new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            };
        }
    }

    @GetMapping
    public ResponseEntity<?> getRequestHistoriesForCurrentUser(Pageable pageable) {
        try {
            List<RequestHistoryDTO> history = requestHistoryService.getRequestHistoriesForCurrentUser(pageable);
            return new ResponseEntity<>(history, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().equals("404")) {
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
