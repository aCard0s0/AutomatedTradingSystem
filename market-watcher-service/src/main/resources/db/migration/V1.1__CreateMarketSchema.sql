CREATE TABLE market
(
    id        SERIAL PRIMARY KEY,
    exchange  VARCHAR(255) NOT NULL,
    pair      VARCHAR(255) NOT NULL,
    timeframe VARCHAR(255) NOT NULL,
    active    BOOLEAN      NOT NULL DEFAULT FALSE
);
