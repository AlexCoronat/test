package com.tecnical.test.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class History {
    @Id
    @Column(name = "id_history", length = 6)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idHistory;
    @Column(length = 8)
    String type;
    @ManyToOne
    @JoinColumn(name = "id_user")
    User user;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @PrePersist
    private void onCreate(){
        createdAt = LocalDateTime.now();
    }
}
