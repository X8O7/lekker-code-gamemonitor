package com.gamemonitor.service;

import com.gamemonitor.domain.dto.CredentialsDTO;
import com.gamemonitor.domain.dto.RegisterUserDTO;
import com.gamemonitor.domain.dto.UserDTO;
import com.gamemonitor.domain.model.UserEntity;
import com.gamemonitor.domain.repository.UserRepository;
import com.gamemonitor.security.Role;
import com.gamemonitor.security.UserCredentialsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class UserService implements UserCredentialsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) {
                return userRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public UserDTO getUserById(String id) {
        if (!id.isEmpty()) {
            var optional =  userRepository.findById(Long.valueOf(id));
            if (optional.isPresent()) {
                var user = optional.get();
                return mapEntityToDto(user);
            }
        }
        return null;
    }

    public UserEntity getUserEntity(Long id) {
        var optional =  userRepository.findById(id);
        return optional.orElse(null);
    }

    public List<UserDTO> getUsers() {
        var users = userRepository.findAll();

        return StreamSupport.stream(users.spliterator(), false)
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public UserDTO createUser(RegisterUserDTO registerUserDTO) {
        var userExists = userRepository.findByEmail(registerUserDTO.email());

        if (userExists.isPresent()) {
            return mapEntityToDto(userExists.get());
        }

        var newUser = new UserEntity();
        newUser.setRole(Role.USER);
        newUser.setName(registerUserDTO.name());
        newUser.setEmail(registerUserDTO.email());
        newUser.setPassword(passwordEncoder.encode(registerUserDTO.password()));
        newUser.setScore(new Random().nextInt(10));

        var savedUser = userRepository.save(newUser);
        return mapEntityToDto(savedUser);
    }

    private UserDTO mapEntityToDto(UserEntity userEntity) {
        return new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getRank(), userEntity.getScore());
    }

    public CredentialsDTO getUserByEmail(String email) {
        var optional = userRepository.findByEmail(email);

        if (optional.isPresent()) {
            var user = optional.get();
            return new CredentialsDTO(user.getEmail(), user.getPassword(), mapEntityToDto(user));
        }
        return null;
    }
}
