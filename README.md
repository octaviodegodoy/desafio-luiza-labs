# Desafio Luiza Labs - Luiz Octavio Godoy

# ESTE DESAFIO FOI DESENVOLVIDO EM 3 DIAS POR EXIGÊNCIA DE UM PROCESSO SELETIVO DO LUIZA LABS
# PARA QUEM FOR SE CANDIDATAR AS ESPECIFICAÇÕES SÃO DE UM CÓDIGO SIMPLES 
# O FEEDBACK DO REVISOR SÓ EXISTE NA CABEÇA DELE NÃO FOI EXPOSTO NA ESPECIFICAÇÃO
# SEGUE O FEEDBACK DESSE CÓDIGO

#Positivo
- No Readme apresentou passos para execução
- Criou testes unitários
- Separou paramentos de posições da dados em constant
- Utilizou stream para manipulação de listas

Negativo
 - Inexistência de API POST para lancamento de arquivo txt via REST API
 - No readme descrição da funcionalidade de aplicação e ferramentas bem resumidas
 - Apos processamentos de testes não foi gerado arquivo com informações sobre cobertura
 - Poderia ter utilizado lib lombok
 - Aplicou snakecase com camelcase no mapeamento do objetos modelos
 - Desleixo com indentação (linhas em branco) 
 - Implementação de acionamento de processo sem uso
 - Comentarios ingles e outros em portugues
 - Solução complexa para conversão falta de organização (substring(START, END) é complexo ?)
 - Sem tratativas para cenários de erros
 - A solução utilizada foi bem simples não houve armazenamento dos dados
 - Endpoints não fazem sentido a entidade base é customer -> /listorders - /listdates
 - Retorno sem paginação
 - Removeu zeros de ids
 - Formato payload não compatível com o pedido "user"
 - Erro genérico para formato data não esperado


Teste de conhecimento Java REST API
# Padrao estrutura basica MVC
Logica de negócio implementada em classe Service
RestController para fazer as chamadas e apresentar no formato JSON.

# Tecnologias utilizadas
SpringBoot 3.2.5
Java 17
JUnit 5.8.2 

# Build e execução
  Verificar se o JDK instalado é 17.0.8.1 ou superior
   Na tela de terminal :
   - mvn clean verify
   Executar SpringBoot :
   - mvn spring-boot:run

# Para consultas 
 Listar todos os dados no formato JSON:
 -  http://localhost:8080/listdata
 Listar ordem por orderId:
 - 'http://localhost:8080/listorders?orderId=[orderId]'
 Listar por intervalo de Datas:
 Entrar com as datas sem espaços como descrito abaixo:
  Exemplo de datas : startDate=20210909, endDate=20211126
 - 'http://localhost:8080/listdates?startDate=[startDate]&endDate=[endDate]'
