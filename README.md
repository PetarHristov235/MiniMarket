Use Spring Boot Framework to build REST API with Gradle/Maven and connect it to a relational DB. We want to see your knowledge about REST, OOP, and SQL skills. Please use projections appropriately when interacting with the DB. Please avoid query creation from method names so you can demonstrate your SQL skills and ability to write native and JPQL/HQL queries. 

Write a few unit tests. Do not bother with the security or differentiation of users at that point.

Upload project to GitHub/GitLab.


The Market

The system must operate as a simplified market where users can be buyers or sellers.

Users:

user entity attributes: id:1, username:"User1", account:0

The users can buy and sell items.

Items:

item entity attributes: id:3, name:Item1, ownerId:1.
example for interacting with items endpoints:
create:  {id:1 name:"Item1", ownerId:1};
getAllItems with ownerId = 1 (use single query)
[

   {

      "id":3,

      "name":”Item1”,

      "ownerId":1,

      “ownerUsername”:"User1"

   }

]

Example

"User1" owns "Item1". He wants to sell it for $100. He creates an active contract. Other users can review all active contracts and choose to participate. 
"User2" has enough money in her account and buys "Item1". The contract is now closed. "User1" receives $100 in his account. "User2" is the new owner of "Item1".

Contracts:

contract entity attributes: (The seller is the owner of the item and can not be the buyer)
endpoints - CRUD. Example for interacting with contracts endpoints:
create: {itemId : 3, price : 100}. Expected behavior: find the owner of item with id 3 in the DB (ownerId = 1) persist the new active contract in the DB:
  {

      "sellerId":1,

      "itemId":3,

      "price":100,

      "active":true

   }

update price of active contract by id: {"itemId":3, "price":200}
getAllActive contracts (use single native query):

[

   {

      "sellerId":1,

      “sellerUsername”:"User1",

      "itemId":3,

      "price":200,

      "active":true

   }

]


closing active contract by id {"itemId":3, "buyerId":2}. Expected behavior: update the accounts of users with id 1 and id 2. 
getAllClosed contracts by optional parameters: itemId, sellerId, buyerId (use single native query):

[

   {

"sellerId":1,

“sellerUsername”:"User1",

"buyerId":2,

“buyerUsername”:"User2",

 "itemId":3,

 "price":100,

 "active":false

   }

]


Bonus

getAllItemsByOwnerId (native query), getAllContractsBySellerId (native query)
Integrate with 3rd party currency API and persist currency rates. Now the amounts in the user's accounts and prices of items can be in different currencies:
 USD, EUR, GBP… Example:
EUR/USD rate is 1.2 
"User1" creates a contract to sell "Item1" for USD 60
"User2" has EUR in her account and pays EUR 50 
"User1" receives USD 60 in his account.
Use Swagger for documentation
