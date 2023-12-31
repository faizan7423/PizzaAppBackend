package com.pizza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.dto.CategoryDTO;
import com.dto.UserDTO;
import com.entity.AdminEntity;
import com.entity.Category;
import com.entity.CustomerEntity;
import com.repository.AdminRepository;
import com.repository.CategoryRepository;
import com.repository.CustomerRepository;
import com.serviceImpl.AdminServiceImpl;
import com.serviceImpl.CategoryServiceImpl;
import com.serviceImpl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class PizzaBokkingAppApplicationTests {

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;
	@InjectMocks
	CategoryServiceImpl categoryServiceImpl;
	@InjectMocks
	AdminServiceImpl adminServiceImpl;

	@Mock
	CustomerRepository customerRepository;

	@Mock
	CategoryRepository categoryRepository;
	@Mock
	AdminRepository adminRepository;

	@Test
	public void getCustomersTest() {
		when(customerRepository.findAll()).thenReturn(Stream
				.of(new CustomerEntity(1, "abc", "123", "PM", "xyz", "pqr@email", (long) 123, "AP"),
						new CustomerEntity(2, "abc", "123", "PM", "xyz", "pqqqr@email", (long) 123, "AP"))
				.collect(Collectors.toList()));
		assertEquals(2, customerServiceImpl.readAllCustomers().size());
	}

	@Test
	public void addCustomerTest() {
		CustomerEntity customer = new CustomerEntity(3, "pqw", "123", "SE", "xyz", "wwr@email", (long) 123, "AP");
		UserDTO userDTO = new UserDTO(3, "pqw", "123", "SE", "xyz", "wwr@email", (long) 123, "AP");
		when(customerRepository.save(customer)).thenReturn(customer);
		UserDTO reuserDTO = customerServiceImpl.registerCustomer(userDTO);
		assertEquals(reuserDTO.getUsername(), customer.getUsername());
		assertEquals(reuserDTO.getRole(), customer.getRole());
		assertEquals(reuserDTO.getEmail(), customer.getEmail());
		assertEquals(reuserDTO.getId(), customer.getId());

	}

	@Test
	public void deleteCustomerTest() {

		doNothing().when(customerRepository).deleteById(99);

		customerServiceImpl.deleteCustomer(99);

		verify(customerRepository, times(1)).deleteById(99);

	}

	@Test
	public void addCategoryTest() {
		Category category = new Category(3, "pqw");
		CategoryDTO categoryDTO = new CategoryDTO(3, "pqw");
		when(categoryRepository.save(category)).thenReturn(category);
		CategoryDTO recategoryDTO = categoryServiceImpl.addCategory(categoryDTO);
		assertEquals(recategoryDTO.getCategoryId(), category.getCategoryId());
		assertEquals(recategoryDTO.getCategoryName(), category.getCategoryName());

	}

	@Test
	public void deleteCategoryTest() {

		// Mock the repository's deleteById method
		doNothing().when(categoryRepository).deleteById(99);

		// Call the service to delete the product
		categoryServiceImpl.removeCategory(99);

		// Verify that the deleteById method was called with the correct ID
		verify(categoryRepository, times(1)).deleteById(99);

	}

	@Test
	public void findByCategoryIdTest() {
		Category category = new Category(3, "pqw");
		when(categoryRepository.findById(3)).thenReturn(Optional.of(category));
		CategoryDTO recategoryDTO = categoryServiceImpl.searchCategoryById(3);
		assertEquals(recategoryDTO.getCategoryId(), category.getCategoryId());
		assertEquals(recategoryDTO.getCategoryName(), category.getCategoryName());

	}

	@Test
	public void findByCategoryNameTest() {
		Category category = new Category(3, "pqw");
		when(categoryRepository.findByCategoryName("pqw")).thenReturn(category);
		CategoryDTO recategoryDTO = categoryServiceImpl.seachCategoryByName("pqw");
		assertEquals(recategoryDTO.getCategoryId(), category.getCategoryId());
		assertEquals(recategoryDTO.getCategoryName(), category.getCategoryName());

	}

	@Test
	public void addAdminTest() {
		AdminEntity admin = new AdminEntity(3, "pqw", "123", "SE", "xyz", "wwr@email", (long) 123, "AP");
		UserDTO userDTO = new UserDTO(3, "pqw", "123", "SE", "xyz", "wwr@email", (long) 123, "AP");
		when(adminRepository.save(admin)).thenReturn(admin);
		UserDTO reuserDTO = adminServiceImpl.addAdmin(userDTO);
		assertEquals(reuserDTO.getUsername(), admin.getUsername());
		assertEquals(reuserDTO.getRole(), admin.getRole());
		assertEquals(reuserDTO.getEmail(), admin.getEmail());
		assertEquals(reuserDTO.getId(), admin.getId());

	}

	@Test
	public void findAdminByEmailTest() {
		AdminEntity admin = new AdminEntity(3, "pqw", "123", "SE", "xyz", "wwr@email", (long) 123, "AP");
		when(adminRepository.findByEmail("wwr@email")).thenReturn(admin);
		UserDTO reuserDTO = adminServiceImpl.getAdminByEmail("wwr@email");
		assertEquals(reuserDTO.getUsername(), admin.getUsername());
		assertEquals(reuserDTO.getRole(), admin.getRole());
		assertEquals(reuserDTO.getEmail(), admin.getEmail());
		assertEquals(reuserDTO.getId(), admin.getId());

	}

}
