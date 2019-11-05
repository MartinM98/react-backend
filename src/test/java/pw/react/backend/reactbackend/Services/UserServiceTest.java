package pw.react.backend.reactbackend.Services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import pw.react.backend.reactbackend.Errors.DoubleLoginException;
import pw.react.backend.reactbackend.Model.User;
import pw.react.backend.reactbackend.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {



    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    @Test(expected = DoubleLoginException.class)
    public void givenTheSameLoginAndAnotherUserWithTheLogin_whenCheckLoginsForUserUpdate_theReturnDoubleLoginException()
    {
        List<User> users=new ArrayList<>();
        users.add(new User());
        users.add(new User());
        when(repository.findAllByLogin(any(String.class))).thenReturn(users);
        userService.checkLogins("Login","Login");
        verify(repository,times(1)).findAllByLogin(any(String.class));

    }

    @Test(expected = DoubleLoginException.class)
    public void givenDifferentLoginAndAnotherUserWithTheLogin_whenCheckLoginsForUserUpdate_theReturnDoubleLoginException()
    {
        List<User> users=new ArrayList<>();
        users.add(new User());
        users.add(new User());
        when(repository.findAllByLogin(any(String.class))).thenReturn(users);
        userService.checkLogins("Login","Login2");
        verify(repository,times(1)).findAllByLogin(any(String.class));

    }
    @Test
    public void givenTheSameLogin_whenCheckLoginsForUserUpdate_theReturnDoubleLoginException()
    {
        List<User> users=new ArrayList<>();
        users.add(new User());

        when(repository.findAllByLogin(any(String.class))).thenReturn(users);
        userService.checkLogins("Login","Login");
        verify(repository,times(1)).findAllByLogin(any(String.class));

    }
    @Test
    public void givenDifferentLogin_whenCheckLoginsForUserUpdate_theReturnDoubleLoginException()
    {
        List<User> users=new ArrayList<>();

        when(repository.findAllByLogin(any(String.class))).thenReturn(users);
        userService.checkLogins("Login","Login2");
        verify(repository,times(1)).findAllByLogin(any(String.class));

    }
}