package itza.example.itzacircuit.Elements;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import itza.example.itzacircuit.Control.ui;
import itza.example.itzacircuit.Control.util;
import itza.example.itzacircuit.R;

/**
 * Created by itza on 29/07/15.
 */
public class Element extends ImageView {
    public static Element selected;
    public int x,y,pos,tipo;
    public double value;
    public String name;
    public boolean check;
    private static OnClickListener ocl=new OnClickListener() {
        @Override
        public void onClick(View v) {
            Element e=(Element)v;
            if(e==Element.selected){
                e.rotate();
            }
            else{
                Element.selected.setBackgroundColor(Color.TRANSPARENT);
                Element.selected=e;
                e.setBackgroundColor(Color.BLUE);
            }
            ui.setInfo();
            ui.setOptions();
            e.printcon();

        }
    };

    public Element(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    public Element(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public Element(Context context) {
        super(context);
        init();
    }

    public void init(){
        name="";
        x=0;
        y=0;
        value=0.0;
        check=false;
        tipo=util.vacio;
        this.setImageResource(R.mipmap.solo);
        this.setOnClickListener(ocl);
        fixpos();
    }
    public void setTipo(int t){
        if(t!=tipo)
            value=0.0;
        tipo=t;
        fixpos();
        setImage();
        List.addElement(this);
    }
    public void setPos(int pos){

    }
    public void fixpos() {
        switch (tipo) {
            case util.vacio:
                pos = util.solopos;
                break;
            case util.tierra:
                pos = util.tierrapos;
                break;
            case util.conector: {
                if (pos > util.maxcon)
                    pos = 0;
            }
            break;
            case util.resis: {
                if (pos > util.maxcomp)
                    pos = 0;
            }
            break;
            case util.fuente: {
                if (pos > util.maxcomp)
                    pos = 0;
            }
            break;
        }
    }
    public void rotate(){
        pos++;
        fixpos();
        setImage();
    }

    public void setValue(double val){
        switch (tipo) {
            case util.vacio: value=0.0; break;
            case util.tierra: value=0.0; break;
            case util.conector: value=0.0; break;
            case util.resis:value=val; break;
            case util.fuente:value=val; break;
        }
    }
    public void setImage(){
        setImageResource(util.imgmat[tipo][pos]);
    }
    public void setSelected(){
    }
    public Element getArriba(){
        if(x==0)
            return null;
        return Emat.getElement(x-1,y);
    }
    public Element getAbajo(){
        if(x+1==Emat.alto)
            return null;
        return Emat.getElement(x+1,y);
    }
    public Element getIzq(){
        if(y==0)
            return null;
        return Emat.getElement(x,y-1);
    }
    public Element getDer(){
        if(y+1==Emat.ancho)
            return null;
        return Emat.getElement(x,y+1);
    }

    public void printcon(){
        String s="";
        if (util.compatible(this,this.getArriba(),util.arriba))
            s+="Error Arriba! ";
        if (util.compatible(this,this.getAbajo(),util.abajo))
            s+="Error Abajo! ";
        if (util.compatible(this,this.getDer(),util.derecha))
            s+="Error Der! ";
        if (util.compatible(this,this.getIzq(),util.izquierda))
            s+="Error Izq! ";
        ui.infoview.setText(s);
    }

    public boolean isComponent(){
        return false;
    }
}
