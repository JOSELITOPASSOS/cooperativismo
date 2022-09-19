# Cooperativismo
 No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias,
por votação.   
 Essa solução backend para gerenciar essas sessões de votação.  

 Tópicos abordados:  
 ● Cadastrar uma nova pauta;  
 ● Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um
tempo determinado na chamada de abertura ou 1 minuto por default).  
 ● Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado
é identificado por um id único e pode votar apenas uma vez por pauta).  
 ● Contabilizar os votos e dar o resultado da votação na pauta.  
 ● Integração com sistema externo para verifique, a partir do CPF do associado, se ele pode
 votar.  
 ● Mensageria e filas usando RabbitMQ.  
 ● Desenvolvimento de testes unitários.  

 ### Para executar o projeto no terminal, digite o seguinte comando:
 ```shell script
mvn spring-boot:run 
```
 ### Para executar o RabbitMQ 
```
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.10-management
```
### Collection do postman com as requisições das APIs 
```
 \src\main\resources\postman
```
