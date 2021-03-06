package Project.Model;

import Project.Utils.Encode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "User")
public class User implements Serializable {

    private static final long serialVersionUID = 368875986784459499L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Getter @Setter private long id;

    @Column(name="nom")
    @Size(min = 2, max = 50)
    @Getter @Setter private String nom;

    @Column(name="prenom")
    @Size(min = 2, max = 50)
    @Getter @Setter private String prenom;

    @Column(name="email")
    @Getter @Setter private String email;

    @Column(name="password")
    @Getter @Setter private String password;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(cascade=CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "user")
    @Getter @Setter private List<Url> urls;

    public User() {}

    public User(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        //this.password = password;

        this.password = Encode.encodeSHA512(this.password, null);
    }
}
