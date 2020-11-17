package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Student {
    String username;
    String password;
    String name;
    String surname;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    public String getFullName(){return name+" "+surname;}
    public String getNewName(){return name+" "+surname+"("+username+")";}


    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}
