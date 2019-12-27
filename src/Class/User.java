package Class;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Oscar C
 */
public class User {

    String Name;
    String Apellido;
    String carnet;
    String pass;
    String Password;  //encriptada

    public User(String Name, String Apellido, String carnet, String pass) {
        this.Name = Name;
        this.Apellido = Apellido;
        this.carnet = carnet;
        this.pass = pass;
        this.Password = convertirSHA256(pass);
    }

    public String getName() {
        return Name;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getCarnet() {
        return carnet;
    }

    public String getPass() {
        return pass;
    }

    public String getPassword() {
        return Password;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public void setPass(String pass) {
        this.pass = pass;
        this.Password = convertirSHA256(pass);
    }



    public static String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
