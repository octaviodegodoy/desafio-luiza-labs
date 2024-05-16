# Desafio Luiza Labs - Luiz Octavio Godoy

Teste de conhecimento Java REST API

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
 - "http://localhost:8080/listorders?orderId=orderId"
 Listar por intervalo de Datas:
 Entrar com as datas sem espaços como descrito abaixo:
  Exemplo de datas : startDate=20210909, endDate=20211126
 - 'http://localhost:8080/listdates?startDate=<startDate>&endDate=<endDate>'
