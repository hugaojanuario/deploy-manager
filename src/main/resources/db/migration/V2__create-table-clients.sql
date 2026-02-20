CREATE TABLE clients (
                         id UUID PRIMARY KEY,

                         name VARCHAR(255) NOT NULL,
                         contact VARCHAR(255),
                         city VARCHAR(255),
                         state VARCHAR(255),

                         user_machine_server VARCHAR(255),
                         password_machine_server VARCHAR(255),

                         user_db VARCHAR(255),
                         password_db VARCHAR(255),

                         version_id UUID NOT NULL,

                         active BOOLEAN NOT NULL DEFAULT TRUE,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                         CONSTRAINT fk_client_version
                             FOREIGN KEY (version_id)
                                 REFERENCES versions(id)
);