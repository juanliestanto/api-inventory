# Inventory Management System

Project, an online items request application. This application aims to make it easier for staff and administration to manage requests for items in the office. The staff will request several items from the admin (administration) according to the number of stock items available and the admin can process the request (approve or reject). Admin can manage the quantity received regarding item requests and provide a note.

### ERD(Entity Relational Diagram)
![erd](./Manajemen_Inventory.png)

### Build With
- [Springboot v3.2.4](https://spring.io/)
- [Java 17](hhttps://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [PostgreSQL](https://www.postgresql.org/docs/)
- [Swagger](https://realrashid.github.io/sweet-alert/)
- [Maven](https://maven.apache.org/)

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.

- Java IDEA
- PgAdmin or other PostgreSQL DBMS
- Postman or other API Testing Apps like Swagger.ui

# Features
### Auth
- APP User :
```
class AppUser{
    String id;
    String email;
    String password;
    Role role;
}
```
- Role:
```
class Role{
    String id;
    String role; //enum
}
enum ERole{
    ROLE_STAFF,
    ROLE_ADMIN
}
```

### DEMAND
- Staff:
```
class Staff{
    String id;
    User user;
    String name;
    String phone;
    EDivisi divisi; 
    Boolean isActive;
    List<Demand> demandList;
}
Enum EDivisi{
    HR,
    FINANCE,
    IT,
}
```
- Admin
```
class Admin{
    String id;
    User user;
    String name;
    String phone;
    Boolean isActive;
}
```
- Item
```
class Item{
    String id;
    String name;
    Int stock;
    String unit;
    Boolean isActive;
    List<DemandDetail> demandDetailList;
}
```
- Demand
```
class Demand{
    String id;
    Long createdAt;
    Long updateAt;
    Staff staff;
    List<DemanDetail>demanDetail;
}
```
- Demand Detail
```
class DemandDetail{
    String id;
    Demand demand;
    String updateBy;
    Long updateAt;
    Item item;
    Integer quantityRequest;
    Integer quantityApproved;
    EStatus status;
    String note;
}
Enum EStatus{
    APPROVED,
    REJECTED,
    PENDING
}
```

## API DOCUMENTATION
### Auth
#### Register

Request :

- Endpoint : ```/api/auth/signup```
- Method : POST
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{	
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "email": "string",
    "role": "string"
  }
}
```

#### Login

Request :

- Endpoint : ```/api/auth/signin```
- Method : POST
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "email": "string",
  "password": "string"
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "email": "string",
    "role": "string",
    "token": "string"
  }
}
```
### User

#### Get User By Id

Request

- Endpoint : ```/api/users/{id}```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": {
    "email": "string",
    "role": "string"
  }
}
```
### Staff
#### Get Staff
Request

- Endpoint : ```/api/staff/{id}```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "phone" : "string",
    "divisi" : "string"
  }
}
```

#### Get All Staff

Request

- Endpoint : ```/api/staff```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": [
    {
       "id": "string",
       "name": "string",
       "phone" : "string",
       "divisi" : "string"
    },
    {
       "id": "string",
       "name": "string",
       "phone" : "string",
       "divisi" : "string"
    }
  ]
}
```

#### Update Staff

Request :

- Authorize : `STAFF ONLY`
- Endpoint : ```/api/staff```
- Method : PUT
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
   "id": "12321-xxxxxxx",
   "name": "juan",
   "phone" : "+62291839",
   "divisi" : "FINANCE"
}
```

Response :

```json
{
  "message": "string",
  "data": {
     "id": "12321-xxxxxxx",
     "name": "juan",
     "phone" : "+62291839",
     "divisi" : "FINANCE"
  }
}
```

#### Delete Staff ```*Soft Delete```

Request :

- Authorize : `STAFF ONLY`
- Endpoint : ```/api/staff/{id}```
- Method : DELETE
- Header :
    - Accept: application/json

Response :

```json
{
  "message": "string",
  "data": null
}
```

### Admin
#### Get Admin
Request

- Endpoint : ```/api/admin/{id}```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": {
    "id": "string",
    "name": "string",
    "phone" : "string",
  }
}
```

#### Get All Admin

Request

- Endpoint : ```/api/admin```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": [
    {
       "id": "string",
       "name": "string",
       "phone" : "string",
    },
    {
       "id": "string",
       "name": "string",
       "phone" : "string",
    }
  ]
}
```

#### Update Admin

Request :

- Authorize : `ADMIN ONLY`
- Endpoint : ```/api/admin```
- Method : PUT
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
   "id": "12321-xxxxxxx",
   "name": "juan",
   "phone" : "+62291839",
}
```

Response :

```json
{
  "message": "string",
  "data": {
     "id": "12321-xxxxxxx",
     "name": "juan",
     "phone" : "+62291839",
  }
}
```

#### Delete Admin ```*Soft Delete```

Request :

- Authorize : `ADMIN ONLY`
- Endpoint : ```/api/admin/{id}```
- Method : DELETE
- Header :
    - Accept: application/json

Response :

```json
{
  "message": "string",
  "data": null
}
```

## Item
### Create Item
Request :

- Authorize : `ADMIN ONLY`
- Endpoint : ```/api/item```
- Method : POST
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "item": "string",
  "stock" : integer
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "id": "023jro3j-xxxxx",
    "name": "string",
    "stock" : integer,
    "isActive" : boolean
  }
}
```

#### Get Item By Id

Request :

- Endpoint : ```/api/item/{id}```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": {
    "id": "023jro3j-xxxxx",
    "name": "string",
    "stock" : integer,
    "isActive" : boolean
  }
}
```

#### Get All Item

Request :

- Endpoint : ```/api/item```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": [
    {
      "id": "023jro3j-xxxxx",
      "name": "string",
      "stock" : integer,
      "isActive" : boolean
    },
    {
      "id": "201e0eo3-xxxxx",
      "name": "string",
      "stock" : integer,
      "isActive" : boolean
    }
  ]
}
```

#### Update Item

Request :

- Authorize : `ADMIN ONLY`
- Endpoint : ```/api/item```
- Method : PUT
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "id": "023jro3j-xxxxx",
  "name": "string",
  "stock" : integer
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "id": "023jro3j-xxxxx",
    "name": "string",
    "stock" : integer,
    "isActive" : boolean
  }
}
```

#### Delete Item ```*Soft Delete```

Request :

- Authorize : `ADMIN ONLY`
- Endpoint : ```/api/item/{id}```
- Method : DELETE
- Header :
    - Accept: application/json

Response :

```json
{
  "message": "string",
  "data": null
}
```

## Demand
#### Request Demand
Request :

- Authorize : `STAFF ONLY`
- Endpoint : ```/api/demand```
- Method : POST
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "staffId": "string",
  "item": [
        {
            "itemId" : "string",
            "quantity" : integer
        },
        {
            "itemId" : "string",
            "quantity" : integer
        }
  ]
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "id": "12032KEK3O-xxxxx",
    "staffId": "string",
    "createdAt" : long,
    "updateAt" : long,
    "item": [
        {
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "PENDING"
        },
        {
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "PENDING"
        },
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "PENDING"
  }
}
```

#### Get Demand By Id
Request :

- Endpoint : ```/api/demand/{id}```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": {
    "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
    "staffId": "string",
    "createdAt" : long,
    "updateAt" : long,
    "item": [
        {
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "PENDING"
        },
        {
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "PENDING"
        },
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "PENDING"
  }
}
```

#### Get All Demand
Request :

- Endpoint : ```/api/demand```
- Method : GET
    - Accept: application/json
- Response :

```json
{
  "message": "string",
  "data": {
    "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
    "staffId": "string",
    "createdAt" : long,
    "updateAt" : long,
    "item": [
        {
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "PENDING"
        },
        {
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "PENDING"
        },
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "PENDING"
  }
}
```

#### Approved Request Demand By Admin Id
Request :

- Authorize : `ADMIN ONLY`
- Endpoint : ```/api/demand/{adminId}/approve```
- Method : PUT
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body : 

```json
{
  "demandDetailId": "string",
  "quantity": integer
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
    "staffId": "string",
    "createdAt" : long,
    "updateAt" : long,
    "item": [
        {
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "PENDING"
        },
        {
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "PENDING"
        },
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "APPROVED"
  }
}
```

#### Rejected Request Demand By Admin Id
Request :

- Authorize : `ADMIN ONLY`
- Endpoint : ```/api/demand/{adminId}/rejected```
- Method : PUT
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body : 

```json
{
  "demandDetailId": "string"
}
```

Response :

```json
{
  "message": "string",
  "data": {
    "id": "58aa290c-a84b-48ab-9f18-3fbbeef6bb7d",
    "staffId": "string",
    "createdAt" : long,
    "updateAt" : long,
    "item": [
        {
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "PENDING"
        },
        {
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "APPROVED"
        },
            "id" : "string",
            "itemId" : "string",
            "quantityRequest" : integer,
            "quantityApproved" : integer,
            "updateAt" : long,
            "updateBy" : "string",
            "status" : "REJECTED"
  }
}
```