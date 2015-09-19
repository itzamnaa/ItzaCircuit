package itza.example.itzacircuit.Nodo;

import java.util.ArrayList;
import java.util.Arrays;

import itza.example.itzacircuit.Control.ui;
import itza.example.itzacircuit.Control.util;
import itza.example.itzacircuit.Elements.Element;
import itza.example.itzacircuit.Elements.Emat;
import itza.example.itzacircuit.Elements.List;

/**
 * Created by itza on 26/08/15.
 */
public class Nodo {
    public static ArrayList<Nodo> nodos;
    public ArrayList<Element> elements;
    public String code;
    public String name;
    public int n;

    public Nodo(){
        elements=new ArrayList<Element>();
        nodos.add(this);
    }

    public static void init(){
        nodos=new ArrayList<Nodo>();
    }
    public void addElement(Element e){
        elements.remove(e);
        elements.add(e);
    }
    public void generateCode(){
        String s="";
        for(Element e:elements){
            s += e.name;
        }
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        code = new String(chars);
    }
    public void clean(){
    }
    public static String nodostoString(){
        String s="";
        for (Nodo n:nodos){
            for(Element e:n.elements){
                if (e.tipo!=util.conector)
                    s+=e.name+"|";
            }
            s+="\n";
        }
        s+="Lista de "+nodos.size()+" elemetos";
        return s;
    }
    public static void makeNodos() {
        nodos=new ArrayList<Nodo>();
        for(Element e: List.conectores){
            Nodo n=new Nodo();
            makeNodo(n,e);
            n.generateCode();
            Emat.setCheck(false);
        }
    }
    public static void makeNodo(Nodo n,Element e) {
        if (e.check)
            return;
        n.addElement(e);
        e.check=true;
        if(e.tipo!=util.conector)
            return;
        if (e.tipo==util.conector){
            if (util.conecta(e, e.getArriba(), util.arriba))
                makeNodo(n, e.getArriba());
            if (util.conecta(e, e.getAbajo(), util.abajo))
                makeNodo(n,e.getAbajo());
            if (util.conecta(e, e.getDer(), util.derecha))
                makeNodo(n,e.getDer());
            if (util.conecta(e, e.getIzq(), util.izquierda))
                makeNodo(n,e.getIzq());
        }
    }
}
