CREATE TABLE candlestick
(
    id        SERIAL PRIMARY KEY,
    pair      VARCHAR(255)   NOT NULL,
    timeframe VARCHAR(255)   NOT NULL,
    open      DECIMAL(18, 8) NOT NULL,
    high      DECIMAL(18, 8) NOT NULL,
    low       DECIMAL(18, 8) NOT NULL,
    close     DECIMAL(18, 8) NOT NULL,
    volume    DECIMAL(18, 8) NOT NULL,
    timestamp TIMESTAMP      NOT NULL
);
