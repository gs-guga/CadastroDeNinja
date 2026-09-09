-- migrations pra adicionar a coluna de rank na tabela cadastros

ALTER TABLE tb_cadastro
ADD COLUMN rank VARCHAR(255);