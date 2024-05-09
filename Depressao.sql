CREATE TABLE Usuario (
    id_usuario INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(15),
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    cargo VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    isAdmin NUMBER(1, 0) DEFAULT 0 not null
);
CREATE TABLE Plano (
    id_plano INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nomeFantasia VARCHAR(255) NOT NULL,
    tipoPlano VARCHAR(255) NOT NULL,
    dataInicio DATE NOT NULL,
    dataFinal DATE NOT NULL,
    valor FLOAT NOT NULL
);
CREATE TABLE Produto (
    id_produto INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco FLOAT NOT NULL,
    id_plano INT,
    FOREIGN KEY (id_plano) REFERENCES Plano (id_plano) ON DELETE CASCADE
);
CREATE TABLE EmpresaCliente (
    id_empresaCliente INT GENERATED BY DEFAULT AS IDENTITY,
    cnpj VARCHAR(18) UNIQUE NOT NULL,
    telefone VARCHAR(15),
    razaoSocial VARCHAR(255) NOT NULL,
    nomeFantasia VARCHAR(255) NOT NULL,
    tamanho INT,
    id_cliente INT,
    PRIMARY KEY (id_empresaCliente),
    FOREIGN KEY (id_cliente) REFERENCES Usuario(id_usuario)
);
CREATE TABLE Endereco (
    id_endereco INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario) ON DELETE CASCADE
);
CREATE TABLE Contrato (
    id_contrato INT GENERATED BY DEFAULT AS IDENTITY,
    empresa_cliente_id INT,
    plano_id INT,
    tipoPlano VARCHAR(255) not NULL,
    PRIMARY key (
        id_contrato,
        empresa_cliente_id,
        plano_id,
    ),
    FOREIGN KEY (empresa_cliente_id) REFERENCES EmpresaCliente (id_empresaCliente),
    FOREIGN KEY (plano_id) REFERENCES Plano (id_plano)
);

/* MYSQL */
create database salesforce;

use salesforce;

CREATE TABLE Usuario (
    id_usuario INT auto_increment PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(15),
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    cargo VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    isAdmin NUMeric(1, 0) DEFAULT 0 not null
);
CREATE TABLE Plano (
    id_plano INT auto_increment PRIMARY KEY,
    nomeFantasia VARCHAR(255) NOT NULL,
    tipoPlano VARCHAR(255) NOT NULL,
    dataInicio DATE NOT NULL,
    dataFinal DATE NOT NULL,
    valor FLOAT NOT NULL
);
CREATE TABLE Produto (
    id_produto INT auto_increment PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco FLOAT NOT NULL,
    id_plano INT,
    FOREIGN KEY (id_plano) REFERENCES Plano (id_plano) ON DELETE CASCADE
);
CREATE TABLE EmpresaCliente (
    id_empresaCliente INT auto_increment,
    cnpj VARCHAR(18) UNIQUE NOT NULL,
    telefone VARCHAR(15),
    razaoSocial VARCHAR(255) NOT NULL,
    nomeFantasia VARCHAR(255) NOT NULL,
    tamanho INT,
    id_cliente INT,
    PRIMARY KEY (id_empresaCliente),
    FOREIGN KEY (id_cliente) REFERENCES Usuario(id_usuario)
);
CREATE TABLE Endereco (
    id_endereco INT auto_increment PRIMARY KEY,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario) ON DELETE CASCADE
);
CREATE TABLE Contrato (
    id_contrato INT auto_increment,
    empresa_cliente_id INT,
    plano_id INT,
    tipoPlano VARCHAR(255) not NULL,
    PRIMARY key (
        id_contrato,
        empresa_cliente_id,
        plano_id
    ),
    FOREIGN KEY (empresa_cliente_id) REFERENCES EmpresaCliente (id_empresaCliente),
    FOREIGN KEY (plano_id) REFERENCES Plano (id_plano)
);

-- Inserindo registros na tabela Usuario
INSERT INTO Usuario (nome, telefone, email, cpf, cargo, senha, isAdmin) 
VALUES 
('João Silva', '123456789', 'joao@email.com', '123.456.789-01', 'Gerente de Vendas', 'senha123', 1),
('Maria Oliveira', '987654321', 'maria@email.com', '987.654.321-02', 'Assistente de Marketing', 'senha456', 0),
('Carlos Souza', '456123789', 'carlos@email.com', '456.123.789-03', 'Analista Financeiro', 'senha789', 0);

-- Inserindo registros na tabela Plano
INSERT INTO Plano (nomeFantasia, tipoPlano, dataInicio, dataFinal, valor) 
VALUES 
('Plano A', 'Assinatura mensal', '2024-01-01', '2024-12-31', 100.00),
('Plano B', 'Assinatura trimestral', '2024-01-01', '2024-12-31', 270.00),
('Plano C', 'Assinatura anual', '2024-01-01', '2024-12-31', 1000.00);

-- Inserindo registros na tabela Produto
INSERT INTO Produto (nome, preco, id_plano) 
VALUES 
('Produto X', 50.00, 1),
('Produto Y', 75.00, 2),
('Produto Z', 100.00, 3);

-- Inserindo registros na tabela EmpresaCliente
INSERT INTO EmpresaCliente (cnpj, telefone, razaoSocial, nomeFantasia, tamanho, id_cliente) 
VALUES 
('12345678901234', '111111111', 'Empresa A', 'Empresa A', 50, 1),
('98765432109876', '222222222', 'Empresa B', 'Empresa B', 100, 2),
('45612378904567', '333333333', 'Empresa C', 'Empresa C', 200, 3);

-- Inserindo registros na tabela Endereco
INSERT INTO Endereco (rua, numero, cidade, estado, cep, id_usuario) 
VALUES 
('Rua A', '123', 'Cidade A', 'Estado A', '12345-678', 1),
('Rua B', '456', 'Cidade B', 'Estado B', '98765-432', 2),
('Rua C', '789', 'Cidade C', 'Estado C', '54321-098', 3);

-- Inserindo registros na tabela Contrato
INSERT INTO Contrato (empresa_cliente_id, plano_id, tipoPlano) 
VALUES 
(1, 1, 'Assinatura mensal'),
(2, 2, 'Assinatura trimestral'),
(3, 3, 'Assinatura anual');
