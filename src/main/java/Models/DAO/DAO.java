package Models.DAO;

import Models.PasajeroModel;

import java.util.List;

public interface DAO <T> {

    public abstract void create(T dato);

    public abstract List<T> getAll();

    public abstract T getById(int id);

    public abstract void update(T dato, int id);

    public abstract void delete(int id);
}