package Models;

import java.util.Objects;

public class PasajeroModel {
    private int id;
    private int dni;
    private String name;
    private String surname;
    private String cel;
    private String mail;

    // Constructor
    public PasajeroModel(int id, int dni, String name, String surname, String cel, String mail) {
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

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasajeroModel that = (PasajeroModel) o;
        return id == that.id && dni == that.dni && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(cel, that.cel) && Objects.equals(mail, that.mail);
    }
}


