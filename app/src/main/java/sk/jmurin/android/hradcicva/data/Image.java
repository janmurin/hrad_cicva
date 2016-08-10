package sk.jmurin.android.hradcicva.data;

import java.io.Serializable;

/**
 * Created by jan.murin on 08-Aug-16.
 */
public class Image implements Serializable{

    public String name;
    public String title;
    public int resID;

    public Image(){

    }

    public Image(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
