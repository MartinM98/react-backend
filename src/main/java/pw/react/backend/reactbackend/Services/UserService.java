package pw.react.backend.reactbackend.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.react.backend.reactbackend.Repositories.UserRepository;
import pw.react.backend.reactbackend.Model.User;

import java.util.Date;
import java.util.List;

@Service
public class UserService{


    private UserRepository repository;

    @Autowired
    public UserService(UserRepository UserRepository) {
        this.repository= UserRepository;
    }


    public void saveUser(User user)
    {
        repository.saveAndFlush(user);
    }

    public boolean checkIfUserExists2(User user)
    {
        List<User> list=repository.findAllByLogin(user.getLogin());

        if(list.isEmpty()) {
            return false;
        }
        else
        {
            return true;
        }
    }

public String deleteUserService(String login)
{
    User user=repository.findOneByLogin(login);
    if(user!=null) {
        repository.delete(user);
        return "User deleted";
    }
    else
    {
        return "User doesn't exist";
    }
}

    public boolean checkIfUserExists(String login)
    {
        List<User> list=repository.findAllByLogin(login);
        if(list.isEmpty()) {
           return false;
       }
      else
       {
            return true;
      }
    }


}
