package com.epam.lab.model;

public class Ingredients {

    private Integer idIngredients;
    private Integer water;
    private Integer malt;
    private Integer hop;
    private Integer sugar;

    public Integer getIdIngredients() {
        return idIngredients;
    }

    public void setIdIngredients(Integer idIngredients) {
        this.idIngredients = idIngredients;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMalt() {
        return malt;
    }

    public void setMalt(int malt) {
        this.malt = malt;
    }

    public int getHop() {
        return hop;
    }

    public void setHop(int hop) {
        this.hop = hop;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredients that = (Ingredients) o;

        if (idIngredients != null ? !idIngredients.equals(that.idIngredients) : that.idIngredients != null)
            return false;
        if (water != null ? !water.equals(that.water) : that.water != null) return false;
        if (malt != null ? !malt.equals(that.malt) : that.malt != null) return false;
        if (hop != null ? !hop.equals(that.hop) : that.hop != null) return false;
        return sugar != null ? sugar.equals(that.sugar) : that.sugar == null;
    }

    @Override
    public int hashCode() {
        int result = idIngredients != null ? idIngredients.hashCode() : 0;
        result = 31 * result + (water != null ? water.hashCode() : 0);
        result = 31 * result + (malt != null ? malt.hashCode() : 0);
        result = 31 * result + (hop != null ? hop.hashCode() : 0);
        result = 31 * result + (sugar != null ? sugar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "idIngredients=" + idIngredients +
                ", water=" + water +
                ", malt=" + malt +
                ", hop=" + hop +
                ", sugar=" + sugar +
                '}';
    }
}
