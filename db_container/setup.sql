CREATE USER sample_owner WITH PASSWORD 'sampletest';
CREATE DATABASE sample;
\connect sample
GRANT ALL PRIVILEGES ON DATABASE sample TO sample_owner;
