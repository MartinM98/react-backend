package pw.react.backend.reactbackend.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.react.backend.reactbackend.Repositories.UserRepository;
import pw.react.backend.reactbackend.Model.User;

import java.util.List;

@Service
public class UserService{


    private UserRepository repository;

    @Autowired
    public UserService(UserRepository UserRepository) {
        this.repository= UserRepository;
    }


    public void saveUser(String login,String firstName,String lastName)
    {
        repository.save(new User(login,firstName,lastName));
    }

    public boolean checkUser(String login)
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
