package net.prescent.entity;

import net.prescent.dto.CustomizeProductDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customize-product")
public class CustomizeProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cpKey;
    private String[] answers;

    private Integer shopKey;
}
