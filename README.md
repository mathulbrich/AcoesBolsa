# Simulador de Negociações na Bolsa de Valores

Índice
========

   * [Descrição](#descrição)
   * [Requisitos](#requisitos)
   * [Instalação](#instalação)
   * [Utilização](#utilização)
      * [Conta](#conta)
        * [Cadastrar Conta](#cadastrar-conta)
        * [Consultar Conta](#consultar-conta)
      * [Monitoramento](#monitoramento)
        * [Cadastrar Monitoramento](#cadastrar-monitoramento)
        * [Consultar Monitoramento](#consultar-monitoramento)
        * [Atualizar Monitoramento](#atualizar-monitoramento)
        * [Remover Monitoramento](#remover-monitoramento)
      * [Histórico](#histórico)
      * [Negociações](#negociações)
   * [Arquitetura](#arquitetura)
   * [Tecnologias](#tecnologias)
   * [Aprendizado](#aprendizado)

Descrição
========

O objetivo deste sistema e realizar a simulação do processo de compra e venda
de ações na bolsa de valores de forma automatizada. Desta forma, o usuario
poderá registrar e consultar contas, além de criar, alterar, remover e buscar
monitoramentos em determinadas empresas.

Requisitos
========

* Java JRE 1.8_162 acima
* MySQL

Instalação
========

MySQL
--------

Para poder utilizar a aplicação normalmente se relacionando com o banco de 
dados é necessário criar uma nova conexão do banco com as seguintes configurações:

```
Conexão: localhost ou 127.0.0.1
Usuário: root
Senha: superabc
Porta: 3306
```

Após configurar a conexão e se conectar nela, crie um novo banco com o 
seguinte comando:

```
CREATE DATABASE acoesbolsa;
```

O usuário também poderá incluir sua própria conexão assim como o banco, 
porém para isso deve ser realizado o donwload do projeto e adicionar
essas alterações no arquivo application.properties.

Aplicação
--------

Para poder rodar o app na sua máquina, após realizar as configurações anteriores,
siga as seguintes etapas:

1. Faça o download da aplicação.
2. Abra seu terminal e navegue até a pasta onde a pasta da aplicação foi colocada.
3. Dentro da pasta target, execute o comando:
```
java -jar ProjetoAcoesBolsa-0.0.1-SNAPSHOT.jar
```
4. A aplicação agora estará rodando.

Utilização
========

Conta
--------

Na conta, dentro do sistema, pode ser realizado duas operações:

1. Cadastrar nova conta
2. Consultar conta existente

### Cadastrar conta

| ENDPOINT          | PARAMETROS    | TIPO          |
| -------------     | ------------- | ------------- |
| /conta/cadastrar  |               | POST          |

Para realizar o cadastro de uma nova conta, realize uma chamada HTTP
seguindo as definições acima.

Ademais, é necessário enviar juntamente um corpo com base nesta estrutura:

```
{
    "email" : "exemplo@gmail.com",
    "saldo" : 10000
}
```

O email deve ser 'gmail' para garantir o envio do email quando a negociação for realizada.

### Consultar Conta

| ENDPOINT          | PARAMETROS       | TIPO          |
| -------------     | -------------    | ------------- |
| /conta/{id}       | id = id da conta | GET           |

Para consultar uma conta existente no sistema, realiza uma chamada http
seguindo as definições acima.

O retorno da requisição será um arquivo no formato JSON assim como o do seguinte exemplo:

```
{
    "id" : 1,
    "email" : "testconsulta@gmail.com",
    "saldo" : 10000,
    "quantidadeAcoes" : 0,
    "monitoramentos" : [],
    "negociacoes" : []
}
```

Monitoramento
--------

No monitoramento de ações, o usuário possui quatro operações que podem ser realizadas, 
sendo elas:

1. Cadastrar novo monitoramento
2. Consultar monitoramento
3. Alterar monitoramento
4. Remover monitoramento

### Cadastrar monitoramento

| ENDPOINT                             | PARAMETROS       | TIPO          |
| -------------                        | -------------    | ------------- |
| /conta/{id}/monitoramentos/cadastrar | id = id da conta | POST          |

Para realizar o cadastro de monitoramento no sistema deve enviar uma requisição HTTP 
seguindo as definições acima.

Ademais, juntamente na requisição deve-se enviar um corpo com base nesta estrutura:

```
{
    "precoCompra" : 10.1,
    "precoVenda" : 10.9,
    "nomeEmpresa" : "Intel"
}
```

O nome da empresa deve ser Intel ou SoftExpert.

### Consultar monitoramento

| ENDPOINT                             | PARAMETROS        | TIPO          |
| -------------                        | -------------     | ------------- |
| /conta/{id}/monitoramentos           | id = id da conta  | GET           |

Para realizar a consulta dos monitoramentos relativo a conta, deve se enviar uma
requisição HTTP seguindo as definições acima.

O retorno da requisição será um arquivo no formato JSON assim como o do seguinte exemplo:

```
{
    "id" : 1,
    "precoCompra" : 10.1,
    "precoVenda" : 10.9,
    "nomeEmpresa" : "Intel"
}
```

### Atualizar monitoramento

| ENDPOINT                             | PARAMETROS        | TIPO          |
| -------------                        | -------------     | ------------- |
| /conta/{id}/monitoramentos/atualizar | id = id da conta  | PUT           |

Para realizar a atualização dos monitoramentos relativo a conta, deve se enviar uma
requisição HTTP seguindo as definições acima.

Ademais, é necessário enviar juntamente um corpo com base nesta estrutura:

```
{
    "id" : 1
    "precoCompra" : 10.3,
    "precoVenda" : 10.55,
    "nomeEmpresa" : "Intel",
}
```

### Remover monitoramento

| ENDPOINT                             | PARAMETROS                                       | TIPO          |
| -------------                        | -------------                                    | ------------- |
| /conta/{id}/monitoramentos/{moni_id} | id = id da conta / moni_id = id do monitoramento | DELETE        |

Para realizar a remoção dos monitoramentos relativo a conta, deve se enviar uma
requisição HTTP seguindo as definições acima. Após enviar a requisição, caso ocorra 
com sucesso, o monitoramento já estará removido.

Histórico
--------

| ENDPOINT              | PARAMETROS       | TIPO          |
| -------------         | -------------    | ------------- |
| /conta/{id}/historico | id = id da conta | GET           |

Para consultar o histórico de negociações relativo a conta, deve se enviar uma
requisição HTTP seguindo as definições acima. 

O retorno da requisição será um arquivo no formato JSON assim como o do seguinte exemplo:

```
{
    {
        "id" : 1,
        "valorNegociado" : 10.5677,
        "quantidade" : 957.67,
        "nomeEmpresa" : "Intel"
        "tipoNegociacao" : "COMPRA",
        "data" : "08-06-2018 00:03:06"
    },
    {
        "id" : 2,
        "valorNegociado" : 10.8213,
        "quantidade" : 957.67,
        "tipoNegociacao" : "VENDA",
        "data" : "08-06-2018 00:05:11"
    }
}
```

Negociações
--------

| ENDPOINT            | PARAMETROS       | TIPO          |
| -------------       | -------------    | ------------- |
| /transacoes/iniciar |                  | POST          |

As negociações no sistema são realizadas quando uma requisição o inicio das transações é
solicitado seguindo as definições acima. Assim, o sistema irá realizar uma operação de
atualização de preços das bolsas das empresas em cada 5 segundos, repetindo o processo
100 vezes (8 minutos e 20 segundos).

Após todas as transações serem efetivadas um relatório é gerado no console do usuário
mostrando todas as contas e suas negociações efetuadas.

