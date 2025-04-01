package com.tecnical.test.controller;

import com.tecnical.test.dto.HistoryDTO;
import com.tecnical.test.mapper.HistoryMapper;
import com.tecnical.test.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class HistoryController {
    private final HistoryService historyService;
    private final HistoryMapper historyMapper;
    @GetMapping("")
    public ResponseEntity<List<HistoryDTO>> getHistory(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(historyMapper.toHistoryDTOList(historyService.getAll()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
