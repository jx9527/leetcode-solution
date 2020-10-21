package com.company;

import java.util.Arrays;

public class leet455 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int indexG=0,indexS=0;
        while (indexG < g.length && indexS<s.length){
            if (s[indexS]>=g[indexG]) indexG++;
            indexS++;
        }
        return indexG;
    }
}
