package bg.softuni.MobiLeLeLe.model.dtoS.model;


import bg.softuni.MobiLeLeLe.model.entities.Model;
import bg.softuni.MobiLeLeLe.model.entities.User;
import bg.softuni.MobiLeLeLe.model.enums.Engine;
import bg.softuni.MobiLeLeLe.model.enums.Transmission;

import java.util.Date;

public class OfferDto extends BaseEntityDTO {

    private String description;

    private Engine engine;

    private String imageUrl;

    private String mileage;

    private String price;

    private Transmission transmission;

    private String year;

    private Date created;

    private Date modified;

    private Model model;

    private User seller;

    public String getDescription() {
        return description;
    }

    public OfferDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Engine getEngine() {
        return engine;
    }

    public OfferDto setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getMileage() {
        return mileage;
    }

    public OfferDto setMileage(String mileage) {
        this.mileage = mileage;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public OfferDto setPrice(String price) {
        this.price = price;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public OfferDto setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public String getYear() {
        return year;
    }

    public OfferDto setYear(String year) {
        this.year = year;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public OfferDto setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Date getModified() {
        return modified;
    }

    public OfferDto setModified(Date modified) {
        this.modified = modified;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public OfferDto setModel(Model model) {
        this.model = model;
        return this;
    }

    public User getSeller() {
        return seller;
    }

    public OfferDto setSeller(User seller) {
        this.seller = seller;
        return this;
    }
}