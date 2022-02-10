package recipes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String name;
    private String description;
    private String ingredients;
    private String directions;
}
