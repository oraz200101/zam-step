CREATE SCHEMA IF NOT EXISTS cryptocurrency_schema;

CREATE TABLE cryptocurrency_schema.wallets
(
    wallet_id   SERIAL PRIMARY KEY,
    owner_email VARCHAR(255) UNIQUE,
    balance     DECIMAL,
    address     VARCHAR(255)
);

CREATE TABLE cryptocurrency_schema.transactions
(
    transaction_id SERIAL PRIMARY KEY,
    token_amount   DECIMAL,
    steps_amount   INTEGER,
    created_time   TIMESTAMP,
    wallet_id      BIGINT,
    FOREIGN KEY (wallet_id) REFERENCES cryptocurrency_schema.wallets (wallet_id)
);
