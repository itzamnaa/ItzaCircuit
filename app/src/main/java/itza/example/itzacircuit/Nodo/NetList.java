package itza.example.itzacircuit.Nodo;

import java.util.ArrayList;

import itza.example.itzacircuit.Control.util;
import itza.example.itzacircuit.Elements.Element;

/**
 * Created by itza on 27/09/2015.
 */
public class NetList {
    public static ArrayList<NetListElement> netlist;

    public static void init(){
        netlist=new ArrayList<NetListElement>();
    }
    public static void makeNetList(){
        for(Nodo a:Nodo.nodos){
            for (Nodo b:Nodo.nodos){
                for (Element x:a.elements){
                    for (Element y:b.elements){
                        if (a!=b){
                            if (x==y){
                                if (x.tipo!=util.conector){
                                    new NetListElement(x.name,a.n,b.n,x.value);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static void clean(){
        int j,i;
        NetListElement a,b;
        for(i=0;i<netlist.size();i++){
            a=netlist.get(i);
            for (j=i+1;j<netlist.size();j++){
                b=netlist.get(j);
                if (a.name.equals(b.name)){
                    netlist.remove(j);
                    j--;
                }
            }
        }
    }

    public static String NetListtoString(){
        String s="";
        for (NetListElement nle:netlist){
            s+=nle.name+"\t";
            s+=nle.x+"\t";
            s+=nle.y+"\t";
            s+=nle.value+"\n";
        }
        return s;
    }
}
