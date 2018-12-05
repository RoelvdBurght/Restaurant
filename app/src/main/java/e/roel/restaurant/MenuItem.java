package e.roel.restaurant;

public class MenuItem {

    private String name;
    private String description;
    private String imageUrl;
    private String price;
    private String category;

    public MenuItem(String name_, String description_, String imageUrl_, String price_,
                    String category_) {
        name = name_;
        description = description_;
        imageUrl = imageUrl_;
        price = price_;
        category = category_;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
