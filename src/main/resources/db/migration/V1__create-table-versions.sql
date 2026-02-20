CREATE TABLE versions (
                          id UUID PRIMARY KEY,
                          number_version VARCHAR(100) NOT NULL,
                          date_release DATE,
                          changelog TEXT,
                          active BOOLEAN NOT NULL DEFAULT TRUE,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
