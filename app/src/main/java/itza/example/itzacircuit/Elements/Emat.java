package itza.example.itzacircuit.Elements;

import android.graphics.Color;
import android.widget.TableLayout;
import android.widget.TableRow;
import java.util.ArrayList;

import itza.example.itzacircuit.Control.ui;
import itza.example.itzacircuit.Control.util;


/**
 * Created by itza on 29/07/15.
 */
public class Emat {
    public static Element mat[][];
    static TableLayout tabla;
    static ArrayList<TableRow> filas;
    public static int alto, ancho;

    public static void init(TableLayout tl){
        tabla=tl;
        ancho=0;
        alto=0;
        filas=new ArrayList<TableRow>();
        for(int i=0;i<5;i++){addFila();addColumna();}
        Element.selected=mat[0][0];
        Element.selected.setBackgroundColor(Color.BLUE);
        ui.setOptions();
    }

    public static void addFila(){
        TableRow tb=new TableRow(tabla.getContext());
        filas.add(tb);
        Element e;
        for(int i=0;i< ancho;i++){
            e=new Element(tb.getContext());
            tb.addView(e);
        }
        tabla.addView(tb);
        alto++;
        updateMat();
    }
    public static void addColumna(){
        Element e;
        for(TableRow tr : filas){
            e=new Element(tr.getContext());
            tr.addView(e);
        }
        ancho++;
        updateMat();
    }
    public static void removeFila(){
        if(alto >5){
            TableRow tr=filas.get(filas.size()-1);
            tr.removeAllViews();
            tabla.removeView(tr);
            filas.remove(tr);
            alto--;
            updateMat();
        }
    }
    public static void removeColumna(){
        if(ancho >5){
            Element e;
            for(TableRow tr: filas){
                tr.removeViewAt(ancho -1);
            }
            ancho--;
            updateMat();
        }
    }
    public static void updateMat(){
        Element e;
        TableRow tr;
        mat =new Element[alto][ancho];
        for(int i=0;i<filas.size();i++){
            tr=filas.get(i);
            for(int j=0;j<tr.getChildCount();j++){
                e=(Element)tr.getChildAt(j);
                e.x=i;
                e.y=j;
                mat[i][j]=e;
            }
        }
    }

    public static Element getElement(int x, int y){
        return mat[x][y];
    }

    public static void setCheck(boolean b){
        for (int i=0;i<alto;i++){
            for (int j=0;j<ancho;j++){
                mat[i][j].check=b;
            }
        }
    }

    public static boolean isEmpy(){
        for (int i=0;i<alto;i++){
            for (int j=0;j<ancho;j++){
                if (mat[i][j].tipo!= util.vacio)
                    return false;
            }
        }
        return true;
    }
    public static boolean existErrors(){
        Element e;
        for (int i=0;i<alto;i++){
            for (int j=0;j<ancho;j++){
                e=mat[i][j];
                if (util.compatible(e,e.getDer(),util.derecha))
                    return true;
                if (util.compatible(e,e.getAbajo(),util.abajo))
                    return true;
            }
        }
        return false;
    }
}
