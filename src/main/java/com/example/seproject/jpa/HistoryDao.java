package com.example.seproject.jpa;

import com.example.seproject.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryDao extends JpaRepository<History, Integer> {

}
