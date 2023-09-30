package com.company.autoservice.service.user;

import com.company.autoservice.dtos.request.UserCreateDTO;
import com.company.autoservice.dtos.request.UserUpdateDTO;
import com.company.autoservice.dtos.response.UserResponseDTO;
import org.springframework.data.domain.Page;

public interface UserService {
    UserResponseDTO addEmployee(UserCreateDTO userCreateDTO);

    UserResponseDTO getByID(Long userID);

    Page<UserResponseDTO> getAllPages(Integer page, Integer size);

    Page<UserResponseDTO> getAllByCompanyID(Long companyID, Integer page, Integer size);

    void block(Long userID);

    void unblock(Long userID);

    void delete(Long userID);

    UserResponseDTO update(Long userID, UserUpdateDTO userUpdateDTO);
}
