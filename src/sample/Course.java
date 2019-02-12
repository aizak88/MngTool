package sample;

public class Course {

    private int id;
    private String name;
    private int price;
    private int isActive;

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course(int id, String name, int price, int isActive) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Course(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
