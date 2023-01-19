package bg.softuni.MobiLeLeLe.model.entity;

import bg.softuni.MobiLeLeLe.model.enums.EngineEnum;
import bg.softuni.MobiLeLeLe.model.enums.TransmissionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "offers")
@Getter
@Setter
@NoArgsConstructor
public class OfferEntity extends BaseEntity {

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private EngineEnum engine;

    @Column
    private String imageUrl;

    @Column
    private String mileage;

    @Column
    private String price;

    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;

    @Column
    private String year;

    @Column
    private Date created;

    @Column
    private Date modified;

    @ManyToOne
    private ModelEntity model;

    @ManyToOne
    private UserEntity seller;

}
