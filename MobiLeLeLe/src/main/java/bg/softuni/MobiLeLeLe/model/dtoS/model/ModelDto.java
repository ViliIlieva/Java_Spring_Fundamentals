package bg.softuni.MobiLeLeLe.model.dtoS.model;

import bg.softuni.MobiLeLeLe.model.entities.Brand;
import bg.softuni.MobiLeLeLe.model.enums.ModelCategory;

import java.util.Date;

public class ModelDto extends BaseEntityDTO {

    private String name;

    private ModelCategory category;

    private String imageUrl;

    private Integer startYear;

    private Integer endYear;

    private Date created;

    private Date modified;

    private Brand brand;

    public String getName() {
        return name;
    }

    public ModelDto setName(String name) {
        this.name = name;
        return this;
    }

    public ModelCategory getCategory() {
        return category;
    }

    public ModelDto setCategory(ModelCategory category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ModelDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public ModelDto setStartYear(Integer startYear) {
        this.startYear = startYear;
        return this;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public ModelDto setEndYear(Integer endYear) {
        this.endYear = endYear;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public ModelDto setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Date getModified() {
        return modified;
    }

    public ModelDto setModified(Date modified) {
        this.modified = modified;
        return this;
    }

    public Brand getBrand() {
        return brand;
    }

    public ModelDto setBrand(Brand brand) {
        this.brand = brand;
        return this;
    }
}