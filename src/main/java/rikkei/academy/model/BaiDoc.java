package rikkei.academy.model;

public class BaiDoc {
    private int id;
    private int id_lotrinh;
    private int id_module;
    String name_baidoc;

    public BaiDoc() {
    }

    public BaiDoc(int id, int id_lotrinh, int id_module, String name_baidoc) {
        this.id = id;
        this.id_lotrinh = id_lotrinh;
        this.id_module = id_module;
        this.name_baidoc = name_baidoc;
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

    public int getId_module() {
        return id_module;
    }

    public void setId_module(int id_module) {
        this.id_module = id_module;
    }

    public String getName_baidoc() {
        return name_baidoc;
    }

    public void setName_baidoc(String name_baidoc) {
        this.name_baidoc = name_baidoc;
    }
}
