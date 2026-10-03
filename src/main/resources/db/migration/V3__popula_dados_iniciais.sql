

INSERT INTO tb_missoes (id, nome, rank) VALUES
                                            (1, 'Proteger o Construtor de Pontes', 'C'),
                                            (2, 'Resgatar o Gato da Senhora Shijimi', 'D'),
                                            (3, 'Escoltar o Construtor Tazuna até ao País das Ondas', 'C'),
                                            (5, 'Infiltrar na Vila Oculta da Chuva e Obter Informações', 'S'),
                                            (6, 'Matar a Kaguya', 'S');



SELECT setval('tb_missoes_id_seq', (SELECT MAX(id) FROM tb_missoes));



INSERT INTO tb_cadastro (nome, email, idade, rank) VALUES
                                                       ('Naruto Uzumaki', 'naruto@gmail.com', 17, 'Gennin'),
                                                       ('Sasuke Uchiha', 'sasuke@gmail.com', 17, 'Gennin'),
                                                       ('Sakura Haruno', 'sakura@gmail.com', 17, 'Gennin'),
                                                       ('Gaara', 'gaara@gmail.com', 19, 'Gennin'),
                                                       ('Hinata Hyuga', 'hinata@gmail.com', 17, 'Gennin'),
                                                       ('Yamato', 'yamato@anbu.konoha.com', 30, 'Jonin / ANBU'),
                                                       ('Choji Akimichi', 'choji@konoha.com', 17, 'Chunin'),
                                                       ('Madara Uchiha', 'madara@gmail.com', 100, 'Exilado');



SELECT setval('tb_cadastro_id_seq', (SELECT MAX(id) FROM tb_cadastro));