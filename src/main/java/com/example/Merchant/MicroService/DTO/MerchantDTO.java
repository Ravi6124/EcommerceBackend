package com.example.Merchant.MicroService.DTO;

public class MerchantDTO
{
    String merchantId;
    String email;
    String firstName;
    String lastName;
    String contactNumber;
    String password;
    String city;
    int merchantRating;
    int numberOfMerchantRatings;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getMerchantRating() {
        return merchantRating;
    }

    public void setMerchantRating(int merchantRating) {
        this.merchantRating = merchantRating;
    }

    public int getNumberOfMerchantRatings() {
        return numberOfMerchantRatings;
    }

    public void setNumberOfMerchantRatings(int numberOfMerchantRatings) {
        this.numberOfMerchantRatings = numberOfMerchantRatings;
    }
}
