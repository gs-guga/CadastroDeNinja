CREATE TABLE tb_missoes (
                            id BIGSERIAL PRIMARY KEY,
                            nome VARCHAR(255),
                            rank VARCHAR(255)
);

CREATE TABLE tb_cadastro (
                             id BIGSERIAL PRIMARY KEY,
                             nome VARCHAR(255),
                             email VARCHAR(255) UNIQUE,
                             idade INT,
                             missoes_id BIGINT,
                             CONSTRAINT fk_missoes FOREIGN KEY (missoes_id) REFERENCES tb_missoes(id)
);