package com.meta.volvo.tests;

import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
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
import com.meta.volvo.entities.Permission;
import com.meta.volvo.entities.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestConfiguration.class)
public class FullServicesTest {

	private static final Logger LOGGER = Logger.getLogger(FullServicesTest.class);

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
	public void fullCoverage() throws Exception {
		LOGGER.info("Creating departments...");
		Department deptFinance = createDepartment("Finance", "The guys that pay the bills.");
		Department deptIt = createDepartment("IT", "Nerds department.");
		String listService = base + "depts/list/all";
		ResponseEntity<Department[]> depts = template.exchange(listService, HttpMethod.GET, null, Department[].class);

		LOGGER.info("Creating permissions...");
		createPermission("Admin", "System administradors.");
		createPermission("Supervisor", "The big brother.");
		createPermission("Developer", "Program writter.");
		listService = base + "permissions/list/all";
		ResponseEntity<Permission[]> permissions = template.exchange(listService, HttpMethod.GET, null,
				Permission[].class);
		Set<Permission> permSet = new HashSet<Permission>(Arrays.asList(permissions.getBody()));

		LOGGER.info("Creating users...");
		createUser("Robson Braga", "Java Developer", deptIt, permSet);
		createUser("Deborah Rasim", "Random User #1", deptFinance, permSet);
		createUser("Lysimachus Róisín", "Random User #2", deptIt, permSet);
		createUser("Bryon Brandie", "Random User #3", deptFinance, permSet);
		listService = base + "users/list/all";
		ResponseEntity<User[]> users = template.exchange(listService, HttpMethod.GET, null, User[].class);

		LOGGER.info("Removing test data.");
		LOGGER.info("Removing users.");
		for (int i = 0; i < users.getBody().length; i++) {
			User user = users.getBody()[i];
			removeUser(user.getId());
		}

		LOGGER.info("Removing permissions.");
		for (Permission permission : permSet) {
			removePermission(permission.getId());
		}

		LOGGER.info("Removing departments.");
		for (int i = 0; i < depts.getBody().length; i++) {
			Department dept = depts.getBody()[i];
			removeDepartment(dept.getId());
		}

	}

	private Department createDepartment(String name, String description) throws URISyntaxException {
		String command = base + "depts/save";
		URI uri = new URI(command);
		LOGGER.info("Calling " + uri.toString());
		HttpEntity<Department> deptRequest = new HttpEntity<>(new Department(name, description));
		ResponseEntity<Department> deptResponse = template.exchange(uri, HttpMethod.POST, deptRequest,
				Department.class);
		assertTrue(deptResponse.getBody().getId() != null);
		LOGGER.info("Department created successfully.");

		return deptResponse.getBody();
	}

	private void removeDepartment(Long deptId) throws URISyntaxException {
		LOGGER.info("Removing department id " + deptId);
		String command = base + "depts/delete/" + deptId;
		URI uri = new URI(command);
		LOGGER.info("Calling " + uri.toString());
		ResponseEntity<Department> deptResponse = template.exchange(uri, HttpMethod.DELETE, null, Department.class);
		assertTrue(deptResponse.getStatusCode() == HttpStatus.OK);
		LOGGER.info("Department removed successfully.");
	}

	private Permission createPermission(String name, String description) throws URISyntaxException {
		String command = base + "permissions/save";
		URI uri = new URI(command);
		LOGGER.info("Calling " + uri.toString());
		HttpEntity<Permission> permRequest = new HttpEntity<>(new Permission(name, description));
		ResponseEntity<Permission> permResponse = template.exchange(uri, HttpMethod.POST, permRequest,
				Permission.class);
		assertTrue(permResponse.getBody().getId() != null);
		LOGGER.info("Permission created successfully.");

		return permResponse.getBody();
	}

	private void removePermission(Long permId) throws URISyntaxException {
		LOGGER.info("Removing permission id " + permId);
		String command = base + "permissions/delete/" + permId.toString();
		URI uri = new URI(command);
		LOGGER.info("Calling " + uri.toString());
		ResponseEntity<Permission> permResponse = template.exchange(uri, HttpMethod.DELETE, null, Permission.class);
		assertTrue(permResponse.getStatusCode() == HttpStatus.OK);
		LOGGER.info("Permission removed successfully.");
	}

	private User createUser(String name, String description, Department dept, Set<Permission> permissions)
			throws URISyntaxException {
		String command = base + "users/save";
		URI uri = new URI(command);
		LOGGER.info("Calling " + uri.toString());
		User user = new User(name, description, dept, permissions);
		HttpEntity<User> userRequest = new HttpEntity<>(user);
		ResponseEntity<User> userResponse = template.exchange(uri, HttpMethod.POST, userRequest, User.class);
		assertTrue(userResponse.getBody().getId() != null);
		LOGGER.info("User created successfully.");

		return userResponse.getBody();
	}

	private void removeUser(Long userId) throws URISyntaxException {
		LOGGER.info("Removing user id " + userId);
		String command = base + "users/delete/" + userId.toString();
		URI uri = new URI(command);
		LOGGER.info("Calling " + uri.toString());
		ResponseEntity<User> userResponse = template.exchange(uri, HttpMethod.DELETE, null, User.class);
		assertTrue(userResponse.getStatusCode() == HttpStatus.OK);
		LOGGER.info("User removed successfully.");
	}

}
