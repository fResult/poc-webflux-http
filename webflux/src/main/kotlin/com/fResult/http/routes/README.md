# HTTP - Routes

## Endpoints

| HTTP Method | Root Path | Sub Path (if any)  | Media-Type        |
|:-----------:|:---------:|:------------------:|-------------------|
|     GET     | `/nested` |         -          | application/json  |
|     GET     | `/nested` |         -          | text/event-stream |
|     GET     | `/nested` |      `/:name`      | application/json  |

### JSON Media Type Response

#### GET `/nested`

```console
➜ curl -H "accept: application/json" http://localhost:8080/nested
{"message":"Hello, World!"}⏎
```

#### GET `/nested/:name`

```bash
curl -H "accept: application/json" http://localhost:8080/nested/:name
```

E.g. for `name` as `John`:

```console
➜ curl -H "accept: application/json" http://localhost:8080/nested/John
{"message":"Hello, John!"}⏎
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
.
.
.
```

### Custom Route Predicate Endpoints

#### GET `/test`

```shell
curl localhost:8080/test?uid={{id}}
```

```console
➜ curl localhost:8080/test/3
{"message":"Hello, World!"}⏎
```

#### GET `/greeting/:name`
