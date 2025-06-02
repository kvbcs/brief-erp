public class Product {
    private final int id;
    private final String category;
    private final String title;
    private final String actor;
    private final double price;
    private final int special;
    private final int common_prod_id;

    public Product(int id, String actor, int special, int common_prod_id, String title, double price, String category) {
        this.id = id;
        this.actor = actor;
        this.special = special;
        this.common_prod_id = common_prod_id;
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getActor() {
        return actor;
    }

    public int getSpecial() {
        return special;
    }

    public int getCommonProdId(){
        return common_prod_id;
    }

    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
}
