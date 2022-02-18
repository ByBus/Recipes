package recipes.persistence.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserEntity {
    @Id
    @NonNull
    @Column(name = "email")
    private String username;
    @NonNull
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false)
    private List<RecipeEntity> recipes = new ArrayList<>();

    private String role = "ROLE_USER";
}
