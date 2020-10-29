package com.noirix.service.impl;

import com.noirix.domain.User;
import com.noirix.repository.UserRepository;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    //short form make Autowired
    private final UserRepository userRepository;

    /* the same
    private UserRepositoryImpl userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

     */

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        // 1. Validation Layer
        // 2. Convert http request params into User object
        // 3. Extended calls into DB or external services
        return userRepository.save(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> search(String query) {
        return userRepository.search(query); //Ctrl+Alb+B - go to implementation of method
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }
}
