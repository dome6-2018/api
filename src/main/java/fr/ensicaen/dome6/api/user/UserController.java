package fr.ensicaen.dome6.api.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "Récupération de tous les utilisateurs.")
    @RequestMapping(method = RequestMethod.GET)
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @ApiOperation(value = "Récupération d'un utilisateur.")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public User getUser(@PathVariable("userId") int userId) {
        User user = userRepository.findById(userId);

        if (user == null) {
            throw new NoResultException();
        }

        return user;
    }

    @ApiOperation(value = "Modification d'un utilisateur.")
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateUser(@PathVariable("userId") int userId, @RequestBody User user) {
        User actualUser = userRepository.findById(userId);

        if (user == null || actualUser.equals(user)) {
            throw new NoResultException();
        }

        userRepository.save(user);
    }

    @ApiOperation(value = "Suppression d'un utilisateur.")
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable("userId") int userId) {
        userRepository.deleteById(userId);
    }
}