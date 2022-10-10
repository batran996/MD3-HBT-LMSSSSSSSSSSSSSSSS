package rikkei.academy.model;

public class Module {
    private int id;
<<<<<<< HEAD
    private String name;

    public Module(String name) {
        this.name = name;
    }

    public Module(int id, String name) {
        this.id = id;
        this.name = name;
=======
    private int id_lotrinh;
    String name_module;

    public Module() {
    }

    public Module(int id, int id_lotrinh, String name) {
        this.id = id;
        this.id_lotrinh = id_lotrinh;
        this.name_module = name;
>>>>>>> 8132a2f24b37575f2bd3ca6e7573090b132699a5
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
=======
    public int getId_lotrinh() {
        return id_lotrinh;
    }

    public void setId_lotrinh(int id_lotrinh) {
        this.id_lotrinh = id_lotrinh;
    }

    public String getName_module() {
        return name_module;
    }

    public void setName_module(String name_module) {
        this.name_module = name_module;
>>>>>>> 8132a2f24b37575f2bd3ca6e7573090b132699a5
    }
}
