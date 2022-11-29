package org.iiitb.userLogin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iiitb.userLogin.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
@RequiredArgsConstructor
class SplitwiseApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("");
	}
	@Test
	void validLogin() {
		String loginURL = "http://localhost:8080/login";
		User request = new User();
		request.setEmail("aman@gmail.com");
		request.setPassword("password");
		log.info("Valid input : " + request);
		User response = restTemplate.postForObject(loginURL, request, User.class);
		log.info("Valid output : " + response);
		if(response != null) System.out.println("Login success : email = " + request.getEmail());
		Assert.assertNotNull(response);
	}
	@Test
	void invalidLoginByPassword() {
		String loginURL = "http://localhost:8080/login";
		User request = new User();
		String randomPassword = UUID.randomUUID().toString().substring(0,7);
		System.out.println(randomPassword);
		request.setEmail("kunal@gmail.com");
		request.setPassword(randomPassword);

		log.info("Valid input : " + request);
		User response = restTemplate.postForObject(loginURL, request, User.class);
		log.info("Valid output : " + response);
		if(response == null)
		{
			System.out.println("Is email valid? : true");
			System.out.println("Is password valid? : false");
			System.out.println("User Login failed");
		}
		Assert.assertNull(response);
	}
	@Test
	void invalidLoginByEmail() {
		String loginURL = "http://localhost:8080/login";
		User request = new User();
		String randomEmail = UUID.randomUUID().toString().substring(0,7) + "@gmail.com";

		System.out.println(randomEmail);
		request.setEmail(randomEmail);
		request.setPassword("password");

		log.info("Valid input : " + request);
		User response = restTemplate.postForObject(loginURL, request, User.class);
		log.info("Valid output : " + response);
		if(response == null)
		{
			System.out.println("Is email valid? : false");
			System.out.println("Is password valid? : false");
			System.out.println("User Login failed");
		}
		Assert.assertNull(response);
	}
	@Test
	void validRegistration(){
			String registrationURL = "http://localhost:8080/register";
		String randomEmail = UUID.randomUUID().toString().substring(0,7) + "@gmail.com";
		User request = new User();
		request.setUsername(UUID.randomUUID().toString().substring(0,7));
		request.setPassword("sample5");
		request.setName("sample");
		request.setEmail(randomEmail);
		log.info("Valid input : " + request);
		User response = restTemplate.postForObject(registrationURL, request, User.class);
		log.info("Valid output : " + response);
		if(response != null) System.out.println("Registration successful with username = " + request.getUsername());
		Assert.assertNotNull(response);
	}
	@Test
	void invalidRegistrationByEmail(){
		String registrationURL = "http://localhost:8080/register";
		String randomEmail = UUID.randomUUID().toString().substring(0,7) + "@gmail.";
		User request = new User();
		request.setUsername(UUID.randomUUID().toString().substring(0,7));
		request.setPassword("sample5");
		request.setName("sample");
		request.setEmail(randomEmail);
		log.info("Invalid input : " + request);
		User response = restTemplate.postForObject(registrationURL, request, User.class);
		log.info("Invalid output : " + response);
		if(response == null) System.out.println("User Registration failed");
		Assert.assertNull(response);
	}
	@Test
	void invalidRegistrationByPassword(){
		String registrationURL = "http://localhost:8080/register";
		String randomEmail = UUID.randomUUID().toString().substring(0,7) + "@gmail.com";
		User request = new User();
		request.setUsername(UUID.randomUUID().toString().substring(0,7));
		request.setPassword("sample56789");
		request.setName("sample");
		request.setEmail(randomEmail);
		log.info("Valid input : " + request);
		User response = restTemplate.postForObject(registrationURL, request, User.class);
		log.info("Valid output : " + response);
		if(response == null) System.out.println("User Registration failed");
		Assert.assertNull(response);
	}


}
