package model;

public class Person {
    private int id;
    private String personName;
    private String address;
    private String phone;
    private int age;
    private String username;

    public Person(int id, String name, String address, String phone, int age, String username){
        this.id = id;
        this.personName = name;
        this.address = address;
        this.phone = phone;
        this.age = age;
        this.username = username;
    }

    public int getId() {
        return id;
    }
    public int getAge() {
        return age;
    }
    public String getAddress() {
        return address;
    }
    public String getPersonName() {
        return personName;
    }
    public String getPhone() {
        return phone;
    }
    public String getUsername() {
        return username;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
