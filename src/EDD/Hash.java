package EDD;

import Object.User;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Oscar C
 */
public class Hash {
    
    User[] tabla;
    int m;
    double cantidad;
    
    public Hash(int m) {
        this.tabla = new User[m];
        this.m = m;
    }
    
    private int funcion(int llave, int m) {
        return llave % m;
    }
    
    public void insertarHash(String Name, String Apellido, int carnet, String pass) {
        
        User nueva = new User(Name, Apellido, carnet, pass);
        insertarHash(carnet, nueva);
        
    }
    
    public User log(int user) {
        for (int i = 0; i < this.tabla.length; i++) {
            if (this.tabla[i].getCarnet() == user) {
                return this.tabla[i];
            }
        }
        return null;
    }
    
    public boolean login(int user, String pass) {
        for (int i = 0; i < this.tabla.length; i++) {
            if(this.tabla[i]!= null){
                if (this.tabla[i].getCarnet() == user && this.tabla[i].getPass().equals(pass)) {
                return true;
            } 
            }
            
        }
        
        return false;
    }
    
    public boolean exist(int carnet){
        for (int i = 0; i < this.tabla.length; i++) {
            if(this.tabla[i] != null){
               if(this.tabla[i].getCarnet()==carnet ){
                return true;
            } 
            }
            
        }
        return false;
    }
    
    private void insertarHash(int llave, User nueva) {
        int in = 1;
        int indice = funcion(llave, m);
        int indiceTemp = indice;
        do {
            if (tabla[indice] == null) {
                tabla[indice] = nueva;
                cantidad++;
                
                break;
            } else {
                
                indice = (llave%7 + 1) * in;
                if (m <= indice) {
                    indice = indice - m;
                }
                in++;
                
            }
        } while (indice != indiceTemp);
        
        if (desbordamiento()) {
            redimensionar();
        }
        
    }
    
    private void redimensionar() {
        int temp = m;
        m = siguientePrimo(m);
        User[] temporal = this.tabla;
        User[] nuevaTabla = new User[m];
        this.tabla = nuevaTabla;
        
        for (int i = 0; i < temporal.length; i++) {
            if (temporal[i] != null) {
                insertarHash(temporal[i].getCarnet(), temporal[i]);
            }
            
        }
        /*
        for (int i = 0; i < temp; i++) {
         if (tabla[i] != null) {
         nuevaTabla[i] = tabla[i];
         }
         }*/
        
    }
    
    private boolean desbordamiento() {
        double s;
        s= cantidad / m;
        System.out.println(s);
        return cantidad / m > 0.55;
        
    }
    
    private int siguientePrimo(int inicio) {
        int siguiente = inicio + 1;
        while (!esPrimo(siguiente)) {
            siguiente++;
        }
        return siguiente;
    }
    
    private boolean esPrimo(int numero) {
        int contador = 2;
        boolean primo = true;
        while ((primo) && (contador != numero)) {
            if (numero % contador == 0) {
                primo = false;
            }
            contador++;
        }
        return primo;
    }
    
    public void eliminar(int carnet) {
        for (int i = 0; i < this.tabla.length; i++) {
            if (this.tabla[i].getCarnet() == carnet) {
                this.tabla[i] = null;
            }
        }
    }
    
    public void modName(int carnet, String nam) {
        for (int i = 0; i < this.tabla.length; i++) {
            if (this.tabla[i].getCarnet() == carnet) {
                this.tabla[i].setName(nam);
            }
        }
    }
    
    public void modApellido(int carnet, String ap) {
        for (int i = 0; i < this.tabla.length; i++) {
            if (this.tabla[i].getCarnet() == carnet) {
                this.tabla[i].setApellido(ap);
            }
        }
    }
        
    public void modPass(int carnet, String pass) {
        for (int i = 0; i < this.tabla.length; i++) {
            if (this.tabla[i].getCarnet() == carnet) {
                this.tabla[i].setPass(pass);
            }
        }
    }
        
    public void modificiar(int carnet, String nam, String ap, String pass) {
        for (int i = 0; i < this.tabla.length; i++) {
            if (this.tabla[i].getCarnet() == carnet) {
                this.tabla[i].setName(nam);
                this.tabla[i].setApellido(ap);
                this.tabla[i].setPass(pass);
            }
        }
    }
    
    private String reporte(){
        String r="";
        for (int i = 0; i < this.tabla.length; i++) {
            if(this.tabla[i] != null){
                r=r + "<tr> <td> Nombre y apellido:  "+this.tabla[i].getName()+" "+this.tabla[i].getApellido()+" Carnet: "+this.tabla[i].getCarnet()+" Password:  "+this.tabla[i].getPassword() + "</td> </tr> \n";
            }else{
                r = r + "<tr> <td> NULL </td></tr>; \n";
            }
            
        }
        return r;
    }
    
    public void report() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("report\\User.dot");

            pw = new PrintWriter(fichero);

            pw.println("digraph G { \n");
            pw.println("node [shape = none];\n");
            
            pw.println("2[label=  <<table border= \"1 \" cellspacing= \" 0 \">;\n");
            pw.println(reporte());
            pw.println("</table>>];\n");
            pw.println("}\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
           // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try{
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng report\\User.dot -o report\\User.png");
            Process p2 = Runtime.getRuntime().exec("cmd /c report\\User.png");
        }catch(Exception e2){
                            e2.printStackTrace();
        }
        

    }
    
}
