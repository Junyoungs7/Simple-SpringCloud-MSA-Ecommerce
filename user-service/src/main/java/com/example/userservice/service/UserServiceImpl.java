package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.Users;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(username);
        if(users == null){
            throw new UsernameNotFoundException(username);
        }

        return new User(users.getEmail(), users.getEncryptedPwd(), true, true, true, true, new ArrayList<>());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Users users = mapper.map(userDto, Users.class);
        users.setEncryptedPwd(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(users);

        UserDto responseUserDto = mapper.map(users, UserDto.class);

        return responseUserDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        Users user = userRepository.findByUserId(userId);

        if(user == null) throw new UsernameNotFoundException("User not found");

        UserDto userDto = new ModelMapper().map(user, UserDto.class);
        List<ResponseOrder> orders = new ArrayList<>();
        userDto.setOrders(orders);
        return userDto;
    }

    @Override
    public Iterable<Users> getUserByAll() {
        return userRepository.findAll();
    }


}
