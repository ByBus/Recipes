package recipes.memory;

public interface Savable<T> {
    void save(T recipe);

    T restore();
}
