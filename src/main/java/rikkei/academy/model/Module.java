package rikkei.academy.model;

public class Module {
    private int id;
    private String name_module;

    public Module() {
    }

    public Module(String name_module) {
        this.name_module = name_module;
    }


    public Module(int id, String name_module) {
        this.id = id;
        this.name_module = name_module;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name_module;
    }

    public void setName(String name) {
        this.name_module = name_module;
    }


    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name_module='" + name_module + '\'' +
                '}';
    }
}