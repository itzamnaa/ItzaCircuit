package itza.example.itzacircuit.Elements;

import java.util.ArrayList;

import itza.example.itzacircuit.Control.ui;
import itza.example.itzacircuit.Control.util;

/**
 * Created by itza on 23/08/15.
 */
public class List {
    public static ArrayList<Element> conectores,resistencias,fuentes,tierras,todos;

    public static void init(){
        conectores=new ArrayList<Element>();
        resistencias=new ArrayList<Element>();
        fuentes=new ArrayList<Element>();
        tierras=new ArrayList<Element>();
        todos=new ArrayList<Element>();
    }
    public static void addElement(Element e){
        todos.remove(e);
        todos.add(e);
        conectores.remove(e);
        resistencias.remove(e);
        fuentes.remove(e);
        tierras.remove(e);
        switch (e.tipo) {
            case util.vacio:{}break;
            case util.tierra:{tierras.add(e);}break;
            case util.conector:{conectores.add(e);}break;
            case util.resis:{resistencias.add(e);}break;
            case util.fuente:{fuentes.add(e);}break;
        }
        if (tierras.size()>1){
            ui.showMessege("Solo puede eistir una tierra");
            tierras.remove(e);
            e.setTipo(util.vacio);
        }
        setNames();
    }
    public static void setNames(){
        Element e;
        int i;
        for(i=0;i<todos.size();i++){
            todos.get(i).name="";
        }
        for(i=0;i<resistencias.size();i++){
            resistencias.get(i).name="R"+(i+1);
        }
        for(i=0;i<fuentes.size();i++){
            fuentes.get(i).name="V"+(i+1);
        }
        if(!tierras.isEmpty())
        tierras.get(0).name="GR";
    }
}
