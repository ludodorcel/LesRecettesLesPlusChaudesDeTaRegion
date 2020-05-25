package com.example.lesrecetteslespluschaudesdetaregion.presentation.model;

import java.util.List;

public class Step {
    private List<Equipment> equipment;
    private List<Equipment> ingredients;
    private Integer number;
    private String step;

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public List<Equipment> getIngredients() {
        return ingredients;
    }

    public Integer getNumber() {
        return number;
    }

    public String getStep() {
        return step;
    }


}
