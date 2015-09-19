package itza.example.itzacircuit.Control;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

import itza.example.itzacircuit.Elements.Element;
import itza.example.itzacircuit.Elements.Emat;
import itza.example.itzacircuit.ItzaCircuit;

/**
 * Created by itza on 28/07/15.
 */
public class ui {
    public static ViewFlipper buttonsflipper =null;
    public static ViewSwitcher viewswitcher =null;
    public static ImageButton baddrow,bdeleterow,baddcolumn,bdeletecolumn;
    public static ImageButton bvacio,bresis,bfuente,btierra,bconector;
    public static TableLayout tabla=null;
    public static Context context=null;
    public static Activity activity=null;
    public static EditText valuein;
    public static TextView nameview,infoview,nodeview;

    public static void init(){
        baddcolumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emat.addColumna();
            }
        });
        baddrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emat.addFila();
            }
        });
        bdeletecolumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emat.removeColumna();
            }
        });
        bdeleterow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Emat.removeFila();
            }
        });
        bvacio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Element.selected.setTipo(util.vacio);ui.setOptions();
            }
        });
        bconector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Element.selected.setTipo(util.conector);ui.setOptions();
            }
        });
        bresis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Element.selected.setTipo(util.resis);
                ui.setOptions();
            }
        });
        bfuente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Element.selected.setTipo(util.fuente);
                ui.setOptions();
            }
        });
        btierra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Element.selected.setTipo(util.tierra);
                ui.setOptions();
            }
        });
        valuein.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Element.selected.setValue(Double.valueOf(valuein.getText().toString()));
                return false;
            }
        });
    }
    public static void setOptions(){

        switch (Element.selected.tipo) {
            case util.vacio:{
                if(viewswitcher.getDisplayedChild()==0)
                    viewswitcher.setDisplayedChild(1);
            }break;
            case util.tierra:{
                if(viewswitcher.getDisplayedChild()==0)
                    viewswitcher.setDisplayedChild(1);
            }break;
            case util.conector:{
                if(viewswitcher.getDisplayedChild()==0)
                    viewswitcher.setDisplayedChild(1);
            }break;
            case util.resis:{
                if(viewswitcher.getDisplayedChild()==1)
                    viewswitcher.setDisplayedChild(0);
            }break;
            case util.fuente:{
                if(viewswitcher.getDisplayedChild()==1)
                    viewswitcher.setDisplayedChild(0);
            }break;
        }
    }

    public static void setInfo(){
        valuein.setText(String.valueOf(Element.selected.value));
        nameview.setText(Element.selected.name);
    }

    public void switchtamplate(){
        switch (Element.selected.tipo) {
            case util.vacio:{}break;
            case util.tierra:{}break;
            case util.conector:{}break;
            case util.resis:{}break;
            case util.fuente:{}break;
        }
    }
    public static void showMessege(String m){
        Toast.makeText(context,m,Toast.LENGTH_LONG).show();
    }
    public static void showMessege(String m,Context c){
        Toast.makeText(activity,m,Toast.LENGTH_LONG).show();
    }

}

