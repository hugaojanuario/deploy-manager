package com.hugojanuario.deploy_manager.domain.client;

import com.hugojanuario.deploy_manager.domain.version.Version;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String state;
    private String contact;

    @ManyToOne
    @JoinColumn(name = "version_id")
    private Version actualVersion;

    private LocalDateTime createdAt;

    
}
