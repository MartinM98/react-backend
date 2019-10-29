package pw.react.backend.reactbackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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


    @PostMapping("/create")
    public String create(@RequestBody User user)
    {
        boolean message;
        message =userService.checkUser(user.getLogin());
        if(message)
        {
            return "User already exists";
        }
        else {
            userService.saveUser(user.getLogin(), user.getfirstName(), user.getlastName());
            return "New user added";
        }

    }


}
