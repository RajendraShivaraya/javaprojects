package securityjwtauthentication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Users
{
    @Id
    @Column(name = "userid", length = 20)
    private String userId;
    @Column(name = "password", length = 12)
    private String password;
    @Column(name = "emailid", length = 50)
    private String emailId;
    @Column(name = "phone", length = 10)
    private Long   phoneNumber;
    @Column(name = "firstname", length = 15)
    private String firstName;
    @Column(name = "lastname", length = 15)
    private String lastName;
    @Column(name = "dob")
    private Date   dob;
    @Column(name = "createddate")
    private Date   accCreationDate;

    @Column(name = "createdtime")
    private Time   accCreationTime;

    @Column(name = "userrole")
    private String userRole;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    private Set<Roles> roles = new HashSet<>();
    public void addRole(Roles role) {
        this.roles.add(role);
    }
}
