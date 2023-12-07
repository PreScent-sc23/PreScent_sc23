package net.prescent.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class FlowerEntity {
    @Id
    private Long id;
    private String name;
    private String meaning;
}
