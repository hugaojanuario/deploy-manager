package com.hugojanuario.deploy_manager.domain.conection;

import com.hugojanuario.deploy_manager.domain.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Enumerated
    private ConnectionType connectionType;

    private String idRemoteConnection;
    private String passwordRemoteConnection;

    private String userMachine;
    private String passwordMachine;

    private String userDb;
    private String passwordDb;
}
