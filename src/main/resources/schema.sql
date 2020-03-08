
CREATE TABLE grupo_produto (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    nome VARCHAR(128) NOT NULL,
    PRIMARY KEY (id) 
);

CREATE TABLE produto ( id   INTEGER      NOT NULL AUTO_INCREMENT, nome VARCHAR(200) NOT NULL, valor_unitario double NOT NULL, grupo_id int NOT NULL ,   PRIMARY KEY (id), FOREIGN KEY (grupo_id) REFERENCES grupo_produto (id));


create table nota_fiscal (  id   INTEGER      NOT NULL AUTO_INCREMENT, numero VARCHAR(20) NOT NULL, valor_total double NOT NULL, PRIMARY KEY (id), UNIQUE KEY(numero)   );


CREATE TABLE produto_nota_fiscal (id   INTEGER      NOT NULL AUTO_INCREMENT, valor_unitario double NOT NULL, valor_total double NOT NULL, quantidade int NOT NULL, produto_id int NOT NULL , nota_fiscal_id int NOT NULL,
PRIMARY KEY (id),  
CONSTRAINT ids  KEY (produto_id, nota_fiscal_id),
FOREIGN KEY (produto_id) REFERENCES produto (id),
FOREIGN KEY (nota_fiscal_id) REFERENCES nota_fiscal (id)
);
