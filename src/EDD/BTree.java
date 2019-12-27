package EDD;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Oscar C
 */
public class BTree {

    public NodeB[] Raiz = new NodeB[5];

    public void Agregar(int Clave, int Contenido) {
        if (Raiz[0] == null) {
            Raiz[0] = new NodeB(Clave, Contenido);
        } else {
            this.Agregar(Clave, Contenido, Raiz);
        }

    }

    public int Buscar(int Clave) {
        return Buscar(Clave, Raiz);
    }

    private int Buscar(int Clave, NodeB[] Raiz) {
        int Salida = 0;
        if (Raiz == null) {
            return Salida;
        }
        for (int i = 0; i < Raiz.length; i++) {

            if (Raiz[i] != null) {
                if (Raiz[i].getClave() == Clave) {
                    Salida = Raiz[i].getContenido();
                } else if (Raiz[i].getClave() > Clave) {
                    Salida = Buscar(Clave, Raiz[i].getPagIzq());
                } else {
                    Salida = Buscar(Clave, Raiz[i].getPagDer());
                }
            }

            if (Salida != 0) {
                return Salida;
            }
        }

        return Salida;
    }

    private int Ocupado(NodeB[] Raiz) {
        for (int i = 0; i < Raiz.length; i++) {
            if (Raiz[i] == null) {
                return i;
            }
        }
        return 4;
    }

    private boolean EsHoja(NodeB[] Raiz) {
        return Raiz[0].EsHoja();
    }

    public String Graficar() {
        return " digraph G{ \n node [shape=record]; \n" + Graficar(this.Raiz) + "\n}";
    }

    private String Graficar(NodeB[] Raiz) {
        if (Raiz == null) {
            return "";
        }
        String Datos = "";
        String Rank = "{ rank=same;";
        for (int i = 0; i < Raiz.length; i++) {

            if (Raiz[i] != null) {
                Datos += Graficar(Raiz[i].getPagIzq());
                Datos += (Raiz[i]).NodoGrafico() + "\n";
                Rank += (Raiz[i]).getClave() + ";";
                // Hash[ shape=Mrecord  label="<>|"] 
                if (Raiz[i].getPagIzq() != null) {
                    Datos += Raiz[i].getClave() + " -> " + Raiz[i].getPagIzq()[0].getClave() + "\n";
                }
                if (Raiz[i].getPagDer() != null) {
                    Datos += Raiz[i].getClave() + " -> " + Raiz[i].getPagDer()[0].getClave() + "\n";
                }
                if (Raiz[i + 1] == null) {
                    Datos += Graficar(Raiz[i].getPagDer());
                } else {
                    Datos += Raiz[i].getClave() + " -> " + Raiz[i + 1].getClave() + "\n";
                }
            }
        }
        Rank += "}\n";
        return Datos + Rank;
    }

    public void Imprimir() {
        Imprimir(this.Raiz);
    }

    private void Imprimir(NodeB[] Raiz) {
        if (Raiz == null) {
            return;
        }
        for (int i = 0; i < Raiz.length; i++) {
            if (Raiz[i] != null) {
                Imprimir(Raiz[i].getPagIzq());
                System.out.println(Raiz[i]);
                if (Raiz[i + 1] == null) {
                    Imprimir(Raiz[i].getPagDer());
                }
            } else {
                return;
            }
        }
    }

    private NodeB GenCentral(NodeB[] Raiz) {
        Raiz[2].setPagIzq(GenIzq(Raiz));
        Raiz[2].setPagDer(GenDerecho(Raiz));
        Raiz[0] = Raiz[2];
        for (int i = 1; i < Raiz.length; i++) {
            Raiz[i] = null;
        }
        return Raiz[0];
    }

    private NodeB[] GenDerecho(NodeB[] Raiz) {
        NodeB[] Nuevo = new NodeB[5];
        Nuevo[0] = Raiz[3];
        Nuevo[1] = Raiz[4];
        return Nuevo;
    }

    private NodeB[] GenIzq(NodeB[] Raiz) {
        NodeB[] Nuevo = new NodeB[5];
        Nuevo[0] = Raiz[0];
        Nuevo[1] = Raiz[1];
        return Nuevo;
    }

    private NodeB Agregar(int Clave, int Contenido, NodeB[] Raiz) {
        NodeB Llenado = null;

        if (EsHoja(Raiz)) {

            for (int i = 0; i < Raiz.length; i++) {

                if (Raiz[i] == null) {
                    Raiz[i] = new NodeB(Clave, Contenido);
                    break;
                } else if (Raiz[i].getClave() > Clave) {
                    Raiz = Mover(Raiz, i);
                    Raiz[i] = new NodeB(Clave, Contenido);
                    break;
                }
            }

        } else {

            for (int i = 0; i < Raiz.length; i++) {
                if (Raiz[i] != null) {
                    if (Raiz[i + 1] != null) {
                        if (Raiz[i].getClave() > Clave) {
                            Llenado = Agregar(Clave, Contenido, Raiz[i].getPagIzq());
                        } else if (Raiz[i + 1].getClave() > Clave) {
                            Llenado = Agregar(Clave, Contenido, Raiz[i].getPagDer());
                        }
                    } else {
                        if (Raiz[i].getClave() > Clave) {
                            Llenado = Agregar(Clave, Contenido, Raiz[i].getPagIzq());
                        } else if (Raiz[i].getClave() < Clave) {
                            Llenado = Agregar(Clave, Contenido, Raiz[i].getPagDer());
                        }
                    }

                }
            }
        }

        if (Llenado != null) {
            for (int i = 0; i < Raiz.length; i++) {
                if (Raiz[i] == null) {
                    Raiz[i - 1].setPagDer(Llenado.getPagIzq());
                    Raiz[i] = Llenado;
                    break;
                }
            }
        }
        if (Raiz[Raiz.length - 1] != null) {
            System.out.println(Raiz[4]);
            NodeB Nodito = GenCentral(Raiz);
            return Nodito;
        } else {
            return null;
        }

    }

    private NodeB[] Mover(NodeB[] Raiz, int Indice) {
        for (int i = Raiz.length - 1; i > Indice; i--) {
            Raiz[i] = Raiz[i - 1];
        }

        return Raiz;
    }

    public void report() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("report\\BTree.dot");

            pw = new PrintWriter(fichero);

            pw.println(Graficar());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            Process p = Runtime.getRuntime().exec("cmd /c dot.exe -Tpng report\\BTree.dot -o report\\BTree.png");
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }
}
