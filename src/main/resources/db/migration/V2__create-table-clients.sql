CREATE TABLE clients (
                         id UUID PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         city VARCHAR(150),
                         state VARCHAR(100),
                         contact VARCHAR(255),

                         version_id UUID NOT NULL,

                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                         CONSTRAINT fk_client_version
                             FOREIGN KEY (version_id)
                                 REFERENCES versions(id)
                                 ON DELETE RESTRICT
);
