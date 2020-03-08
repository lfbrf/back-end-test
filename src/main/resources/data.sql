/* 
Os valores inseridos por meio da carga estão sendo usados no teste, quaisquer alterações podem afetar diretamente no funcionamento destes. 
Porém, possíveis novas inserções não afetarão no funcionamento do mesmo.
*/

INSERT INTO grupo_produto (nome) VALUES ('Grupo Um');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Dois');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Três');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Quatro');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Cinco');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Seis');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Sete');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Oito');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Nove');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Dez');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Onze');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Doze');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Treze');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Quatorze');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Quinze');
INSERT INTO grupo_produto (nome) VALUES ('Grupo Dezesseis');

INSERT INTO produto (nome, valor_unitario, grupo_id) VALUES ('Produto Um', 10.00, 1 );

INSERT INTO produto (nome, valor_unitario, grupo_id) VALUES ('Produto Dois', 50.00, 1 );

INSERT INTO produto (nome, valor_unitario, grupo_id) VALUES ('Produto Tres', 40.00, 1 );

INSERT INTO produto (nome, valor_unitario, grupo_id) VALUES ('Produto Quatro', 50.00, 2 );

INSERT INTO nota_fiscal (numero, valor_total) VALUES ('1234', 20.00 );

INSERT INTO nota_fiscal (numero, valor_total) VALUES ('4567', 94.00 );

INSERT INTO nota_fiscal (numero, valor_total) VALUES ('777777', 94.00 );

INSERT INTO nota_fiscal (numero, valor_total) VALUES ('12334456', 98.00 );

INSERT INTO produto_nota_fiscal (valor_unitario, valor_total, quantidade, produto_id, nota_fiscal_id)  VALUES (10.00, 20.00, 2, 1, 1);

INSERT INTO produto_nota_fiscal (valor_unitario, valor_total, quantidade, produto_id, nota_fiscal_id)  VALUES (20.00, 40.00, 2, 2, 1);



