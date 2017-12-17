package com.epam.lab.model;

public class SpillMethod {


    private Integer idSpillMethod;
    private Double size;
    private String matherial;

    public Integer getIdSpillMethod() {
        return idSpillMethod;
    }

    public void setIdSpillMethod(Integer idSpillMethod) {
        this.idSpillMethod = idSpillMethod;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getSize() {
        return size;
    }

    public String getMatherial() {
        return matherial;
    }

    public void setMatherial(String matherial) {
        this.matherial = matherial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpillMethod that = (SpillMethod) o;

        if (idSpillMethod != null ? !idSpillMethod.equals(that.idSpillMethod) : that.idSpillMethod != null)
            return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        return matherial != null ? matherial.equals(that.matherial) : that.matherial == null;
    }

    @Override
    public int hashCode() {
        int result = idSpillMethod != null ? idSpillMethod.hashCode() : 0;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (matherial != null ? matherial.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SpillMethod{" +
                "idSpillMethod=" + idSpillMethod +
                ", size=" + size +
                ", matherial='" + matherial + '\'' +
                '}';
    }
}
