package com.company.autoservice.service.user;

import  com.company.autoservice.dtos.request.UserCreateDTO;
import com.company.autoservice.dtos.response.UserResponseDTO;
import com.company.autoservice.entity.Company;
import com.company.autoservice.entity.User;
import com.company.autoservice.enums.Role;
import com.company.autoservice.enums.Status;
import com.company.autoservice.exception.DuplicateValueException;
import com.company.autoservice.exception.ItemNotFoundException;
import com.company.autoservice.repository.UserRepository;
import com.company.autoservice.service.company.CompanyService;
import com.company.autoservice.service.media.MediaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MediaService mediaService;
    private final CompanyService companyService;

    @Override
    public UserResponseDTO addEmployee(UserCreateDTO userCreateDTO) {
        checkUserUnique(userCreateDTO.getEmail(), userCreateDTO.getPhoneNumber());
        Company company = companyService.getByID(userCreateDTO.getCompanyID());

        User mappedUser = modelMapper.map(userCreateDTO, User.class);
        mappedUser.setPassword(generatePassword());
        mappedUser.setStatus(Status.ACTIVE);
        mappedUser.setRole(Role.USER);
        mappedUser.setCompany(company);

        if (userCreateDTO.getMediaID() != null)
            mappedUser.setMedia(mediaService.getMediaById(userCreateDTO.getMediaID()));
        User savedUser = userRepository.save(mappedUser);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO getByID(Long userID) {
        return modelMapper.map(getUserById(userID), UserResponseDTO.class);
    }

    @Override
    public Page<UserResponseDTO> getAllPages(Integer page, Integer size) {
        return userRepository.findAll(PageRequest.of(page, size))
                .map(this::toDTO);
    }

    @Override
    public Page<UserResponseDTO> getAllByCompanyID(Long companyID, Integer page, Integer size) {
        if (companyService.getByID(companyID) != null)
            return userRepository.findAllByCompanyId(companyID, PageRequest.of(page, size))
                    .map(this::toDTO);
        return null;
    }

    @Override
    public void block(Long userID) {
        userRepository.blockUser(userID);
    }

    @Override
    public void unblock(Long userID) {
        userRepository.unblockUser(userID);
    }

    @Override
    public void delete(Long userID) {
        if (userRepository.existsById(userID))
            throw new ItemNotFoundException("User not found with ID: " + userID);
        userRepository.deleteById(userID);
    }

    private UserResponseDTO toDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
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