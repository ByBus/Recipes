package recipes.business;

public interface Mappable<T, U> {
    U mapToEntity(T recipe);

    T mapToDTO(U recipe);
}
