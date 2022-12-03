package id.branditya.hacktivfinalproject2;

public class Product {
    private int id;
    private String type;
    private String filter;
    private String category;
    private String name;
    private int quantity;

    public Product() {
        super();
    }

    public Product(String type, String filter, String category, String name, int quantity) {
        this.type = type;
        this.filter = filter;
        this.category = category;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
