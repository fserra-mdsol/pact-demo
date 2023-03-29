.PHONY: all
all: buildall testall 

buildall:
	cd pact-broker-docker; docker-compose up -d

testall: test_consumer publish_consumer test_provider

test_consumer:
	cd pact-react-consumer; npm run test:pact

publish_consumer:
	cd pact-react-consumer; npm run publish:pact

test_provider:
	cd pact-scala-provider; sbt test

cleanall:
	cd pact-broker-docker; docker-compose down -v

ps:
	cd pact-broker-docker; docker-compose ps

rebuild: cleanall buildall
