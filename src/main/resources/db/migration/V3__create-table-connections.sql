CREATE TABLE connections (
                             id UUID PRIMARY KEY,

                             client_id UUID NOT NULL,
                             connection_type VARCHAR(50) NOT NULL,

                             id_remote_connection VARCHAR(255),
                             password_remote_connection VARCHAR(255),

                             user_machine VARCHAR(255),
                             password_machine VARCHAR(255),

                             user_db VARCHAR(255),
                             password_db VARCHAR(255),

                             created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                             CONSTRAINT fk_connection_client
                                 FOREIGN KEY (client_id)
                                     REFERENCES clients(id)
                                     ON DELETE CASCADE
);
