
build:
	docker-compose run web mvn test; docker-compose build web

run:
	docker-compose run -p 4567:4567 web

builddb:
	docker-compose build sampletest_db

clean:
	docker-compose run web mvn clean