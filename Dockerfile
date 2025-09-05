# Usa uma imagem base oficial do OpenJDK com a versão 17.
FROM openjdk:17-jdk-alpine

# Define o diretório de trabalho dentro do container.
WORKDIR /app

# Copia os arquivos do Maven e a pasta .mvn
COPY pom.xml .
COPY .mvn .mvn

# Copia os scripts do Maven Wrapper
COPY mvnw .
COPY mvnw.cmd .

# Copia o código-fonte do projeto
COPY src src

# Define a permissão de execução para o script do Maven Wrapper
RUN chmod +x ./mvnw

# Usa o Maven Wrapper para empacotar a aplicação em um arquivo JAR
RUN ./mvnw clean package -DskipTests

# O comando que será executado quando o container iniciar.
ENTRYPOINT ["java", "-jar", "target/DesafioTecnicoIpass-0.0.1-SNAPSHOT.jar"]

# Expõe a porta que a aplicação vai rodar.
EXPOSE 8080