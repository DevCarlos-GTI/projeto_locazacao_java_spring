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
    (3, 'Fortaleza ', 2687000);