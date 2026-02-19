package com.hugojanuario.deploy_manager.domain.version;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "versions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String numberVersion;

    private LocalDate dateRelease;

    @Column(columnDefinition = "TEXT")
    private String changelog;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

}
