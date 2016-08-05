package com.globallogic.training.hradcicva.data;

import android.content.res.Resources;
import android.widget.Toast;

import com.globallogic.training.hradcicva.R;
import com.globallogic.training.hradcicva.gui.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jan.murin on 29-Jul-16.
 */
public class Database {

    public static final String TAG = Database.class.getSimpleName();
    private static List<Topic> topics = new ArrayList<>();
    private static final String[] buttonNames;
    private static final ArrayList<List<String>> tabNames;
    private static List<Integer> headerImageResIDs = new ArrayList<>();

    static {
        Topic m0 = new Topic();
        m0.name="O hrade";
        Article a00 = new Article();
        a00.title="História hradu Čičva";
        a00.tabName="História";
        a00.assetUrl="historia.html";
        a00.images.add("historia_1");
        a00.images.add("historia_2");
        a00.images.add("historia_3");
        a00.images.add("historia_4");
        m0.articles.add(a00);
        Article a01 = new Article();
        a01.title="Majitelia hradu";
        a01.tabName="Majitelia";
        a01.assetUrl="majitelia.html";
        a01.images.add("majitelia_1");
        a01.images.add("majitelia_2");
        a01.images.add("majitelia_3");
        m0.articles.add(a01);
        Article a02 = new Article();
        a02.title="Povesti o hrade";
        a02.tabName="Povesti";
        a02.assetUrl="povesti.html";
        a02.images.add("povesti_1");
        a02.images.add("povesti_2");
        a02.images.add("povesti_3");
        a02.images.add("povesti_4");
        m0.articles.add(a02);
        Article a03 = new Article();
        a03.title="Ako sa dostať na hrad";
        a03.tabName="Prístup";
        a03.assetUrl="pristup.html";
        a03.images.add("pristup_1");
        a03.images.add("pristup_2");
        m0.articles.add(a03);
        topics.add(m0);
        Topic m1 = new Topic();
        m1.name="Prehliadka hradu";
        Article a10 = new Article();
        a10.title="Úvod";
        a10.tabName="Úvod";
        a10.assetUrl="uvod.html";
        a10.images.add("uvod_1");
        a10.images.add("uvod_2");
        a10.images.add("uvod_3");
        m1.articles.add(a10);
        Article a11 = new Article();
        a11.title="Bergfrit";
        a11.tabName="Bergfrit";
        a11.assetUrl="bergfrit.html";
        a11.images.add("bergfrit_1");
        a11.images.add("bergfrit_2");
        a11.images.add("bergfrit_3");
        m1.articles.add(a11);
        Article a12 = new Article();
        a12.title="Nádvorie hradu";
        a12.tabName="Nádvorie";
        a12.assetUrl="nadvorie.html";
        a12.images.add("nadvorie_1");
        a12.images.add("nadvorie_2");
        a12.images.add("nadvorie_3");
        a12.images.add("nadvorie_4");
        m1.articles.add(a12);
        Article a13 = new Article();
        a13.title="Palácová časť";
        a13.tabName="Palác";
        a13.assetUrl="palac.html";
        a13.images.add("palac_1");
        a13.images.add("palac_2");
        a13.images.add("palac_3");
        m1.articles.add(a13);
        topics.add(m1);
        Topic m2 = new Topic();
        m2.name="Podujatia";
        Article a20 = new Article();
        a20.title="Obnova hradu Čičva";
        a20.tabName="Obnova";
        a20.assetUrl="obnova.html";
        a20.images.add("obnova_1");
        a20.images.add("obnova_2");
        a20.images.add("obnova_3");
        m2.articles.add(a20);
        Article a21 = new Article();
        a21.title="Brigády na hrade";
        a21.tabName="Brigády";
        a21.assetUrl="brigady.html";
        a21.images.add("brigady_1");
        a21.images.add("brigady_2");
        a21.images.add("brigady_3");
        m2.articles.add(a21);
        Article a22 = new Article();
        a22.title="Akcie";
        a22.tabName="Akcie";
        a22.assetUrl="akcie.html";
        a22.images.add("akcie_1");
        a22.images.add("akcie_2");
        a22.images.add("akcie_3");
        a22.images.add("akcie_4");
        a22.images.add("akcie_5");
        a22.images.add("akcie_6");
        a22.images.add("akcie_7");
        a22.images.add("akcie_8");
        m2.articles.add(a22);
        Article a23 = new Article();
        a23.title="Okolie hradu";
        a23.tabName="Okolie";
        a23.assetUrl="okolie.html";
        a23.images.add("okolie_1");
        a23.images.add("okolie_2");
        a23.images.add("okolie_3");
        a23.images.add("okolie_4");
        a23.images.add("pristup_1");
        m2.articles.add(a23);
        topics.add(m2);
        Topic m3 = new Topic();
        m3.name="PFHC o.z.";
        Article a30 = new Article();
        a30.title="Združenie";
        a30.tabName="Združenie";
        a30.assetUrl="zdruzenie.html";
        a30.images.add("zdruzenie_1");
        a30.images.add("zdruzenie_2");
        m3.articles.add(a30);
        Article a31 = new Article();
        a31.title="Kontakty";
        a31.tabName="Kontakty";
        a31.assetUrl="kontakt.html";
        m3.articles.add(a31);
        Article a32 = new Article();
        a32.title="Sponzori";
        a32.tabName="Sponzori";
        a32.assetUrl="sponzori.html";
        m3.articles.add(a32);
        topics.add(m3);

        buttonNames = new String[topics.size()];
        tabNames = new ArrayList<>();
        Resources resources = MainActivity.CONTEXT.getResources();
        for (int i = 0; i < topics.size(); i++) {
            Topic topic = topics.get(i);
            buttonNames[i] = topic.name;
            List<String> tabs = new ArrayList<>();
            for (int j = 0; j < topic.articles.size(); j++) {
                Article a = topic.articles.get(j);
                tabs.add(a.tabName);
                for (int k = 0; k < a.images.size(); k++) {
                    int resID = resources.getIdentifier(a.images.get(k), "drawable", MainActivity.CONTEXT.getPackageName());
                    a.imageIDs.add(resID);
                    if (resID == 0) {
                        Toast.makeText(MainActivity.CONTEXT, "Image not found! [" + a.images.get(k) + "]", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            tabNames.add(tabs);
        }

        headerImageResIDs.add(R.drawable.header_1);
        headerImageResIDs.add(R.drawable.header_2);
        headerImageResIDs.add(R.drawable.header_3);

    }


    public static List<String> getButtonNames() {
        return Arrays.asList(buttonNames);
    }

    public static String[] getTabs(int buttonID) {
        return tabNames.get(buttonID).toArray(new String[tabNames.get(buttonID).size()]);
    }

    public static String getTopicName(int menuID) {
        return topics.get(menuID).name;
    }

    public static Article getArticle(int buttonID, int position) {
        return topics.get(buttonID).articles.get(position);
    }


    public static ArrayList<Integer> getHeaderImageResIDsList() {
        return (ArrayList<Integer>) headerImageResIDs;
    }

    public static int getButtonDrawable(int position) {
        switch (position) {
            case 0:
                return R.drawable.povesti_2;
            case 1:
                return R.drawable.bergfrit_1;
            case 2:
                return R.drawable.akcie_1;
            case 3:
                return R.drawable.pfhc;
            default:
                return R.drawable.header_3;
        }
    }

    public static int getHeaderDrawable() {
        return R.drawable.header_1;
    }
}
