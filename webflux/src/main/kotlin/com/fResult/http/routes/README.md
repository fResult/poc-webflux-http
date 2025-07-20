# HTTP - Routes

## Endpoints

| HTTP Method | Root Path | Sub Path (if any)  | Media-Type        |
|:-----------:|:---------:|:------------------:|-------------------|
|     GET     |  /nested  |         -          | application/json  |
|     GET     |  /nested  |         -          | text/event-stream |
|     GET     |  /nested  |       /:name       | application/json  |

### JSON Media Type Response

```console
➜ curl -H "accept: application/json" http://localhost:8080/nested
{"message":"Hello, John!"}⏎
```

```console
➜ curl -H "accept: application/json" http://localhost:8080/nested/John
{"message":"Hello, World!"}⏎
```

### Event Stream Media Type Response

```console
➜ curl -H "accept: text/event-stream" http://localhost:8080/nested
data:#1
data:#2
data:#3
data:#4
data:#5
data:#6
data:#7
data:#8
data:#9
data:#10
data:#11
...
```
