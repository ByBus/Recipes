package recipes.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    @Email(message = "Email is not valid", regexp = ".+@.+\\..+")
    @NotEmpty
    String email;
    @NotNull
    @NotBlank
    @Size(min = 8)
    String password;
}
