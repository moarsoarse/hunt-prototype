package com.hunt.common.model;

import java.util.UUID;

/**
 * Интерфейс, помечающий класс, как имеющий поле id и предоставляющий доступ к значению этого поля.
 *
 */
public interface Identifiable {

    UUID getId();
}
