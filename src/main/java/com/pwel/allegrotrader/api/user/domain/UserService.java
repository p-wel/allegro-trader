package com.pwel.allegrotrader.api.user.domain;

import com.pwel.allegrotrader.deprecated.AllegroRestClient;
import com.pwel.allegrotrader.api.user.infrastructure.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private AllegroRestClient allegroRestClient;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public void setCurrentUser(){

    }

    public User getCurrentUser(){

        return null;
    }

    private String generateBearerToken(String tenSecondsCode) {
        return allegroRestClient.generateBearerToken(tenSecondsCode);
    }
}
