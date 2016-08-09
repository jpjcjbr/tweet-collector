# Módulo Tweet-collector
Módulo utilizado para coletar dados da api do twitter
Baseado em java, springboot e cassandra

# Build java
mvn clean install

# Build container docker
docker build -t jpjcjbr/tweet-collector .

# Parâmetros básicos do módulo
Podem ser encontrados em src/main/resources/application.properties