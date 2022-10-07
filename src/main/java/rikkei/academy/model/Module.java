package rikkei.academy.model;

public class Module {
    private int id;
    private int id_lotrinh;
    String name;

    public Module() {
    }

    public Module(int id, int id_lotrinh, String name) {
        this.id = id;
        this.id_lotrinh = id_lotrinh;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_lotrinh() {
        return id_lotrinh;
    }

    public void setId_lotrinh(int id_lotrinh) {
        this.id_lotrinh = id_lotrinh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
