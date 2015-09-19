package itza.example.itzacircuit.Control;

import itza.example.itzacircuit.Elements.Element;
import itza.example.itzacircuit.R;

/**
 * Created by itza on 29/07/15.
 */
public class util {
    public static final int vacio=0,conector=1,resis=2,fuente=3,tierra=4;
    public static final int arriba=0,derecha=1,abajo=2,izquierda=3;
    public static final int conectores[]={R.mipmap.conector1,R.mipmap.conector2,R.mipmap.conector3,
            R.mipmap.conector4,R.mipmap.conector5,R.mipmap.conector6,
            R.mipmap.conector7,R.mipmap.conector8,R.mipmap.conector9,
            R.mipmap.conector10,R.mipmap.conector11,R.mipmap.tierra,R.mipmap.solo};
    public static final int resistencias[]={R.mipmap.resis2,R.mipmap.resis1,R.mipmap.conector3,
            R.mipmap.conector4,R.mipmap.conector5,R.mipmap.conector6,
            R.mipmap.conector7,R.mipmap.conector8,R.mipmap.conector9,
            R.mipmap.conector10,R.mipmap.conector11,R.mipmap.tierra,R.mipmap.solo};
    public static final int fuentes[]={R.mipmap.fuente2,R.mipmap.fuente1,R.mipmap.conector3,
            R.mipmap.conector4,R.mipmap.conector5,R.mipmap.conector6,
            R.mipmap.conector7,R.mipmap.conector8,R.mipmap.conector9,
            R.mipmap.conector10,R.mipmap.conector11,R.mipmap.tierra,R.mipmap.solo};
    public static final int imgmat[][]={conectores,conectores,resistencias,fuentes,conectores};

    static final boolean f=false;
    static final boolean t=true;

    static final boolean conectar[][]={{t,f,t,f},{f,t,f,t},{f,t,t,f},{f,f,t,t},{t,f,f,t},{t,t,f,f},
            {t,t,t,f},{f,t,t,t},{t,f,t,t},{t,t,f,t},{t,t,t,t},{t,f,f,f},{f,f,f,f}};

    public static final int solopos=12;
    public static final int tierrapos=11;
    public static final int maxcon=10;
    public static final int maxcomp=1;

    public static boolean conecta(Element o, Element d,int dir){
        if (d==null)
            return false;
        switch (dir){
            case arriba:    return (conectar[o.pos][0] && conectar[d.pos][2]);
            case derecha:   return (conectar[o.pos][1] && conectar[d.pos][3]);
            case abajo:     return (conectar[o.pos][2] && conectar[d.pos][0]);
            case izquierda: return (conectar[o.pos][3] && conectar[d.pos][1]);
        }
        return false;
    }
    public static boolean compatible(Element o, Element d,int dir){
        if (d==null){
            switch (dir){
                case arriba:    return (conectar[o.pos][0]);
                case derecha:   return (conectar[o.pos][1]);
                case abajo:     return (conectar[o.pos][2]);
                case izquierda: return (conectar[o.pos][3]);
            }
        }
        switch (dir){
            case arriba:    return (conectar[o.pos][0] ^ conectar[d.pos][2]);
            case derecha:   return (conectar[o.pos][1] ^ conectar[d.pos][3]);
            case abajo:     return (conectar[o.pos][2] ^ conectar[d.pos][0]);
            case izquierda: return (conectar[o.pos][3] ^ conectar[d.pos][1]);
        }
        return false;
    }
}


