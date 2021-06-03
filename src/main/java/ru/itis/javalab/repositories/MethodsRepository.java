package ru.itis.javalab.repositories;

import org.springframework.stereotype.Component;

@Component
public interface MethodsRepository {
    void enlargeCount(String name, int count);
    void save(String name, int count);
    void delete();
}
