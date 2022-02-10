package recipes.memory;

public interface Savable<T, R> {
    R save(T object);

    T restore(R id);
}
