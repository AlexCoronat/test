package com.tecnical.test.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistoryDTO {
    private Integer idHistory;
    private String type;
    private UserSummaryDTO user;
    private LocalDateTime createdAt;
}
