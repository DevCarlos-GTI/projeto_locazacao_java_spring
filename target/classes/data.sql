create table tb_cidade(
    id_cidade bigint not null primary key,
    nome varchar(50) not null,
    qtd_habitantes bigint
);

insert into tb_cidade
    (id_cidade, nome, qtd_habitantes)
values
    (1, 'São Luís', 1779000),
    (2, 'Caxias', 165525),
    (3, 'Fortaleza', 2687000),
    (4, 'São Paulo', 12330000),
    (5, 'Rio de Janeiro', 6748000),
    (6, 'Salvador', 2417678),
    (7, 'Porto Alegre', 1500000),
    (9, 'Palmas', 306296),
    (10, 'Natal', 890480),
    (11, 'Brasília', 2817381 );

--select * from tb_cidade where nome like "s%" -- começa com
--select * from tb_cidade where nome like "%s" -- termina com
--select * from tb_cidade where nome like "%a%" --contem