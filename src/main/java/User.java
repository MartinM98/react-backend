
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="User")
//@JsonIgnoreProperties("hibernateLazyInitializer","handler")
public class User implements Serializable {

    private static final long serialVersionUID=923746872364276483L;
    //public static User EMPTY=new User();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long ID;
    @Column(name="Login")
    private String Login;
    @Column(name="First Name")
    private String firstName;
    @Column(name="Last Name")
    private String lastName;
    @Column(name="Date of birth")
    private Date dateOfBirth;
    @Column(name="Active")
    private boolean Active;

   public long getID(){
        return ID;
    }
     public void setID(long ID)
    {
        this.ID=ID;
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
        return Active;
    }
    public void setActive(boolean Active)
    {
        this.Active= Active;
    }

    public User(String firstName,String lastName)
    {
        this.firstName=firstName;
        this.lastName=lastName;
    }



}
