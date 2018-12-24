package com.algorithms.assignOne;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class CountInversions {

    static class vecInt {
        Vector<Integer> nums;
        Integer num;

        vecInt(Vector<Integer> vec, Integer n){
            this.nums = vec;
            this.num = n;
        }
    }

    static vecInt mySort(Vector<Integer> nums){

        if(nums.size() == 1){
            vecInt sorted_numbers = new vecInt(nums, 0);
            return sorted_numbers;
        }
        else{
            ArrayList<Vector<Integer>> half_splits = splitVector(nums);
            Vector<Integer> fhalf = half_splits.get(0), shalf = half_splits.get(1);

            vecInt fhalf_sort = mySort(fhalf), shalf_sort = mySort(shalf);

            return countMerge(fhalf_sort, shalf_sort);
        }
    }

    static ArrayList<Vector<Integer>> splitVector(Vector<Integer> my_vec){
        Integer half_size = my_vec.size() / 2;

        Vector<Integer> fhalf = new Vector<>();
        Vector<Integer> shalf = new Vector<>();
        Integer n = 0;

        while(n < half_size){
            fhalf.add(my_vec.get(n));
            n = n+1;
        }
        while(n < my_vec.size()){
            shalf.add(my_vec.get(n));
            n = n+1;
        }

        ArrayList<Vector<Integer>> splits = new ArrayList<Vector<Integer>>();
        splits.add(fhalf);
        splits.add(shalf);

        return splits;
    }

    static vecInt countMerge(vecInt fhalfTup, vecInt shalfTup){

        Vector<Integer> fhalf = fhalfTup.nums, shalf = shalfTup.nums;
        Integer inv = fhalfTup.num + shalfTup.num;

        Integer f = 0, s = 0;
        Integer F = fhalf.size(), S = shalf.size();
        Vector<Integer> mergedVec = new Vector<Integer>();

        while ((f < F) && (s < S)){
            Integer fElem = fhalf.get(f), sElem = shalf.get(s);
            if (fElem < sElem){
                mergedVec.add(fElem);
                f = f+1;
            }
            else{
                mergedVec.add(sElem);
                s = s+1;
                Integer tmpInv = F - f;
                inv = inv + tmpInv;
            }
        }

        while (f < F){
            mergedVec.add(fhalf.get(f));
            f = f+1;
        }

        while (s < S){
            mergedVec.add(shalf.get(s));
            s = s+1;
        }

        vecInt  vecInt = new vecInt(mergedVec, inv);

        return  vecInt;
    }

    public static void main(String[] args) throws IOException {

        Invertor invertor = new Invertor(args[0], args[1]);
        vecInt sortedTuple = mySort(invertor.unsorted_vector);
        invertor.set_sorted_vector(sortedTuple.nums);
        invertor.set_inversions(sortedTuple.num);
        invertor.write_sorted_vector();

    }
}
