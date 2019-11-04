package pw.react.backend.reactbackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.reactbackend.Services.UserService;
import pw.react.backend.reactbackend.Model.User;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController
{

   private final UserService userService;

    @Autowired
    public UserController(UserService loginService) {
        this.userService = loginService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody List<User> users)
    {

        return userService.createUser(users);

    }


    @GetMapping("/{Login}")
    public ResponseEntity<User> check(@PathVariable String Login)
    {
    return userService.checkUser(Login);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable long id,@Valid @RequestBody User user)
    {
        return userService.updateUser(id,user);
    }






}
