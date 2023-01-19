package bg.softuni.MobiLeLeLe.model.entity;

import bg.softuni.MobiLeLeLe.model.entity.enums.CategoryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "models")
@Getter
@Setter
@NoArgsConstructor
public class ModelEntity extends BaseEntity{

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column
    private String imageUrl;

    @Column
    private Integer startYear;

    @Column
    private Integer endYear;

    @Column
    private Date created;

    @Column
    private Date modified;

    @ManyToOne
    private BrandEntity brands;

}
