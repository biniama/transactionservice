#RESTful web service for managing transactions

####RESTful web service that stores some transactions (in memory is fine) and returns information about those transactions.

The transactions to be stored have a type and an amount. The service should support returning all transactions of a type. 
Also, transactions can be linked to each other (using a "parent_id") and we need to know the total amount involved for all transactions linked to a particular transaction.

In detail the api spec looks like the following:

PUT /transactionservice/transaction/$transaction_id

Body:
```json
{ "amount":double,"type":string,"parent_id":long }
```
where:

transaction_id is a long specifying a new transaction

amount is a double specifying the amount

type is a string specifying a type of the transaction.

parent_id is an optional long that may specify the parent transaction of this transaction.

GET /transactionservice/transaction/$transaction_id

Returns:
```json
{ "amount":double,"type":string,"parent_id":long }
```
GET /transactionservice/types/$type

Returns:

[ long, long, .... ]

A json list of all transaction ids that share the same type $type.

GET /transactionservice/sum/$transaction_id

Returns
```json
{ "sum", double }
```
A sum of all transactions that are transitively linked by their parent_id to $transaction_id.

Some simple examples would be:

PUT /transactionservice/transaction/10 { "amount": 5000, "type":"cars" }
```json
{ "status": "ok" }
```
PUT /transactionservice/transaction/11
```json
{ "amount": 10000, "type": "shopping", "parent_id": 10 }
```
=> { "status": "ok" }

GET /transactionservice/types/cars => [10]

GET /transactionservice/sum/10 => {"sum":15000}

GET /transactionservice/sum/11 => {"sum":10000}

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
