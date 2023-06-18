DROP TABLE IF EXISTS Gastos;
DROP TABLE IF EXISTS TipoDeGasto;
DROP TABLE IF EXISTS Veiculo;
DROP TABLE IF EXISTS Modelo;
DROP TABLE IF EXISTS Marca;
DROP TABLE IF EXISTS Proprietario;

create table Proprietario(
	id serial primary key,
	CPF char(11) unique not null,
	nome varchar(80) not null,
	telefone_DDI varchar(3) not null,
	telefone_DDD varchar(3) not null,
	telefone_numero varchar(9),
	email varchar(70) unique not null,
	numeroDaCNH char(9) not null,
	categoriaDaCNH varchar(20) not null,
	numeroDeCarros integer not null
);

create table Marca(
	id serial primary key,
	nome varchar(40) unique not null,
	url varchar(240) not null
);

create table Modelo(
	id serial primary key,
	nome varchar(40) unique not null,
	url varchar(240) not null,
	tipoDoVeiculo varchar(20) not null,
	idMarca integer not null,
	Foreign Key(idMarca)
	References Marca(id)
);

create table Veiculo(
	id serial primary key,
	placa char(7) unique not null,
	anoFabricacao char(4) not null,
	anoModelo char(4) not null,
	tipoDoCombustivel varchar(20) not null,
	quilometragemAtual integer not null,
	situacaoDoVeiculo varchar(20) not null,
	idModelo integer not null,
	Foreign Key(idModelo)
	References Modelo(id),
	idProprietario integer not null,
	Foreign Key(idProprietario)
	References Proprietario(id)
);

create table TipoDeGasto(
	id serial primary key,
	nome varchar(40) unique not null,
	url varchar(240) not null
);

create table Gastos(
	id serial primary key,
	descricao varchar(240) not null,
	valor real not null,
	data date not null,
	idVeiculo integer not null,
	Foreign Key(idVeiculo)
	References Veiculo(id),
	idTipoDeGasto integer not null,
	Foreign Key(idTipoDeGasto)
	References TipoDeGasto(id)
);