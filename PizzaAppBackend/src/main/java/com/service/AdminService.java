package com.service;

import com.dto.UserDTO;

public interface AdminService {

	public UserDTO addAdmin(UserDTO adminDTO);

	public String updateAdmin(int id, UserDTO adminDTO);

	public UserDTO getAdminByEmail(String email);

}
