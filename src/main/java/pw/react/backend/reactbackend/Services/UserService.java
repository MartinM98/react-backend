package pw.react.backend.reactbackend.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pw.react.backend.reactbackend.Model.User;
import pw.react.backend.reactbackend.Repositories.UserRepository;

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




}
