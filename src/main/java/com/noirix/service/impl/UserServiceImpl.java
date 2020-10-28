package com.noirix.service.impl;

import com.noirix.domain.User;
import com.noirix.repository.impl.UserRepositoryImpl;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    //short form make Autowired
    private final UserRepositoryImpl userRepository;

    /* the same
    private UserRepositoryImpl userRepository;

    public UserServiceImpl(UserRepositoryImpl userRepository) {
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
}
