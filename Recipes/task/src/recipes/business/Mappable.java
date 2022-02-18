package recipes.business;

public interface Mappable<T, U> {
    U mapToEntity(T objFrom);

    T mapToDTO(U objFrom);

    void update(T objFrom, U objTo);
}
