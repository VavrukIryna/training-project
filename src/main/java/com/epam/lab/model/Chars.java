package com.epam.lab.model;

public class Chars {


    private Integer idChars;
    private Double volumeAlcoholFraction;
    private String clearness;
    private String filtered;
    private String nutritionalValue;
    private SpillMethod spillMethod;

    public Integer getIdChars() {
        return idChars;
    }

    public void setIdChars(Integer idChars) {
        this.idChars = idChars;
    }

    public Double getVolumeAlcoholFraction() {
        return volumeAlcoholFraction;
    }

    public void setVolumeAlcoholFraction(Double volumeAlcoholFraction) {
        this.volumeAlcoholFraction = volumeAlcoholFraction;
    }

    public String getClearness() {
        return clearness;
    }

    public void setClearness(String clearness) {
        this.clearness = clearness;
    }

    public String getFiltered() {
        return filtered;
    }

    public void setFiltered(String filtered) {
        this.filtered = filtered;
    }

    public String getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(String nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public SpillMethod getSpillMethod() {
        return spillMethod;
    }

    public void setSpillMethod(SpillMethod spillMethod) {
        this.spillMethod = spillMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chars chars = (Chars) o;

        if (idChars != null ? !idChars.equals(chars.idChars) : chars.idChars != null) return false;
        if (volumeAlcoholFraction != null ? !volumeAlcoholFraction.equals(chars.volumeAlcoholFraction) : chars.volumeAlcoholFraction != null)
            return false;
        if (clearness != null ? !clearness.equals(chars.clearness) : chars.clearness != null) return false;
        if (filtered != null ? !filtered.equals(chars.filtered) : chars.filtered != null) return false;
        if (nutritionalValue != null ? !nutritionalValue.equals(chars.nutritionalValue) : chars.nutritionalValue != null)
            return false;
        return spillMethod != null ? spillMethod.equals(chars.spillMethod) : chars.spillMethod == null;
    }

    @Override
    public int hashCode() {
        int result = idChars != null ? idChars.hashCode() : 0;
        result = 31 * result + (volumeAlcoholFraction != null ? volumeAlcoholFraction.hashCode() : 0);
        result = 31 * result + (clearness != null ? clearness.hashCode() : 0);
        result = 31 * result + (filtered != null ? filtered.hashCode() : 0);
        result = 31 * result + (nutritionalValue != null ? nutritionalValue.hashCode() : 0);
        result = 31 * result + (spillMethod != null ? spillMethod.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Chars{" +
                "idChars=" + idChars +
                ", volumeAlcoholFraction=" + volumeAlcoholFraction +
                ", clearness='" + clearness + '\'' +
                ", filtered='" + filtered + '\'' +
                ", nutritionalValue='" + nutritionalValue + '\'' +
                ", spillMethod=" + spillMethod +
                '}';
    }
}
