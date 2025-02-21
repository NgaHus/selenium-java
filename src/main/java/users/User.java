package users;

import java.util.Random;

public class User {
    private int id;
    private String name;
    private String gender;
    private String password;
    private int height;
    private int age;

    public User(int id, String name, String lastName, String gender, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.password = password;
    }
    public User(int id) {
        this.id = id;
        this.name = randomName();
        this.gender = randomGender();
        this.password = null;
        this.height = randomNumber(150, 180);
        this.age = randomNumber(25, 45);
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getGender() {
        return gender;
    }
    public String getPassword() {
        return password;
    }
    public int getHeight() {
        return height;
    }
    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public void info(){
        System.out.println(name + ", gioi tinh: " + gender + ", chieu cao: "
                + height + ", tuoi: " + age);
    }

    private int randomNumber(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }
    private String randomName(){
        Random rand = new Random();
        String[] name = {"Hien", "Huong", "Hung", "Linh", "Nga", "Thao", "Thy"};
        return name[rand.nextInt(name.length)];
    }
    private String randomGender(){
        Random rand = new Random();
        String[] gender = {"male", "female"};
        return gender[rand.nextInt(gender.length)];
    }



}
