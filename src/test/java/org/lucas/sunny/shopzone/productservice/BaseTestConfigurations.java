package org.lucas.sunny.shopzone.productservice;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseTestConfigurations {

	@LocalServerPort
	protected Integer port;

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
			DockerImageName.parse("postgres:16.3-alpine3.20")).withDatabaseName("project_db")
			.withInitScript("sql/database_setup.sql");

//	@BeforeAll
//	static void beforeAll() {
//		postgres.start();
//	}
//
//	@AfterAll
//	static void afterAll() {
//		postgres.stop();
//		//postgres.close();
//	}
//
//	@DynamicPropertySource
//	static void configureProperties(DynamicPropertyRegistry registry) {
//		registry.add("spring.datasource.url", postgres::getJdbcUrl);
//		registry.add("spring.datasource.username", postgres::getUsername);
//		registry.add("spring.datasource.password", postgres::getPassword);
//	}

}
