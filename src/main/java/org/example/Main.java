package org.example;

import users.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //        String price = "$50.00";
        //        int position = price.indexOf(".");
        //        String formatted_price = price.substring(1, position);
        //        int int_price = Integer.parseInt(formatted_price);
        //        float float_price = Float.parseFloat(formatted_price);
        //        System.out.println(int_price);

        //        System.out.println(isValidPassword("abA$12abc")); //valid
        //        System.out.println(isValidPassword(null)); // check null
        //        System.out.println(isValidPassword("abA$12c")); // check length < 8
        //        System.out.println(isValidPassword("12345$abc")); // check is missing upper character
        //        System.out.println(isValidPassword("12345$ABC")); // check is missing lower character
        //        System.out.println(isValidPassword("abcAabcd$")); // check is missing number
        //        System.out.println(isValidPassword("abcAabc12")); // check is missing special character

        User[] users = new User[7];
        users[0] = new User(1);
        users[1] = new User(2);
        users[2] = new User(3);
        users[3] = new User(4);
        users[4] = new User(5);
        users[5] = new User(6);
        users[6] = new User(7);

        List<User> userList = Arrays.asList(users);


        for (User user : users) {
            user.info();
        }
        System.out.println("-----");
        Arrays.stream(users).max(Comparator.comparing(User::getAge)).get().info();
        Arrays.stream(users).min(Comparator.comparing(User::getAge)).get().info();

        System.out.println("====================");

        List<User> femaleUsers = Arrays.stream(users).filter(user -> user.getGender().equals("female")).toList();

        for (User user:femaleUsers){
            user.info();
        }
        System.out.println("====================");
        List<String> names = Arrays.stream(users).map(User::getName).toList();
        System.out.println(names);
        System.out.println("====================");
        //todo: sum total year old
        int totalYearOlds = Arrays.stream(users).mapToInt(User::getAge).sum();
        System.out.printf("total year olds is %d",totalYearOlds);
        System.out.println("====================");
        // sort by year old
        List<User> sortedByYearOldUsers = Arrays.stream(users).sorted(Comparator.comparing(User::getAge).reversed()).toList();
        for (User user: sortedByYearOldUsers){
            user.info();
        }


    }

    public static boolean isValidPassword(String str){
        if(str == null || str.isEmpty()){
            return false;
        }
        return str.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[?@#$%^&*]).{8,}$");
    }
}