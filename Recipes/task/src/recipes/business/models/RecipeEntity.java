package recipes.business.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipes")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class RecipeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "category")
    private String category;

    @CreationTimestamp
    @Column(name = "date")
    private LocalDateTime date;

    @NonNull
    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name = "ingredients")
    private String[] ingredients;

    @NonNull
    @Column(name = "directions")
    private String[] directions;
}
