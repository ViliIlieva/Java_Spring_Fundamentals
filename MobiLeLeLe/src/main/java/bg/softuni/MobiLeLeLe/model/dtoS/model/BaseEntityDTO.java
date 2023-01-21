package bg.softuni.MobiLeLeLe.model.dtoS.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class BaseEntityDTO {

    private String id;

    public String getId() {
        return id;
    }

    public BaseEntityDTO setId(String id) {
        this.id = id;
        return this;
    }
}