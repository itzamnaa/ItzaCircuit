package itza.example.itzacircuit.Control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.io.StreamCorruptedException;

import itza.example.itzacircuit.Elements.Element;
import itza.example.itzacircuit.Elements.Emat;

/**
 * Created by itza on 23/08/15.
 */
public class Save {

    static SaveObject sm;

    public static void save(File file){
        toSaveObject();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(sm);
            oos.close();
            fos.close();
        } catch (IOException el) {
            ui.showMessege(el.toString());
        }
    }
    public static void load(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            sm = (SaveObject) ois.readObject();
            ois.close();
            fis.close();
        }catch (ClassNotFoundException e) {
            ui.showMessege("No se puede interpretar el contenido");
            return;
        } catch (OptionalDataException e) {
            ui.showMessege(e.toString());;
            return;
        } catch (FileNotFoundException e) {
            ui.showMessege("El Archivo no existe!!!");
            return;
        } catch (StreamCorruptedException e) {
            ui.showMessege("El archivo esta Corrupto");
            return;
        } catch (IOException e) {
            ui.showMessege("Error al leer el archivo");
            return;
        }
        while(sm.alto!=Emat.alto){
            if(sm.alto>Emat.alto)
                Emat.addFila();
            else
                Emat.removeFila();
        }
        while(sm.ancho!=Emat.ancho){
            if(sm.ancho>Emat.ancho)
                Emat.addColumna();
            else
                Emat.removeColumna();
        }
        Element e;
        for (int i=0;i<sm.alto;i++){
            for (int j=0;j<sm.ancho;j++){
                e=Emat.mat[i][j];
                e.setTipo(sm.tipo[i][j]);
                e.value=sm.value[i][j];
                e.pos=sm.pos[i][j];
                e.fixpos();
                e.setImage();
            }
        }
    }
    public static void toSaveObject(){
        int x,y;
        x=Emat.alto;
        y= Emat.ancho;
        Element e;
        sm = new SaveObject();
        sm.alto=x;
        sm.ancho=y;
        sm.pos=new int[x][y];
        sm.tipo=new int[x][y];
        sm.value=new double[x][y];
        for (int i=0;i<x;i++){
            for (int j=0;j<y;j++){
                e=Emat.mat[i][j];
                sm.pos[i][j]=e.pos;
                sm.tipo[i][j]=e.tipo;
                sm.value[i][j]=e.value;
            }
        }
    }
}
class SaveObject implements Serializable {
    public int alto,ancho;
    public int tipo[][];
    public int pos[][];
    public double value[][];
}