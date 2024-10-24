package Models;

import java.util.Objects;

public class HabitacionModel {

    private int id;
    private int numeroHabitacion;
    private int cantidadCamas;
    private int cantidadCamasSimples;
    private int cantidadCamasDobles;
    private int precioDia;

    public HabitacionModel(int id, int numeroHabitacion, int cantidadCamas, int cantidadCamasSimples, int cantidadCamasDobles, int precioDia) {
        this.id = id;
        this.numeroHabitacion = numeroHabitacion;
        this.cantidadCamas = cantidadCamas;
        this.cantidadCamasSimples = cantidadCamasSimples;
        this.cantidadCamasDobles = cantidadCamasDobles;
        this.precioDia = precioDia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public int getCantidadCamas() {
        return cantidadCamas;
    }

    public void setCantidadCamas(int cantidadCamas) {
        this.cantidadCamas = cantidadCamas;
    }

    public int getCantidadCamasSimples() {
        return cantidadCamasSimples;
    }

    public void setCantidadCamasSimples(int cantidadCamasSimples) {
        this.cantidadCamasSimples = cantidadCamasSimples;
    }

    public int getCantidadCamasDobles() {
        return cantidadCamasDobles;
    }

    public void setCantidadCamasDobles(int cantidadCamasDobles) {
        this.cantidadCamasDobles = cantidadCamasDobles;
    }

    public int getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(int precioDia) {
        this.precioDia = precioDia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HabitacionModel that = (HabitacionModel) o;
        return id == that.id && numeroHabitacion == that.numeroHabitacion && cantidadCamas == that.cantidadCamas && cantidadCamasSimples == that.cantidadCamasSimples && cantidadCamasDobles == that.cantidadCamasDobles && precioDia == that.precioDia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroHabitacion, cantidadCamas, cantidadCamasSimples, cantidadCamasDobles, precioDia);
    }

    @Override
    public String toString() {
        return "HabitacionModel{" +
                "id=" + id +
                ", numeroHabitacion=" + numeroHabitacion +
                ", cantidadCamas=" + cantidadCamas +
                ", cantidadCamasSimples=" + cantidadCamasSimples +
                ", cantidadCamasDobles=" + cantidadCamasDobles +
                ", precioDia=" + precioDia +
                '}';
    }
}
