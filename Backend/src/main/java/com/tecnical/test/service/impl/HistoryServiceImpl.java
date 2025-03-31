package com.tecnical.test.service.impl;

import com.tecnical.test.model.History;
import com.tecnical.test.repository.HistoryRepository;
import com.tecnical.test.service.HistoryService;
import com.tecnical.test.util.KeyPermissions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final PermissionService permissionService;
    @Override
    public List<History> getAll() {
        if (!permissionService.getKeyPermissions().contains(KeyPermissions.HISTORY_MODULE)) {
            throw new RuntimeException("No tiene permisos para esta operacion");
        }
        return historyRepository.findAll();
    }

    @Override
    public History save(History history) {
        return historyRepository.save(history);
    }
}
