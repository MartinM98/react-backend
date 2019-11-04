package pw.react.backend.reactbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pw.react.backend.reactbackend.Model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByLogin(String login);
    User findByLogin(String login);
    @Override
    <S extends User> List<S> saveAll(Iterable<S> iterable);
}
