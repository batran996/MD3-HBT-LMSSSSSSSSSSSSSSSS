package rikkei.academy.model;

public class BaiDoc {
    private int id;
    private String name;

    public BaiDoc(String name) {
        this.name = name;
    }

    public BaiDoc(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BaiDoc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
