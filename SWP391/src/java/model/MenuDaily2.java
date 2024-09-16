package model;

/**
 * Lớp kai báo biến và thuộc tính
 *
 * @Phiên Bản : 1.0 04/06/2023
 * @Tác giả: Trịnh Minh Tân
 */
public class MenuDaily2 {

    private int id;
    private String name_food;
    private String describe_food;
    private int price_sell;
    private String img;
    private float discout;
    private int quantity;
    private int price_final;

    public MenuDaily2() {
    }

    public MenuDaily2(int id, String name_food, String describe_food, int price_sell, String img, float discout, int quantity, int price_final) {
        this.id = id;
        this.name_food = name_food;
        this.describe_food = describe_food;
        this.price_sell = price_sell;
        this.img = img;
        this.discout = discout;
        this.quantity = quantity;
        this.price_final = price_final;
    }

    public MenuDaily2(int id, String name_food, String img, float discout) {
        this.id = id;
        this.name_food = name_food;
        this.img = img;
        this.discout = discout;
    }

    public MenuDaily2(int id, String name_food, String img, int price_final) {
        this.id = id;
        this.name_food = name_food;
        this.img = img;
        this.price_final = price_final;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_food() {
        return name_food;
    }

    public void setName_food(String name_food) {
        this.name_food = name_food;
    }

    public String getDescribe_food() {
        return describe_food;
    }

    public void setDescribe_food(String describe_food) {
        this.describe_food = describe_food;
    }

    public int getPrice_sell() {
        return price_sell;
    }

    public void setPrice_sell(int price_sell) {
        this.price_sell = price_sell;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getDiscout() {
        return discout;
    }

    public void setDiscout(float discout) {
        this.discout = discout;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice_final() {
        return price_final;
    }

    public void setPrice_final(int price_final) {
        this.price_final = price_final;
    }

}
