CREATE TABLE endereco (
    id BIGSERIAL PRIMARY KEY,
    cep VARCHAR(50) NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(50) NOT NULL,
    complemento VARCHAR(255),
    bairro VARCHAR(255) NOT NULL,
    localidade VARCHAR(255) NOT NULL,
    uf VARCHAR(15)  NOT NULL,
    ibge VARCHAR(15),
    gia VARCHAR(15),
    ddd VARCHAR(15),
    siafi VARCHAR(15)
);
