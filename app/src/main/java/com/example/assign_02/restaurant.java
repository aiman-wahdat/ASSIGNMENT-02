package com.example.assign_02;

public class restaurant {
    String RestaurantName;
    String Location;
    String Phone;
    String Description;
    String Ratings;

    public restaurant() {
    }

    public restaurant(String restaurantName, String location, String phone, String description, String ratings) {
        RestaurantName = restaurantName;
        Location = location;
        Phone = phone;
        Description = description;
        Ratings = ratings;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getRatings() {
        return Ratings;
    }

    public void setRatings(String ratings) {
        Ratings = ratings;
    }

    @Override
    public String toString() {
        return "restaurant{" +
                "RestaurantName='" + RestaurantName + '\'' +
                ", Location='" + Location + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Description='" + Description + '\'' +
                ", Ratings='" + Ratings + '\'' +
                '}';
    }
}
