package melnikov.qualification.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class Human extends Identifier {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        Objects.requireNonNull(name,"The name must be nonnull");
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email)  {
        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(email)) {
            this.email = email;
            return;
        }
        throw new IllegalArgumentException("This email address is not Valid");
    }


}
