package com.company.autoservice.service.user;

import com.company.autoservice.dtos.request.UserCreateDTO;
import com.company.autoservice.dtos.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO addEmployee(UserCreateDTO userCreateDTO);

    UserResponseDTO getByID(Long userID);

}
