package itza.example.itzacircuit.Nodo;

import java.util.Arrays;

/**
 * Created by itza on 27/09/2015.
 */
public class NetListElement {
    public int x,y;
    public String name;
    public double value;
    public String code;

    public NetListElement(String name,int x,int y,double value){
        this.name=name;
        this.value=value;
        this.x=x;
        this.y=y;
        NetList.netlist.add(this);
        this.generateCode();
    }
    public void generateCode(){
        String s=name+x+y+value;
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        code = new String(chars);
    }
}
