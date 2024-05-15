# Desafio Luiza Labs - Luiz Octavio Godoy

Teste de conhecimento Java REST API

# Para executar no IntelliJ IDE
  No maven usar o plugin spring-boot:
    - spring-boot:run

# Para consultas 
 Listar todos os dados no formato JSON:
 -  http://localhost:8080/listdata
 Listar ordem por orderId:
 - http://localhost:8080/listorders?orderId=<orderId>
 Listar por intervalo de Datas:
 Entrar com as datas sem espaços como descrito abaixo:
 - http://localhost:8080/listdates?startDate=20210909&endDate=20211126
