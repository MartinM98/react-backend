package pw.react.backend.reactbackend.Model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="users")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties("hibernateLazyInitializer","handler")
public class User implements Serializable {

    private static final long serialVersionUID=-2343243243242432341L;
    //public static pw.react.backend.reactbackend.Model.User EMPTY=new pw.react.backend.reactbackend.Model.User();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "active")
    private boolean active;


    public long getID(){
        return id;
    }
    public void setID(int id)
    {
        this.id=id;
    }
    public String getLogin(){
        return login;
    }
    public void setLogin(String login)
    {
        this.login=login;
    }
    public String getfirstName(){
        return firstName;
    }
    public void setfirstName(String firstName)
    {
        this.firstName=firstName;
    }
    public String getlastName(){
        return lastName;
    }
    public void setlastName(String lastName)
    {
        this.lastName=lastName;
    }
    public Date getdateOfBirth(){
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth=dateOfBirth;
    }
    public boolean getActive(){
        return active;
    }
    public void setActive(boolean active)
    {
        this.active= active;
    }

    public User()
    {

    }
    public User(String login,String firstName,String lastName,Date dateOfBirth,boolean active )
    {
        this.login=login;
        this.firstName=firstName;
        this.lastName=lastName;
        this.dateOfBirth=dateOfBirth;
        this.active=active;
    }


}
