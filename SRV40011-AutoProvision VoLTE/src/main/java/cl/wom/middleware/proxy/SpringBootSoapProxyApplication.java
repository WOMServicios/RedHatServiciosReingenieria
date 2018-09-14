package cl.wom.middleware.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:/spring-camel-context.xml"})
public class SpringBootSoapProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSoapProxyApplication.class, args);
	}
}