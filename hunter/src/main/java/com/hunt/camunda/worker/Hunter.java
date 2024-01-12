package com.hunt.camunda.worker;

import com.hunt.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * Базовый класс для Entity.
 * <p>У каждой сущности в БД должны быть эти поля.</p>
 *
 * @author idurdyev
 */
@Getter
@Setter
public class Hunter extends BaseEntity {


    private String name;
    private String surName;

}
