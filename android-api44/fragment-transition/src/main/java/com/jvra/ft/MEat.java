package com.jvra.ft;

/**
 * Created by Jansel R. Abreu (Vanwolf) on 3/26/2015.
 */
public class Meat {
    public int resourceId;
    public String title;

    public Meat(int resourceId, String title) {
        this.resourceId = resourceId;
        this.title = title;
    }

    public static final Meat[] MEATS = {
            new Meat(R.drawable.p1, "First"),
            new Meat(R.drawable.p2, "Second"),
            new Meat(R.drawable.p3, "Third"),
            new Meat(R.drawable.p4, "Fourth"),
            new Meat(R.drawable.p5, "Fifth"),
            new Meat(R.drawable.p6, "Sixth"),
            new Meat(R.drawable.p7, "Seventh"),
            new Meat(R.drawable.p8, "Eighth"),
            new Meat(R.drawable.p9, "Ninth"),
            new Meat(R.drawable.p10, "Tenth"),
            new Meat(R.drawable.p11, "Eleventh"),
    };

}
