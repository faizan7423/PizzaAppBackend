package com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advices.AdminNotFoundException;
import com.dto.UserDTO;
import com.entity.AdminEntity;
import com.repository.AdminRepository;
import com.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public UserDTO addAdmin(UserDTO userDTO) {

		AdminEntity admin = new AdminEntity();
		admin.setUsername(userDTO.getUsername());
		admin.setAddress(userDTO.getAddress());
		admin.setEmail(userDTO.getEmail());
		admin.setName(userDTO.getName());
		admin.setNumber(userDTO.getNumber());
		admin.setPassword(userDTO.getPassword());
		admin.setRole(userDTO.getRole());
		admin.setId(userDTO.getId());

		adminRepository.save(admin);
		return userDTO;
	}

	// Update User
	public String updateAdmin(int id, UserDTO adminData) {

		try {
			AdminEntity admin = adminRepository.findById(id).orElseThrow(() -> new AdminNotFoundException());
			if (admin.getUsername() != null)
				admin.setUsername(adminData.getUsername());
			if (admin.getRole() != null)
				admin.setRole(adminData.getRole());
			if (admin.getNumber() != 0)
				admin.setNumber(adminData.getNumber());
			if (admin.getAddress() != null)
				admin.setAddress(adminData.getAddress());
			if (admin.getEmail() != null)
				admin.setEmail(adminData.getEmail());
			if (admin.getName() != null)
				admin.setName(adminData.getName());
			adminRepository.save(admin);

		} catch (AdminNotFoundException e) {

			System.out.println(e);
			return "Admin data not updated";
		}
		return "Admin Updated Successfully";
	}

	// Delete user
	public void deleteAdmin(int id) {
		adminRepository.deleteById(id);
	}

	public UserDTO getAdminByEmail(String email) {
		AdminEntity admin = adminRepository.findByEmail(email);
		UserDTO userDTO = new UserDTO();

		userDTO.setName(admin.getName());
		userDTO.setAddress(admin.getAddress());
		userDTO.setEmail(admin.getEmail());
		userDTO.setNumber(admin.getNumber());
		userDTO.setRole(admin.getRole());
		userDTO.setUsername(admin.getUsername());
		userDTO.setId(admin.getId());

		return userDTO;
	}

}
