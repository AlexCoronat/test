package com.tecnical.test.mapper;

import com.tecnical.test.dto.HistoryDTO;
import com.tecnical.test.model.History;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = PermissionMapper.class)
public interface HistoryMapper {
    HistoryDTO toHistoryDTO(History history);
    History toHistory(HistoryDTO historyDTO);
    List<HistoryDTO> toHistoryDTOList(List<History> historyList);
}
