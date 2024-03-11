# Desafio TOTVS
Aplicação feita em Java e Angular e Json API Server como banco de dados. Executa cadastro de usuários validando:
* Se os nomes dos usuários têm um mínimo de 10 caracteres, 
* Se o telefone já não está associado a um usuário.
* Se o telefone é nulo ou num formato inválido.

Aplicação possuio testes unitários para as validações e documentação com JavaDoc.

## Deploy
Para fazer deploy é necessário ter instalado Java, gradle e node.

### Json API Server
Na raíz do projeto execute o comando`npx json-server db.json`

### Backend
Dentro da pasta totvs-backend execute o comando `gradle bootRun`, ou inicie a aplicação através de sua IDE.

### Frontend
Dentro da pasta totvs-frontend execute o comando `ng serve`, ou inicie a aplicação através de sua IDE. A aplicação pode ser acessada em [http://localhost:4200/](http://localhost:4200/).

