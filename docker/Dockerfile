FROM ubuntu:20.04
ENV DEBIAN_FRONTEND=noninteractive

# Install dependencies and JDK 17
RUN apt-get update -y && \
    apt-get install -y software-properties-common wget curl && \
    apt-get update -y && \
    apt-get install -y build-essential git openjdk-17-jdk

# Install Maven
RUN wget https://archive.apache.org/dist/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.tar.gz && \
    tar xzvf apache-maven-3.8.1-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.8.1/bin/mvn /usr/bin/mvn && \
    rm apache-maven-3.8.1-bin.tar.gz

# Set Maven environment variables
ENV MAVEN_HOME=/opt/apache-maven-3.8.1
ENV PATH=$MAVEN_HOME/bin:$PATH

# Verify installations
RUN java -version && mvn -version
