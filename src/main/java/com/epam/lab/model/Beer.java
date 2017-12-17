package com.epam.lab.model;

public class Beer {


    private Integer idBeer;
    private String name;
    private Type type;
    private String ai;
    private String manufacturer;
    private Ingredients ingredients;
    private Chars chars;

    public Integer getIdBeer() {
        return idBeer;
    }

    public void setIdBeer(Integer idBeer) {
        this.idBeer = idBeer;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAi() {
        return ai;
    }

    public void setAi(String ai) {
        this.ai = ai;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public Chars getChars() {
        return chars;
    }

    public void setChars(Chars chars) {
        this.chars = chars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Beer beer = (Beer) o;

        if (idBeer != null ? !idBeer.equals(beer.idBeer) : beer.idBeer != null) return false;
        if (name != null ? !name.equals(beer.name) : beer.name != null) return false;
        if (type != beer.type) return false;
        if (ai != null ? !ai.equals(beer.ai) : beer.ai != null) return false;
        if (manufacturer != null ? !manufacturer.equals(beer.manufacturer) : beer.manufacturer != null) return false;
        if (ingredients != null ? !ingredients.equals(beer.ingredients) : beer.ingredients != null) return false;
        return chars != null ? chars.equals(beer.chars) : beer.chars == null;
    }

    @Override
    public int hashCode() {
        int result = idBeer != null ? idBeer.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (ai != null ? ai.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (chars != null ? chars.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "idBeer=" + idBeer +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", ai='" + ai + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", ingredients=" + ingredients +
                ", chars=" + chars +
                '}';
    }
}
