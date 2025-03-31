package com.tecnical.test.service;

import com.tecnical.test.model.History;

import java.util.List;

public interface HistoryService {
    List<History> getAll();
    History save(History history);
}
