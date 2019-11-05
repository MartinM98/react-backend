package pw.react.backend.reactbackend;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pw.react.backend.reactbackend.Errors.DoubleLoginException;
import pw.react.backend.reactbackend.Errors.ErrorMessage;
import pw.react.backend.reactbackend.Errors.UserNotFound;
import pw.react.backend.reactbackend.Model.User;
import pw.react.backend.reactbackend.Repositories.UserRepository;
import pw.react.backend.reactbackend.Services.UserService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ReactBackendApplicationTests {

    @Mock
   private UserRepository repository;

    @InjectMocks
   private UserService userService;

    @Test(expected = UserNotFound.class)
    public void givenNonExistingId_whenUpdateUser_theReturnUserNotFoundException()
    {
        when(userService.updateUser(1L,new User())).thenThrow(UserNotFound.class);
        ResponseEntity<User> actualResponseEntity=userService.updateUser(1L,new User());
        User actual=actualResponseEntity.getBody();
       User nullUser=new User();
        assertThat(actual).isEqualTo(nullUser);
        verify(repository,times(1)).findById(eq(1L));
        verify(repository,times(0)).save(any(User.class));
        verify(repository,times(0)).findAllByLogin((eq("Login")));
    }

    @Test
    public void givenExistingId_whenUpdateUser_theReturnUpdatedUser()
    {
        User updateUser=new User("Login","Firstname","Lastname", Date.valueOf("2019-09-19"),false);
        when(repository.findById(anyLong())).thenReturn(Optional.of(updateUser));
        when(repository.save(any(User.class))).thenReturn(updateUser);
        ResponseEntity<User> actualResponseEntity=userService.updateUser(1L,updateUser);
        assertThat(actualResponseEntity.getBody()).isEqualTo(updateUser);
        verify(repository,times(1)).findById(eq(1L));
        verify(repository,times(1)).findAllByLogin((eq("Login")));
        verify(repository,times(1)).save(eq(updateUser));
    }

    @Test
    public void givenUsers_whenCreateUser_theReturnString()
    {
        List<User> users=new ArrayList<User>();
        users.add(new User("Login","Firstname","Lastname", Date.valueOf("2019-09-19"),false));
        users.add(new User("Login2","Firstname","Lastname", Date.valueOf("2019-09-19"),false));
        when(repository.saveAll(any(List.class))).thenReturn(users);
        ResponseEntity<String> actual=userService.createUser(users);
        assertThat(actual.getBody()).isInstanceOf(String.class);
        verify(repository,times(1)).saveAll(eq(users));
    }

    @Test
    public void givenExistingLogin_whenCheckUser_theReturnUser()
    {
        User user=new User("Login","Firstname","Lastname", Date.valueOf("2019-09-19"),false);
        when(repository.findByLogin("Login")).thenReturn(user);
        ResponseEntity<User> actual=userService.checkUser("Login");
        assertThat(actual.getBody()).isInstanceOf(User.class);
        verify(repository,times(1)).findByLogin(any(String.class));
    }

    @Test
    public void givenNonExistingLogin_whenCheckUser_theReturnNull()
    {
        User user=null;
        when(repository.findByLogin(any(String.class))).thenReturn(user);
        ResponseEntity<User> actual=userService.checkUser("Login");
        assertThat(actual.getBody()).isNull();
        verify(repository,times(1)).findByLogin(any(String.class));
    }

@Test(expected = UserNotFound.class)
    public void givenNonExistingId_whenDeleteUser_theReturnUserNotFound()
{
    when(repository.findById(any(long.class))).thenReturn(Optional.empty());
    ResponseEntity<String> actual=userService.deleteUser(1L);
    assertThat(actual.getBody()).isInstanceOf(String.class);
    verify(repository,times(1)).findById(any(long.class));
    verify(repository,times(0)).deleteById(any(long.class));

}

    @Test
    public void givenExistingId_whenDeleteUser_theReturnString()
    {
        when(repository.findById(any(long.class))).thenReturn(Optional.of(new User()));
        ResponseEntity<String> actual=userService.deleteUser(1L);
        assertThat(actual.getBody()).isInstanceOf(String.class);
        verify(repository,times(1)).findById(any(long.class));
        verify(repository,times(1)).deleteById(any(long.class));

    }

    @Test
    public void givenUserNotFoundException_whenNoEntry()
    {
        UserNotFound ex = new UserNotFound("msg");

        ResponseEntity<ErrorMessage> response = userService.noEntry(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        ErrorMessage body = response.getBody();
        assertThat(body.getMessage()).isEqualTo("msg");
        assertThat(body.getCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void givenDoubleLoginException_whenDoubleLogin()
    {
        DoubleLoginException ex = new DoubleLoginException("msg");

        ResponseEntity<ErrorMessage> response = userService.doubleLogin(ex);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.IM_USED);
        ErrorMessage body = response.getBody();
        assertThat(body.getMessage()).isEqualTo("msg");
        assertThat(body.getCode()).isEqualTo(HttpStatus.IM_USED.value());
    }


}
