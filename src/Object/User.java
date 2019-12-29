package Object;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Oscar C
 */
public class User {

    String Name;
    String Apellido;
    int carnet;
    String pass;
    String Password;  //encriptada
    int estado = 0;
    String motivo;

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getMotivo() {
        return motivo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public User(String Name, String Apellido, int carnet, String pass) {
        this.Name = Name;
        this.Apellido = Apellido;
        this.carnet = carnet;
        this.pass = pass;
        this.Password = convertirSHA256(pass);
    }
        public User(String Name, String Apellido, int carnet, String pass, String mot) {
        this.motivo= mot;
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

    public int getCarnet() {
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

    public void setCarnet(int carnet) {
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
