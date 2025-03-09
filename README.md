# Repos-listing

Repos Listing is Quarkus application that retrieves user's public repositories (excluding forks) with their branches.

User passes GitHub username to endpoint
```http request
GET /{username}
```
The response is JSON object in following format:
```json
{
  "userRepos": [
    {
      "repositoryName": "Repository Name",
      "owner": "Owner Login",
      "branches": [
        {
          "name": "Branch Name",
          "lastCommitSha": "sha"
        }
      ]
    }
  ]
}
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

## Packaging and running the application

To package the application, run:

```shell script
./mvnw package
```

Once packaged, you can run the application using:
```shell
java -jar target/quarkus-app/quarkus-run.jar
```

## Running with Docker

First, build the project:
```shell script
./mvnw package
```

Then, build the Docker image:
```shell
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/repos-listing-jvm .
```

Finally, run the container:
```shell
docker run -i --rm -p 8080:8080 quarkus/repos-listing-jvm
```