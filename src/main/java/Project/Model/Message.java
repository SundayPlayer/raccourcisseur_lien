package Project.Model;

import Project.Utils.Encode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Message")
public class Message implements Serializable, Validator {

    private static final long serialVersionUID = 368875986784459499L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Getter @Setter private long id;

    @Column(name="message")
    @Getter @Setter private String message;

    @Column(name="encode")
    @Getter @Setter private String encode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    @Getter @Setter private User user;

    public Message() {}

    public Message(String message, User user) {
        this.message = message;
        this.user = user;

        // Encodage du message
        this.encode = Encode.encodeSHA512(this.message, null);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "NotEmpty.message.name");

        if (message.length() < 2) {
            errors.rejectValue("message", "minValue", "Min.message.name");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
}
