CREATE TABLE pessoa (
    id BIGSERIAL PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    cpf VARCHAR(50) NOT NULL UNIQUE,
    telefone VARCHAR(50),
    endereco_id BIGINT
);