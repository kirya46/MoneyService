# MoneyService

# MoneyService

**init database**

Run script **/projectFolder/sql/init.sql**. Use PostgresSQL.

Configure hibernate  `/projectFolder/src/main/resources/hibernate.cfg.xml` 
changet lines:



**request methods**

`GET http://localhost:8080/rest/company/create - return Company json representation`

`POST http://localhost:8080/rest/credit/{companyId}/{creditValue}` - return operation status.

`POST http://localhost:8080/rest/debit/{companyId}/{debitValue}` - return operation status.

**build project**

`gradle war clean  &&  gradle war build`

result `.war` archive search in path `/build/libs/MoneyService-1.0-SNAPSHOT.war`
      
      
    
