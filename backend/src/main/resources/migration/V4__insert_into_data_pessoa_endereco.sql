INSERT INTO endereco (cep, logradouro, numero, complemento, bairro, localidade, uf, ibge, gia, ddd, siafi) VALUES
('01001-000', 'Rua das Oliveiras', '123', 'Apt 101', 'Centro', 'São Paulo', 'SP', '3550308', '1004', '11', '7107'),
('20000-000', 'Rua dos Cedros', '456', 'Casa 2', 'Jardim', 'Rio de Janeiro', 'RJ', '3304557', '2001', '21', '6001'),
('40000-000', 'Avenida dos Reis', '789', 'Bloco A', 'Alto', 'Salvador', 'BA', '2927408', '1002', '71', '4220'),
('70000-000', 'Praça da Paz', '101', 'Loja 3', 'Centro', 'Brasília', 'DF', '5300108', '2003', '61', '9701'),
('60000-000', 'Rua das Palmeiras', '102', 'Apt 202', 'Jardim', 'Fortaleza', 'CE', '2304400', '2004', '85', '1389'),
('30000-000', 'Alameda dos Profetas', '103', 'Casa 4', 'Vila Nova', 'Belo Horizonte', 'MG', '3106200', '1005', '31', '4120'),
('80000-000', 'Rua dos Apóstolos', '104', 'Bloco B', 'Bela Vista', 'Curitiba', 'PR', '4106902', '1006', '41', '7561'),
('90000-000', 'Avenida da Fé', '105', 'Casa 5', 'Centro', 'Porto Alegre', 'RS', '4314902', '1007', '51', '8803'),
('64000-000', 'Rua do Templo', '106', 'Apt 303', 'Jardim', 'Teresina', 'PI', '2211001', '2008', '86', '1389'),
('50000-000', 'Travessa dos Santos', '107', 'Loja 6', 'Centro', 'Recife', 'PE', '2611606', '1009', '81', '1389');

INSERT INTO pessoa (nome_completo, cpf, telefone, endereco_id) VALUES
('Abraão Silva', '123.456.789-01', '(11) 91234-5678', 1),
('Sara Oliveira', '234.567.890-12', '(21) 92345-6789', 2),
('Isaac Pereira', '345.678.901-23', '(71) 93456-7890', 3),
('Rebeca Costa', '456.789.012-34', '(61) 94567-8901', 4),
('Jacó Santos', '567.890.123-45', '(85) 95678-9012', 5),
('Raquel Almeida', '678.901.234-56', '(31) 96789-0123', 6),
('Moisés Souza', '789.012.345-67', '(41) 97890-1234', 7),
('Miriam Rocha', '890.123.456-78', '(51) 98901-2345', 8),
('José Fernandes', '901.234.567-89', '(86) 99012-3456', 9),
('Maria Lima', '012.345.678-90', '(81) 90012-3456', 10);