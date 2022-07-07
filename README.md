# Spring Jpa Projection Demo
This is a demo project for using spring data jpa with projection.

### Java Pojos
```java
@Entity
public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;
}
```

```java
@Entity
public class Address {

    private Long id;
    private String street;
    private String zipCode;
}
```
### Response
What we get from the Jpa repository when asking for a Person.
```json
{
  "id": "7ca32d10-8da9-4334-baf0-9a6c922f4dff",
  "firstName": "kalle",
  "lastName": "anka",
  "address": {
    "id": "0bde6c63-c195-4720-8c5e-bbf21fd0f56b",
    "street": "13 Quack street",
    "zipCode": "13132"
  }
}
```
### Projections
Then we can use projections. Projections are like a view in SQL, 
you decide what columns you want to retrieve from the entity in the database.
<br/>
To define a projection you create am interface that having matching getters 
for the entity.
<br/><br/>
These are called Closed Projection, when the getters matching the getters in the pojo.
```java
public interface PersonView {
    
    String getFirstName();
    String getLastName();
    AddressView getAddress();
}
```
```java
public interface AddressView {
    String getZipCode();
}
```
What we get from the repository with projections.
```json
{
  "firstName": "kalle",
  "lastName": "anka",
  "address": {
    "zipCode": "13132"
  }
}
```
We can also use open projections which don't need the getters to be the same as the entity.
We can use the @Value annotation to write SpEL expression to fetch data from different columns
and combine them.
```java
public interface PersonView {
    
    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();
    AddressView getAddress();
}
```
### How to use projection inside repository
Super easy you just use the projection as a return object.
```java
public interface PersonRepository extends JpaRepository<Person, String> {

    PersonView findByFirstName(String firstName);
}
```
