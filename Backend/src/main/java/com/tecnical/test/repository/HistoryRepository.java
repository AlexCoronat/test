package com.tecnical.test.repository;

import com.tecnical.test.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}
