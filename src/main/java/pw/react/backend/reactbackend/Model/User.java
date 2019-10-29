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

    private static final long serialVersionUID=9237468364276483L;
    //public static pw.react.backend.reactbackend.Model.User EMPTY=new pw.react.backend.reactbackend.Model.User();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
    @Column(name="login")
    private String login;
    @Column(name="[first name]")
    private String firstname;
    @Column(name="[last name]")
    private String lastname;
    @Column(name="[date of birth]")
    private Date dateofbirth;
    @Column(name="active")
    private boolean active;


    public long getID(){
        return id;
    }
    public void setID(long id)
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
        return firstname;
    }
    public void setfirstName(String firstname)
    {
        this.firstname=firstname;
    }
    public String getlastName(){
        return lastname;
    }
    public void setlastName(String lastname)
    {
        this.lastname=lastname;
    }
    public Date getdateOfBirth(){
        return dateofbirth;
    }
    public void setDateOfBirth(Date dateofbirth)
    {
        this.dateofbirth=dateofbirth;
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
    public User(String login,String firstname,String lastname)
    {
        this.login=login;
        this.firstname=firstname;
        this.lastname=lastname;
    }
    public User(String login)
    {
        this.login=login;
    }



}
