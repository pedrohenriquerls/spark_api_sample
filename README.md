# SparkJava Sample Api
####Techstack
  - Java8
  - Spark
  - Docker
  - Postgres
  
##How to use
###Config
Theres a file called config.java on src/main/java/me/pedrohenriquerls/, before anything is necessary set the docker ip default and ports of db and token to authenticate on api 

###Running
  - make build
  - make run
  - open "http://$(docker-machine ip default):4567/api/v1/alive"

##Api
#####Get: api/v1/alive
>Example:
>curl 192.168.99.100:4567/api/v1/alive

>Response: "OK" if the server running like expected.


#####Get: api/v1/companies/:uuid/details
Params: company_uuid
>Example:
>curl 192.168.99.100:4567/api/v1/companies/b2f84dbb-7535-4438-bfaa-6f3cb60e8045/details?token=37d759acc748941dd27c7eea06f7cbdb

>Response: {"response":{"employees":[],"company_uuid":"b2f84dbb-7535-4438-bfaa-6f3cb60e8045","city":"London","name":"Private Detective","address":"Backer Street","country":"England"},"code":200}

#####Get: api/v1/companies/list
>Example:
>curl 192.168.99.100:4567/api/v1/companies/list?token=37d759acc748941dd27c7eea06f7cbdb

>Response: {"response":[{"company_uuid":"0d2e2dbf-e83f-456b-bbc9-b13178c21a25","name":"teste"},{"company_uuid":"164456cf-77bb-440c-97f7-5272c8c3969a","name":"asdf"}],"code":200}

#####Post: api/v1/companies/create
Params: name, address, city, email, phone_number, country
>Example:
>curl -H "Content-Type: application/json" -X POST -d '{name: "Private Detective",address: "backer street 212",city: "london",country: "england",token:"37d759acc748941dd27c7eea06f7cbdb"}' 192.168.99.100:4567/api/v1/companies/create

>Response: {"response":{"company_uuid":"f3ab97fe-4e06-4b01-be1c-96675fa8b54f","city":"london","name":"Private Detective","address":"backer street 212","country":"england"},"message":"New company perssited.","code":200}

#####Put: api/v1/companies/update
Params: company_uuid, address, city, email, phone_number, country
>Example:
>curl -H "Content-Type: application/json" -X PUT -d '{name: "The Best Private Detective", company_uuid:"f3ab97fe-4e06-4b01-be1c-96675fa8b54f", token:"37d759acc748941dd27c7eea06f7cbdb"}' 192.168.99.100:4567/api/v1/companies/update

>Response: {"response":{"employees":[],"company_uuid":"f3ab97fe-4e06-4b01-be1c-96675fa8b54f","city":"london","name":"The Best Private Detective","address":"backer street 212","country":"england"},"message":"Company f3ab97fe-4e06-4b01-be1c-96675fa8b54f updated.","code":200}

#####Post: api/v1/companies/employee/create
Params: name, company_uuid
>Example:
>curl -H "Content-Type: application/json" -X POST -d '{company_uuid:"f3ab97fe-4e06-4b01-be1c-96675fa8b54f", name: "Sherlock Holmes",token:"37d759acc748941dd27c7eea06f7cbdb"}' 192.168.99.100:4567/api/v1/companies/employee/create

>Response: {"response":{"employee_uuid":"efe22fac-3b9b-404a-8ca2-0d06f7e9f8e7","name":"Sherlock Holmes","company":{"employees":[{"name":"Sherlock Holmes"}], "company_uuid":"f3ab97fe-4e06-4b01-be1c-96675fa8b54f","city":"london","name":"The Best Private Detective","address":"backer street 212","country":"england"}},"message":"Employee from f3ab97fe-4e06-4b01-be1c-96675fa8b54f created.","code":200}
