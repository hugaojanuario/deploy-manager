package com.hugojanuario.deploy_manager.domain.version;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "versions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numberVersion;
    private LocalDateTime dateRelease;
    private String changelog;

    private LocalDateTime createdAt;

}
