package com.tecnical.test.dto;

import lombok.Data;

@Data
public class HistoryDTO {
    private Integer idHistory;
    private String type;
    private UserSummaryDTO user;
}
