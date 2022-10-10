package rikkei.academy.model;

public class BaiDoc {
    private int id;
    String name_baidoc;

    public BaiDoc() {
    }

    public BaiDoc(String name_baidoc) {
        this.name_baidoc = name_baidoc;
    }


    public BaiDoc(int id, String name_baidoc) {
        this.id = id;
        this.name_baidoc = name_baidoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name_baidoc;
    }

    public void setName(String name) {
        this.name_baidoc = name_baidoc;
    }


    @Override
    public String toString() {
        return "BaiDoc{" +
                "id=" + id +
                ", name_baidoc='" + name_baidoc + '\'' +
                '}';
    }

}
