package com.hunt.worker.model;


import jakarta.persistence.MappedSuperclass;
import org.reflections.Reflections;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Collection;
import java.util.UUID;


@MappedSuperclass
public class AbstractEntity extends AbstractPersistable<UUID> {
    public static Collection<Class<? extends AbstractEntity>> findChildren() {
        Reflections reflections = new Reflections("com.hunt"); // Replace with your package name
        return reflections.getSubTypesOf(AbstractEntity.class);
    }

    public static Class<? extends AbstractEntity> childForName(String childSimpleName) {
        return
                findChildren().stream().filter(c -> c.getSimpleName().equals(childSimpleName)).findFirst().get();
    }
}
