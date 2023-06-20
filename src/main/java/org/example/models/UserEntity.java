package org.example.models;

public class UserEntity {
    private int id;
    private String phone;
    private String name;
    private String username;
    private String email;
    private String website;
    private AddressEntity address;

    public UserEntity(int id, String phone, String name, String username, String email, String website, AddressEntity address) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.username = username;
        this.email = email;
        this.website = website;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserEntity {" +
                "\nid: " + id +
                ", \nname: '" + name + '\'' +
                ", \nusername: '" + username + '\'' +
                ", \nemail: '" + email + '\'' +
                ", \naddress: " + address +
                ", \nphone: '" + phone + '\'' +
                ", \nwebsite: " + website + '\'' +
                "\n}";
    }
}
