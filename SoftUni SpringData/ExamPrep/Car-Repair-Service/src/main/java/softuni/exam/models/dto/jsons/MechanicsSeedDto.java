package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MechanicsSeedDto implements Serializable {
    @Expose
    @Email
    @NotNull
    private String email;
    @Expose
    @Length(min = 2)
    @NotNull
    private String firstName;
    @Expose
    @Length(min = 2)
    @NotNull
    private String lastName;
    @Expose
    @Length(min = 2)
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
