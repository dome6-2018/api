package fr.ensicaen.dome6.api.security;

import fr.ensicaen.dome6.api.user.User;
import fr.ensicaen.dome6.api.user.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecurityController {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Implemented by Spring Security
     */
    @ApiOperation(value = "Login", notes = "Connecte un utilisateur à l'application")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = Authentication.class)})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestParam("username") String username, @RequestParam("password") String password) {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }

    /**
     * Implemented by Spring Security
     */
    @ApiOperation(value = "Logout", notes = "Déconnecte l'utilisateur courant.")
    @ApiResponses({@ApiResponse(code = 200, message = "")})
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout() {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }

    @ApiOperation(value = "Register", notes = "Inscription d'un nouvel utilisateur.")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}