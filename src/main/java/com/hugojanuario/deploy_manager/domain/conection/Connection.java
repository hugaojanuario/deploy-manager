package com.hugojanuario.deploy_manager.domain.conection;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "connections")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private String client;

    private ConnectionType connectionType;

    private String idRemoteConnection;
    private String passwordRemoteConnection;

    private String userMachine;
    private String passwordMachine;

    private String userDb;
    private String passwordDb;
}
