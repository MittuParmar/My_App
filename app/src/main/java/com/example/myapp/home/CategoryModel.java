package com.example.myapp.home;

public class CategoryModel {

    private int image;
    private String CategoryName;

    public CategoryModel(int image, String categoryName) {
        this.image = image;
        CategoryName = categoryName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
