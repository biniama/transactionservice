# Sample APIs and responses

```json
[~]$ curl -i -X PUT -H "Content-Type:application/json" -d '{"type":"car", "amount":10.0}' http://localhost:8080/transactions/1
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 13 Oct 2016 20:35:24 GMT

{"id":1,"type":"car","amount":10.0,"parentId":null}
[~]$ curl -i -X PUT -H "Content-Type:application/json" -d '{"type":"car", "amount":20.0, "parentId": 1}' http://localhost:8080/transactions/2HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 13 Oct 2016 20:35:29 GMT

{"id":2,"type":"car","amount":20.0,"parentId":1}
[~]$ curl -i -X PUT -H "Content-Type:application/json" -d '{"type":"house", "amount":300.0, "parentId": 2}' http://localhost:8080/transactions/3
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 13 Oct 2016 20:35:41 GMT

{"id":3,"type":"house","amount":300.0,"parentId":2}
[~]$ curl -i -X GET -H "Content-Type:application/json" http://localhost:8080/types/car
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 13 Oct 2016 20:35:47 GMT

[1,2]
[~]$ curl -i -X GET -H "Content-Type:application/json" http://localhost:8080/types/house
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 13 Oct 2016 20:35:51 GMT

[3]
[~]$ curl -i -X GET -H "Content-Type:application/json" http://localhost:8080/sum/1
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 13 Oct 2016 20:35:57 GMT

{"sum":30.0}
[~]$ curl -i -X GET -H "Content-Type:application/json" http://localhost:8080/sum/2
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 13 Oct 2016 20:36:04 GMT

{"sum":320.0}
[~]$ curl -i -X GET -H "Content-Type:application/json" http://localhost:8080/sum/3
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 13 Oct 2016 20:36:07 GMT

{"sum":300.0}
```
