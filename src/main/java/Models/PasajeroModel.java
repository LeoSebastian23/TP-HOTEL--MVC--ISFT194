package Models;

import java.util.Objects;

public class PasajeroModel {
    private int id;
    private int dni;
    private String name;
    private String surname;
    private int cel;
    private String mail;

    // Constructor
    public PasajeroModel(int id, int dni, String name, String surname, int cel, String mail) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.cel = cel;
        this.mail = mail;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getCel() {
        return cel;
    }

    public void setCel(int cel) {
        this.cel = cel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    // Método toString
    @Override
    public String toString() {
        return "Pasajero{" +
                "id=" + id +
                ", dni=" + dni +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", cel=" + cel +
                ", mail='" + mail + '\'' +
                '}';
    }

    // Método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, dni, name, surname, cel, mail);
    }

    // Método equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PasajeroModel pasajeroModel = (PasajeroModel) obj;
        return dni == pasajeroModel.dni && cel == pasajeroModel.cel &&
                Objects.equals(name, pasajeroModel.name) &&
                Objects.equals(surname, pasajeroModel.surname) &&
                Objects.equals(mail, pasajeroModel.mail);
    }
}


