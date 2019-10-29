package pw.react.backend.reactbackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.reactbackend.Services.UserService;
import pw.react.backend.reactbackend.Model.User;


@RestController
public class UserController
{


   private UserService userService;

    @Autowired
    public UserController(UserService loginService) {
        this.userService = loginService;
    }



//    @PostMapping("/check")
//    public String check()
//    {
//        return "Checked";
//    }

    @GetMapping("/checkuser")
    public String checkuser(@RequestBody User user)
    {
        if(userService.checkIfUserExists2(user))
        {
            return "This user exists";
        }
        else
        {
            return "User doesn't exist";
        }
    }
    @DeleteMapping("/deleteuser/{login}")
    public String deleteUser(@PathVariable(value = "login") String login)
    {
        String message=userService.deleteUserService(login);
        return message;

    }

    @PostMapping("/create")
    public String create(@RequestBody User user)
    {
        boolean message;
        message =userService.checkIfUserExists(user.getLogin());
        if(message)
        {
            return "User with this login already exists";
        }
        else {

           userService.saveUser(user);
           return "New user added";
        }

    }


}
