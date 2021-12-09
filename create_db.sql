
    create table clientes (
       id bigint not null,
        cpf varchar(20),
        email varchar(100),
        nome varchar(150),
        publicidade_email bit,
        publicidade_sms bit,
        telefone_celular varchar(50),
        tipo varchar(50),
        primary key (id)
    ) engine=InnoDB;

    create table config_app (
       id bigint not null auto_increment,
        client_id_ifood_mercado varchar(255),
        client_secret_ifood_mercado varchar(255),
        expire_in datetime,
        token TEXT,
        primary key (id)
    ) engine=InnoDB;

    create table enderecos_cliente (
       id bigint not null auto_increment,
        bairro varchar(100) not null,
        cep varchar(10) not null,
        cidade varchar(100) not null,
        complemento varchar(100) not null,
        estado varchar(50) not null,
        ibge varchar(10),
        id_endereco_entrega bigint,
        latitude decimal(19,2),
        logradouro varchar(255) not null,
        longitude decimal(19,2),
        numero varchar(10),
        uf varchar(2) not null,
        numero_endereco_cliente varchar(10),
        cliente_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table eventos_pedidos_loja (
       id bigint not null,
        codigo_pedido varchar(50),
        id_loja integer,
        status varchar(10),
        pedido_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table itens_pedido (
       id bigint not null,
        codigo varchar(50),
        codigo_barra varchar(20),
        codigo_loja varchar(50),
        desistencia bit,
        value_index varchar(255),
        indisponivel bit,
        observacao varchar(255),
        peso_variavel bit,
        plu varchar(50),
        produto varchar(255),
        quantidade integer,
        quantidade3 integer,
        valor decimal(19,2),
        valor_total decimal(19,2),
        pedido_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table loja (
       id bigint not null,
        bairro varchar(100) not null,
        cep varchar(10) not null,
        cidade varchar(100) not null,
        cnpj varchar(20),
        estado varchar(50) not null,
        logradouro varchar(255) not null,
        nome varchar(100),
        numero_enredeco_loja varchar(10),
        rede_id bigint,
        rede_nome varchar(150),
        status varchar(20),
        uf varchar(2) not null,
        primary key (id)
    ) engine=InnoDB;

    create table pedidos_integracao (
       id bigint not null auto_increment,
        agendamento_data_fim date,
        agendamento_data_inicio date,
        agendamento_hora_fim time,
        agendamento_hora_inicio time,
        codigo varchar(50),
        codigo_loja varchar(50),
        cpf_na_nota bit,
        data date,
        data_hora datetime,
        bairro varchar(100) not null,
        cep varchar(10) not null,
        cidade varchar(100) not null,
        complemento varchar(100) not null,
        estado varchar(50) not null,
        ibge varchar(10),
        id_endereco_entrega bigint,
        latitude decimal(19,2),
        logradouro varchar(255) not null,
        longitude decimal(19,2),
        numero varchar(10),
        uf varchar(2) not null,
        entrega bit,
        hora time,
        id_cliente bigint,
        id_loja bigint,
        parceiro_codigo_entrega varchar(50),
        parceiro_codigo_pedido varchar(50),
        pessoa_autorizada_recebimento varchar(100),
        plataforma varchar(100),
        quantidade_item_unico integer,
        status varchar(10),
        status_descricao varchar(30),
        valor_conveniencia decimal(19,2),
        valor_corrigido decimal(19,2),
        valor_desconto decimal(19,2),
        valor_entrega decimal(19,2),
        valor_mercado decimal(19,2),
        valor_retirada decimal(19,2),
        valor_total decimal(19,2),
        valor_troco decimal(19,2),
        cliente_id bigint,
        loja_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table produtos_integracao (
       id bigint not null auto_increment,
        ativo bit,
        categoria varchar(100),
        codigo_barra decimal(19,2),
        data_ultima_itegracao datetime,
        departamento varchar(100),
        descricao TEXT,
        id_loja bigint,
        imageurl varchar(150),
        integrar bit,
        marca varchar(100),
        nome varchar(150),
        plu integer,
        quantidade_atacado decimal(19,2),
        quantidade_estoque_atual decimal(19,2),
        quantidade_estoque_minimo decimal(19,2),
        sub_categoria varchar(100),
        unidade varchar(100),
        validade_proxima bit,
        valor decimal(19,2),
        valor_atacado decimal(19,2),
        valor_compra decimal(19,2),
        valor_promocao decimal(19,2),
        volume varchar(100),
        primary key (id)
    ) engine=InnoDB;

    alter table enderecos_cliente 
       add constraint FKl113k1u6u2y689tsxqwtp9bnt 
       foreign key (cliente_id) 
       references clientes (id);

    alter table eventos_pedidos_loja 
       add constraint FKih46beb1so5yr816vhecil56b 
       foreign key (pedido_id) 
       references pedidos_integracao (id);

    alter table itens_pedido 
       add constraint FKtj1hoferqers5s2ha84g7148v 
       foreign key (pedido_id) 
       references pedidos_integracao (id);

    alter table pedidos_integracao 
       add constraint FKetr4fm4donsnami42xnsdsl6q 
       foreign key (cliente_id) 
       references clientes (id);

    alter table pedidos_integracao 
       add constraint FK7be75s2wihv1x05pmehuyqcjm 
       foreign key (loja_id) 
       references loja (id);
insert into config_app (client_id_ifood_mercado, client_secret_ifood_mercado) values ('b090431c-f2aa-48c2-95ce-7e474362d964', 'uf57Q~1VVNdS3r_KZsRBs-khqK5Yttet1yUaW');
INSERT INTO produtos_integracao (ativo,categoria,codigo_barra,data_ultima_itegracao,departamento,descricao,id_loja,imageurl,integrar,marca,nome,plu,quantidade_atacado,quantidade_estoque_atual,quantidade_estoque_minimo,sub_categoria,unidade,validade_proxima,valor,valor_atacado,valor_compra,valor_promocao,volume) VALUES (1,'PADRAO',7891231231231,'0000-00-00 00:00:00','PERECIVEIS','ALIMENTICIOS',1,NULL,1,'','ARROZ FANTASTICO 5KG T1',17,1.00,3.00,10.00,'','KG',0,26.90,1.00,21.58,1.00,'1KG');
INSERT INTO produtos_integracao (ativo,categoria,codigo_barra,data_ultima_itegracao,departamento,descricao,id_loja,imageurl,integrar,marca,nome,plu,quantidade_atacado,quantidade_estoque_atual,quantidade_estoque_minimo,sub_categoria,unidade,validade_proxima,valor,valor_atacado,valor_compra,valor_promocao,volume) VALUES (1,'PADRAO',7891231231231,'0000-00-00 00:00:00','PERECIVEIS','ALIMENTICIOS',1,NULL,1,'','ARROZ FANTASTICO 1KG T1',20,1.00,17.00,10.00,'','KG',0,5.99,4.09,4.32,4.09,'1KG');
create table clientes (id bigint not null, cpf varchar(20), email varchar(100), nome varchar(150), publicidade_email bit, publicidade_sms bit, telefone_celular varchar(50), tipo varchar(50), primary key (id)) engine=InnoDB;
create table config_app (id bigint not null auto_increment, client_id_ifood_mercado varchar(255), client_secret_ifood_mercado varchar(255), expire_in datetime, token TEXT, primary key (id)) engine=InnoDB;
create table enderecos_cliente (id bigint not null auto_increment, bairro varchar(100) not null, cep varchar(10) not null, cidade varchar(100) not null, complemento varchar(100) not null, estado varchar(50) not null, ibge varchar(10), id_endereco_entrega bigint, latitude decimal(19,2), logradouro varchar(255) not null, longitude decimal(19,2), numero varchar(10), uf varchar(2) not null, numero_endereco_cliente varchar(10), cliente_id bigint, primary key (id)) engine=InnoDB;
create table eventos_pedidos_loja (id bigint not null, codigo_pedido varchar(50), id_loja integer, status varchar(10), pedido_id bigint, primary key (id)) engine=InnoDB;
create table itens_pedido (id bigint not null, codigo varchar(50), codigo_barra varchar(20), codigo_loja varchar(50), desistencia bit, value_index varchar(255), indisponivel bit, observacao varchar(255), peso_variavel bit, plu varchar(50), produto varchar(255), quantidade integer, quantidade3 integer, valor decimal(19,2), valor_total decimal(19,2), pedido_id bigint, primary key (id)) engine=InnoDB;
create table loja (id bigint not null, bairro varchar(100) not null, cep varchar(10) not null, cidade varchar(100) not null, cnpj varchar(20), estado varchar(50) not null, logradouro varchar(255) not null, nome varchar(100), numero_enredeco_loja varchar(10), rede_id bigint, rede_nome varchar(150), status varchar(20), uf varchar(2) not null, primary key (id)) engine=InnoDB;
create table pedidos_integracao (id bigint not null auto_increment, agendamento_data_fim date, agendamento_data_inicio date, agendamento_hora_fim time, agendamento_hora_inicio time, codigo varchar(50), codigo_loja varchar(50), cpf_na_nota bit, data date, data_hora datetime, bairro varchar(100) not null, cep varchar(10) not null, cidade varchar(100) not null, complemento varchar(100) not null, estado varchar(50) not null, ibge varchar(10), id_endereco_entrega bigint, latitude decimal(19,2), logradouro varchar(255) not null, longitude decimal(19,2), numero varchar(10), uf varchar(2) not null, entrega bit, hora time, id_cliente bigint, id_loja bigint, parceiro_codigo_entrega varchar(50), parceiro_codigo_pedido varchar(50), pessoa_autorizada_recebimento varchar(100), plataforma varchar(100), quantidade_item_unico integer, status varchar(10), status_descricao varchar(30), valor_conveniencia decimal(19,2), valor_corrigido decimal(19,2), valor_desconto decimal(19,2), valor_entrega decimal(19,2), valor_mercado decimal(19,2), valor_retirada decimal(19,2), valor_total decimal(19,2), valor_troco decimal(19,2), cliente_id bigint, loja_id bigint, primary key (id)) engine=InnoDB;
create table produtos_integracao (id bigint not null auto_increment, ativo bit, categoria varchar(100), codigo_barra varchar(20), data_ultima_itegracao datetime, departamento varchar(100), descricao TEXT, id_loja bigint, image_url varchar(150), integrar bit, marca varchar(100), nome varchar(150), plu varchar(50), quantidade_atacado integer, quantidade_estoque_atual integer, quantidade_estoque_minimo integer, sub_categoria varchar(100), unidade varchar(100), validade_proxima bit, valor decimal(19,2), valor_atacado decimal(19,2), valor_compra decimal(19,2), valor_promocao decimal(19,2), volume varchar(100), primary key (id)) engine=InnoDB;
alter table enderecos_cliente add constraint FKl113k1u6u2y689tsxqwtp9bnt foreign key (cliente_id) references clientes (id);
alter table eventos_pedidos_loja add constraint FKih46beb1so5yr816vhecil56b foreign key (pedido_id) references pedidos_integracao (id);
alter table itens_pedido add constraint FKtj1hoferqers5s2ha84g7148v foreign key (pedido_id) references pedidos_integracao (id);
alter table pedidos_integracao add constraint FKetr4fm4donsnami42xnsdsl6q foreign key (cliente_id) references clientes (id);
alter table pedidos_integracao add constraint FK7be75s2wihv1x05pmehuyqcjm foreign key (loja_id) references loja (id);
insert into config_app (client_id_ifood_mercado, client_secret_ifood_mercado) values ('b090431c-f2aa-48c2-95ce-7e474362d964', 'uf57Q~1VVNdS3r_KZsRBs-khqK5Yttet1yUaW');
INSERT INTO sigma_db_ifood.produtos_integracao (ativo,categoria,codigo_barra,data_ultima_itegracao,departamento,descricao,id_loja,imageurl,integrar,marca,nome,plu,quantidade_atacado,quantidade_estoque_atual,quantidade_estoque_minimo,sub_categoria,unidade,validade_proxima,valor,valor_atacado,valor_compra,valor_promocao,volume) VALUES (1,'PADRAO',7891231231231,'0000-00-00 00:00:00','PERECIVEIS','ALIMENTICIOS',1,NULL,1,'','ARROZ FANTASTICO 5KG T1',17,1.00,3.00,10.00,'','KG',0,26.90,1.00,21.58,1.00,'1KG');
INSERT INTO sigma_db_ifood.produtos_integracao (ativo,categoria,codigo_barra,data_ultima_itegracao,departamento,descricao,id_loja,imageurl,integrar,marca,nome,plu,quantidade_atacado,quantidade_estoque_atual,quantidade_estoque_minimo,sub_categoria,unidade,validade_proxima,valor,valor_atacado,valor_compra,valor_promocao,volume) VALUES (1,'PADRAO',7891231231231,'0000-00-00 00:00:00','PERECIVEIS','ALIMENTICIOS',1,NULL,1,'','ARROZ FANTASTICO 1KG T1',20,1.00,17.00,10.00,'','KG',0,5.99,4.09,4.32,4.09,'1KG');
create table clientes (id bigint not null, cpf varchar(20), email varchar(100), nome varchar(150), publicidade_email bit, publicidade_sms bit, telefone_celular varchar(50), tipo varchar(50), primary key (id)) engine=InnoDB;
create table config_app (id bigint not null auto_increment, client_id_ifood_mercado varchar(255), client_secret_ifood_mercado varchar(255), expire_in datetime, token TEXT, primary key (id)) engine=InnoDB;
create table enderecos_cliente (id bigint not null auto_increment, bairro varchar(100) not null, cep varchar(10) not null, cidade varchar(100) not null, complemento varchar(100) not null, estado varchar(50) not null, ibge varchar(10), id_endereco_entrega bigint, latitude decimal(19,2), logradouro varchar(255) not null, longitude decimal(19,2), numero varchar(10), uf varchar(2) not null, numero_endereco_cliente varchar(10), cliente_id bigint, primary key (id)) engine=InnoDB;
create table eventos_pedidos_loja (id bigint not null, codigo_pedido varchar(50), id_loja integer, status varchar(10), pedido_id bigint, primary key (id)) engine=InnoDB;
create table itens_pedido (id bigint not null, codigo varchar(50), codigo_barra varchar(20), codigo_loja varchar(50), desistencia bit, value_index varchar(255), indisponivel bit, observacao varchar(255), peso_variavel bit, plu varchar(50), produto varchar(255), quantidade integer, quantidade3 integer, valor decimal(19,2), valor_total decimal(19,2), pedido_id bigint, primary key (id)) engine=InnoDB;
create table loja (id bigint not null, bairro varchar(100) not null, cep varchar(10) not null, cidade varchar(100) not null, cnpj varchar(20), estado varchar(50) not null, logradouro varchar(255) not null, nome varchar(100), numero_enredeco_loja varchar(10), rede_id bigint, rede_nome varchar(150), status varchar(20), uf varchar(2) not null, primary key (id)) engine=InnoDB;
create table pedidos_integracao (id bigint not null auto_increment, agendamento_data_fim date, agendamento_data_inicio date, agendamento_hora_fim time, agendamento_hora_inicio time, codigo varchar(50), codigo_loja varchar(50), cpf_na_nota bit, data date, data_hora datetime, bairro varchar(100) not null, cep varchar(10) not null, cidade varchar(100) not null, complemento varchar(100) not null, estado varchar(50) not null, ibge varchar(10), id_endereco_entrega bigint, latitude decimal(19,2), logradouro varchar(255) not null, longitude decimal(19,2), numero varchar(10), uf varchar(2) not null, entrega bit, hora time, id_cliente bigint, id_loja bigint, parceiro_codigo_entrega varchar(50), parceiro_codigo_pedido varchar(50), pessoa_autorizada_recebimento varchar(100), plataforma varchar(100), quantidade_item_unico integer, status varchar(10), status_descricao varchar(30), valor_conveniencia decimal(19,2), valor_corrigido decimal(19,2), valor_desconto decimal(19,2), valor_entrega decimal(19,2), valor_mercado decimal(19,2), valor_retirada decimal(19,2), valor_total decimal(19,2), valor_troco decimal(19,2), cliente_id bigint, loja_id bigint, primary key (id)) engine=InnoDB;
create table produtos_integracao (id bigint not null auto_increment, ativo bit, categoria varchar(100), codigo_barra varchar(20), data_ultima_itegracao datetime, departamento varchar(100), descricao TEXT, id_loja bigint, image_url varchar(150), integrar bit, marca varchar(100), nome varchar(150), plu varchar(50), quantidade_atacado integer, quantidade_estoque_atual integer, quantidade_estoque_minimo integer, sub_categoria varchar(100), unidade varchar(100), validade_proxima bit, valor decimal(19,2), valor_atacado decimal(19,2), valor_compra decimal(19,2), valor_promocao decimal(19,2), volume varchar(100), primary key (id)) engine=InnoDB;
alter table enderecos_cliente add constraint FKl113k1u6u2y689tsxqwtp9bnt foreign key (cliente_id) references clientes (id);
alter table eventos_pedidos_loja add constraint FKih46beb1so5yr816vhecil56b foreign key (pedido_id) references pedidos_integracao (id);
alter table itens_pedido add constraint FKtj1hoferqers5s2ha84g7148v foreign key (pedido_id) references pedidos_integracao (id);
alter table pedidos_integracao add constraint FKetr4fm4donsnami42xnsdsl6q foreign key (cliente_id) references clientes (id);
alter table pedidos_integracao add constraint FK7be75s2wihv1x05pmehuyqcjm foreign key (loja_id) references loja (id);
insert into config_app (client_id_ifood_mercado, client_secret_ifood_mercado) values ('b090431c-f2aa-48c2-95ce-7e474362d964', 'uf57Q~1VVNdS3r_KZsRBs-khqK5Yttet1yUaW');
INSERT INTO produtos_integracao (ativo,categoria,codigo_barra,data_ultima_itegracao,departamento,descricao,id_loja,image_url,integrar,marca,nome,plu,quantidade_atacado,quantidade_estoque_atual,quantidade_estoque_minimo,sub_categoria,unidade,validade_proxima,valor,valor_atacado,valor_compra,valor_promocao,volume) VALUES (15905,'PADRAO','7891231231231','0000-00-00 00:00:00','PERECIVEIS','ALIMENTICIOS',1,'',1,'','ARROZ FANTASTICO 1KG T1','20',1,17,10,'','KG',0,5.99,4.09,4.32,4.09,'1KG');
