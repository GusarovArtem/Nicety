package ua.nicety.http.mapper;

public interface Mapper<F, T> {

    T map(F object);
}
