/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import ohtu.domain.User;

/**
 *
 * @author ola
 */
public class FileUserDao implements UserDao {
    
    Scanner sc;
    String filename;
    List<User> users;

    public FileUserDao(String filename) {
        this.filename = filename;
        this.add(new User("pekka", "akkep"));
        try {
            sc = new Scanner(new File(this.filename));
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen epäonnistui. Virhe: " + e.getMessage());
            return;
        }
    }

    @Override
    public List<User> listAll() {
        while (sc.hasNext()) {
            String name = sc.next();
            String password = sc.next();
            users.add(new User(name, password));
        }
        return users;
    }

    @Override
    public User findByName(String name) {
        for (User u : this.listAll()) {
            if (u.getUsername().equals(name)) 
                return u;
        }
        return null;
    }

    @Override
    public void add(User user) {
        try {
            FileWriter fw = new FileWriter(this.filename);
            fw.write(user.getUsername() + " " + user.getPassword() + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Tiedostoon kirjoittaminen epäonnistui. Virhe: " + e.getMessage());
            return;
        }
        
    }
    
}
