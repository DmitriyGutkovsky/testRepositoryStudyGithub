package com.noirix.homeworks.homework2;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/*
вывести уникальный список всех друзей и друзей друзей до N уровня
 */
@Data
public class User {

  private Long id;

  private String name;

  private String surname;

  private List<User> friends;

  public static void main(String[] args) {

    List<User> allUsers = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      User user = new User();
      user.setId((long) i);
      user.setName("Name" + i);
      user.setSurname("Surname" + i);
      allUsers.add(user);
    }

    for (int i = 0; i < allUsers.size(); i++) {
      List<User> userFriends = new ArrayList<>();
      for (int j = i + 1; j < allUsers.size(); j++) {
        userFriends.add(allUsers.get(j));
      }
      allUsers.get(i).setFriends(userFriends);
    }

//        for (User allUser : allUsers) {
//          System.out.println(allUser.toString());
//        }
    listOfFriends(allUsers.get(5), 2);
  }

  public static List<User> listOfFriends(User user, int n) {

    List<User> userFriendsList = user.getFriends();
    if (n > 1) {
      for (User user1 : userFriendsList) {
        listOfFriends(user1, n - 1);
      }
    }

    System.out.println(user);

   return userFriendsList;
  }
}
