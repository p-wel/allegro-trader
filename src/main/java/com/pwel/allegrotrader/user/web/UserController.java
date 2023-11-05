package com.pwel.allegrotrader.user.web;

import com.pwel.allegrotrader.allegro.AllegroRestClient;
import com.pwel.allegrotrader.user.domain.User;
import com.pwel.allegrotrader.user.domain.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("api/1/users")
public class UserController {

    private UserService userService;
    private AllegroRestClient allegroRestClient;

    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("profile")
    public User getUser(@RequestBody Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("authenticate")
    public void authenticate(@RequestParam String code) {
        if (!code.isEmpty()) {
            String bearerToken = allegroRestClient.generateBearerToken(code);
        }
        userService.getUserById(1L);
    }
}
