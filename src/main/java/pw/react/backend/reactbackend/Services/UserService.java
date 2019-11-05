package pw.react.backend.reactbackend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pw.react.backend.reactbackend.Errors.DoubleLoginException;
import pw.react.backend.reactbackend.Model.User;
import pw.react.backend.reactbackend.Repositories.UserRepository;
import pw.react.backend.reactbackend.Errors.ErrorMessage;
import pw.react.backend.reactbackend.Errors.UserNotFound;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {


    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository UserRepository) {
        this.repository = UserRepository;
    }


    public ResponseEntity<String> createUser(List<User> users) {

        List<User> savedUsers=repository.saveAll(users);
        return ResponseEntity.ok(savedUsers.stream().map(c->String.valueOf(c.getId())).collect(Collectors.joining(",")));
    }




    public ResponseEntity<User> checkUser(String login) {
        return Optional
            .ofNullable( repository.findByLogin(login))
                .map( user -> ResponseEntity.ok().body(user) )
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    void checkLogins(String login1,String login2)
    {
        if(login1==login2)
        {
            List<User> users=repository.findAllByLogin(login1);
            int length=users.size();
            if(length>1)
            {
                throw new DoubleLoginException("Cannot assign this login");
        }
        }
        else
        {
            List<User> users=repository.findAllByLogin(login2);
            int length=users.size();
            if(length>0)
            {
                throw new DoubleLoginException("Cannot assign this login");
            }
        }
    }

    public ResponseEntity<User> updateUser(long id,User user)
    {
        Optional<User> us=repository.findById(id);
        if(us.isPresent()) {
                User userToSave = us.get();
                checkLogins(userToSave.getLogin(),user.getLogin());
                userToSave.setAll(user);
                repository.save(userToSave);
                return ResponseEntity.ok(userToSave);
        }
        else
        {
    throw new UserNotFound("Id: + "+id);
        }
    }


    public ResponseEntity<String> deleteUser(long id)
    {
        Optional<User> us=repository.findById(id);
        if(us.isPresent())
        {
            repository.deleteById(id);
            return ResponseEntity.ok("User with the id: "+id+"is deleted");
        }
        else
        {
            throw new UserNotFound("Id: + "+id);
        }

    }


    @ExceptionHandler({UserNotFound.class})
    public ResponseEntity<ErrorMessage> noEntry(UserNotFound ex) {
        return new ResponseEntity<>(
                new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND.value(), "The user does not exist."),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DoubleLoginException.class})
    public ResponseEntity doubleLogin(DoubleLoginException ex) {
        return new ResponseEntity<>(
                new ErrorMessage(ex.getMessage(), HttpStatus.IM_USED.value(), "The login is already assigned"),
                HttpStatus.IM_USED);
    }


}
