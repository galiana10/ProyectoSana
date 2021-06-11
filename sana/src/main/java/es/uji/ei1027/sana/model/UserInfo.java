package es.uji.ei1027.sana.model;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class UserInfo {

    String username;
    String password;
    String name;
    String nie;
    int type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {


        this.password = password;
    }

    public void setPasswordEncripted(String password) {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

        this.password = passwordEncryptor.encryptPassword(password);
    }

    public String getNie() {
        return nie;
    }

    public void setNie(String nie) {
        this.nie = nie;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
