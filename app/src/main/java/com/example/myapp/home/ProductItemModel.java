package com.example.myapp.home;

public class ProductItemModel {

    int image;
    String productTitle;


    public ProductItemModel(int image, String productTitle, int minQuantity, int maxQuantity) {
        this.image = image;
        this.productTitle = productTitle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

}
