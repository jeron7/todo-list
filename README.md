# todo-list

Application to manage and track daily tasks.

## Objectives

Want to practice in a project some concepts and tools that I studied lately, like Clean Architecture, TDD (Test-Driven Development) and Spring Framework.

### REST API

At the end of this project is expected to have the following endpoints:

- ```POST /users``` create and persist a user in the system.
- ```GET /users/{id}``` recover a user persisted in the system.
- ```PUT /users/{id}``` update a user persisted in the system.
- ```DELETE /users/{id}``` remove a user persisted in the system.

- ```POST /tasks``` create and persist a task in the system.
- ```GET /tasks/{id}``` recover a task persisted in the system.
- ```PUT /tasks/{id}``` update a task persisted in the system.
- ```DELETE /tasks/{id}``` remove a task persisted in the system.

## How to execute

To execute the project you will need to have Docker installed in our machine. Then run:

```shell
docker compose up --build -d
```

### Get application logs

```shell
docker logs todo-list-app-1 -f
```

### Stop application

```shell
docker compose down
```
