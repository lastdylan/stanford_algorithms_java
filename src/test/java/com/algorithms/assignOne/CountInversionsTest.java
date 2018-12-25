package com.algorithms.assignOne;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import static org.junit.Assert.assertEquals;

public class CountInversionsTest {

    Integer[] arrayOne = {3, 7, 1, 4, 2};
    Integer[] farrayOne = {3, 7}, sarrayOne = {1, 4, 2};
    Integer[] sorted_arrayOne = {1, 2, 3, 4, 7};
    Integer[] sorted_farrayOne = {3, 7}, sorted_sarrayOnee = {1, 2, 4};
    long invOne = 6;
    Vector<Integer> vecOne = new Vector<>(Arrays.asList(arrayOne));
    Vector<Integer> sorted_vecOne = new Vector<>(Arrays.asList(sorted_arrayOne));
    Vector<Integer> fVecOne = new Vector<>(Arrays.asList(farrayOne));
    Vector<Integer> sVecOne = new Vector<>(Arrays.asList(sarrayOne));
    Vector<Integer> sorted_fVecOne = new Vector<>(Arrays.asList(sorted_farrayOne));
    Vector<Integer> sorted_sVecOne = new Vector<>(Arrays.asList(sorted_sarrayOnee));

    @Test
    public void mySort() {

        CountInversions countInversions = new CountInversions();
        CountInversions.vecInt vecOne_tup = countInversions.mySort(this.vecOne);
        assertEquals(this.invOne, vecOne_tup.num);
        assertEquals(this.sorted_vecOne, vecOne_tup.nums);
    }

    @Test
    public void splitVector() {

        CountInversions countInversions = new CountInversions();

        ArrayList<Vector<Integer>> split = countInversions.splitVector(this.vecOne);

        System.out.println(split.get(0));
        System.out.println(split.get(1));

        assertEquals(this.fVecOne, split.get(0));
        assertEquals(this.sVecOne, split.get(1));
    }

    @Test
    public void countMerge() {
        CountInversions.vecInt ftup = new CountInversions.vecInt(sorted_fVecOne, new Long(0));
        CountInversions.vecInt stup = new CountInversions.vecInt(sorted_sVecOne, new Long(1));

        CountInversions.vecInt cmergeTup = CountInversions.countMerge(ftup, stup);

        assertEquals(new Long(6).longValue(), cmergeTup.num);
        assertEquals(sorted_vecOne, cmergeTup.nums);
    }
}
