package com.famjam.famjam;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import com.famjam.famjam.config.TestSecurityConfig;
import com.famjam.famjam.BaseTest;

@SpringBootTest(properties = {
		"spring.jpa.hibernate.ddl-auto=create-drop",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.main.allow-bean-definition-overriding=true",
		"spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
		"jwt.secret=testsecretkeytestsecretkeytestsecretkeytestsecretkey",
		"jwt.expiration=3600000"
})
@Import(TestSecurityConfig.class)
public class FamjamApplicationTests extends BaseTest {

	@Test
	void contextLoads() {
	}

}
