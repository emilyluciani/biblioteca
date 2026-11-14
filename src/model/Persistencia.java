package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Persistencia {

    public void salvar(Object objeto, String arquivo) throws Exception {
        ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(arquivo));
        saida.writeObject(objeto);
        saida.close();
    }

    public Object carregar(String arquivo) throws Exception {
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arquivo));
        Object objeto = entrada.readObject();
        entrada.close();
        return objeto;
    }
}