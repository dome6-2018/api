package fr.ensicaen.dome6.api.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;

@RestController
@RequestMapping("/me")
public class MeController {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public MeController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @ApiOperation(value = "Récupération de l'utilisateur connecté.")
    @RequestMapping(method = RequestMethod.GET)
    public User getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (User) authentication.getPrincipal();
    }

    @ApiOperation(value = "Modification de l'utilisateur connecté.")
    @RequestMapping(method = RequestMethod.PATCH)
    public void updateMe(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User actualUser = (User) authentication.getPrincipal();

        if (user == null) {
            throw new NoResultException();
        }

        if (user.getLastname() != null) {
            actualUser.setLastname(user.getLastname());
        }
        if (user.getFirstname() != null) {
            actualUser.setFirstname(user.getFirstname());
        }
        if (user.getEmail() != null) {
            actualUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            actualUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        userRepository.save(actualUser);
    }
}