import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    @Autowired
    UserRepository repository;

    @PostMapping("/create")
    public String create(@RequestBody User user)
    {
        repository.save(new User(user.getfirstName(),user.getlastName()));
        return "User created";
    }


}
