package model;

public class Client {
    private int id;
    private String personName;
    private String address;
    private String phone;
    private int age;
    private String username;
    private String password;

    public Client(){this(0,"","","",0,"","");}

    public Client(int id, String name, String address, String phone, int age, String username, String password){
        this.id = id;
        this.personName = name;
        this.address = address;
        this.phone = phone;
        this.age = age;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return id + " " + personName + " " + address + " " + phone + " " + age + " " + username;
    }
}
