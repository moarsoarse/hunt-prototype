package com.hunt.hunter.model;

import com.hunt.worker.model.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

/**
 *
 */
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@Table(name = "hunter")
public class Hunter extends AbstractEntity {

    @Column(nullable = false)
    @ToString.Include
    private String name;

    @Column(nullable = false)
    @ToString.Include
    private String surName;

    @Column(nullable = false)
    @NaturalId
    private String passportNo;

    public Hunter(String name, String surName, String passportNo) {
        this.name = name;
        this.surName = surName;
        this.passportNo = passportNo;
    }

}
