package com.hugojanuario.deploy_manager.domain.connection;

import com.hugojanuario.deploy_manager.domain.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "connections")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false, foreignKey = @ForeignKey(name = "fk_connection_client"))
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConnectionType connectionType;

    private String idRemoteConnection;
    private String passwordRemoteConnection;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
