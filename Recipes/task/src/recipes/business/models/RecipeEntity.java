package recipes.business.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class RecipeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

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
