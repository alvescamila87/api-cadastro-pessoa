ALTER TABLE pessoa ADD CONSTRAINT fk_pessoa_endereco_id_endereco_id FOREIGN KEY (endereco_id) REFERENCES endereco(id);