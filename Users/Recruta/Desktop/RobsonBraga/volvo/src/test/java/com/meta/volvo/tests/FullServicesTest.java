package com.meta.volvo.tests;

import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.meta.volvo.entities.Department;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestConfiguration.class)
public class FullServicesTest {

	@LocalServerPort
	private int port;

	private String base;

	@Autowired
	private TestRestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = "http://localhost:" + port + "/";
	}

	@Test
	public void createDepartment() throws Exception {
		String command = base + "depts/save";
		URI uri = new URI(command);
		HttpEntity<Department> request = new HttpEntity<>(
				new Department("Financial", "Department create by integration test."));
		ResponseEntity<Department> response = template.exchange(uri, HttpMethod.POST, request, Department.class);
		assertTrue(response.getBody().getId() != null);

		command = base + "depts/delete/" + response.getBody().getId().toString();
		uri = new URI(command);
		response = template.exchange(uri, HttpMethod.DELETE, null, Department.class);
		assertTrue(response.getStatusCode() == HttpStatus.OK);

	}
}
