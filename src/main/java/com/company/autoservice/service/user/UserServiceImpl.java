package com.company.autoservice.service.user;


import com.company.autoservice.dtos.request.UserCreateDTO;
import com.company.autoservice.dtos.response.UserResponseDTO;
import com.company.autoservice.entity.Company;
import com.company.autoservice.entity.User;
import com.company.autoservice.enums.Role;
import com.company.autoservice.enums.Status;
import com.company.autoservice.exception.DuplicateValueException;
import com.company.autoservice.exception.ItemNotFoundException;
import com.company.autoservice.repository.UserRepository;
import com.company.autoservice.service.media.MediaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MediaService mediaService;

    @Override
    public UserCreateDTO create(UserCreateDTO userCreateDTO) {
        checkUserUnique(userCreateDTO.getEmail(), userCreateDTO.getPhoneNumber());
//        Company company = companyService.getByID(userCreateDTO.getCompanyID);
        User mappedUser = modelMapper.map(userCreateDTO, User.class);
        mappedUser.setPassword(generatePassword());
        mappedUser.setStatus(Status.ACTIVE);
        mappedUser.setRole(Role.USER);
        if (userCreateDTO.getMediaID() != null)
            mappedUser.setMedia(mediaService.getMediaById(userCreateDTO.getMediaID()));
        User savedUser = userRepository.save(mappedUser);
        return modelMapper.map(savedUser, UserCreateDTO.class);
    }

    @Override
    public UserResponseDTO getByID(Long userID) {
        return modelMapper.map(getUserById(userID), UserResponseDTO.class);
    }

    private User getUserById(Long userID) {
        return userRepository.findById(userID).orElseThrow(
                () -> new ItemNotFoundException("User not found with ID: " + userID)
        );
    }

    private void checkUserUnique(String email, String phoneNumber) {
        if (userRepository.existsByEmail(email))
            throw new DuplicateValueException("User exists with Email: " + email);
        else if (userRepository.existsByPhoneNumber(phoneNumber))
            throw new DuplicateValueException("User exists with Phone number: " + phoneNumber);
    }

    private String generatePassword() {
        return String.valueOf(new Random().nextInt(100000, 1000000));
    }

}