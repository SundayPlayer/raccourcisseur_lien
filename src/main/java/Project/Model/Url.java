package Project.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Url")
public class Url implements Serializable, Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Getter @Setter private long id;

    @Column(name="url")
    @Size(min = 3, max = 350)
    @Getter @Setter private String url;

    @Column(name="urlCourte")
    @Size(min = 3, max = 50)
    @Getter @Setter private String urlCourte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    @Getter @Setter private User user;

    public Url() {}

    public Url(String url, String urlCourte, User user) {
        this.url = url;
        this.urlCourte = urlCourte;
        this.user = user;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Url.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "url", "NotEmpty.message.name");

        if (url.length() < 3) {
            errors.rejectValue("url", "minValue", "Min.message.name");
        }

        if (url.length() > 350) {
            errors.rejectValue("url", "maxValue", "Max.message.name");
        }
    }
}
