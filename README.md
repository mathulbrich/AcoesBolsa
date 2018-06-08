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
      * [Negociações](#negociações)
        * [Historico](#historico)
      * [Transações](#transações)
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

Para realizar o cadastro de uma nova conta, realize uma chamada http
seguindo as seguintes definições:

| ENDPOINT          | PARAMETROS    | TIPO          |
| -------------     | ------------- | ------------- |
| /conta/cadastrar  |               | POST          |

Ademais, é necessário enviar um corpo com esta estrutura:

```
{
	"email" : "exemplo@gmail.com",
	"saldo" : 10000
}
```
