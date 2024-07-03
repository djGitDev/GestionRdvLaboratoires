# Start from the latest Ubuntu image
FROM ubuntu:latest

# Set the label
LABEL authors="Alter"

# Install dependencies
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    && apt-get clean

# Set the default command to run when the container starts
ENTRYPOINT ["top", "-b"]
