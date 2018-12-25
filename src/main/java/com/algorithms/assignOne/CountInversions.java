package com.algorithms.assignOne;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class CountInversions {

    static class vecInt {
        Vector<Integer> nums;
        long num;

        vecInt(Vector<Integer> vec, Long n){
            this.nums = vec;
            this.num = n.longValue();
        }
    }

    static vecInt mySort(Vector<Integer> nums){

        if(nums.size() == 1){
            vecInt sorted_numbers = new vecInt(nums, new Long(0));
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
        long inv = fhalfTup.num + shalfTup.num, mergeInv = 0;

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
                Long tmpInv = new Long(F - f);
                mergeInv = mergeInv + tmpInv.longValue();
                if ( inv + mergeInv < 0) {
                    System.out.println(String.format("inv: %d, mergeInv: %d", inv, mergeInv));
                }
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

        vecInt  vecInt = new vecInt(mergedVec, inv + mergeInv);
        return  vecInt;
    }

    public static void main(String[] args) throws IOException {

        Options options = new Options();

        Option input = new Option("i", "input", true, "Input file path");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("o", "output", true, "Output file path");
        output.setRequired(true);
        options.addOption(output);


        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            String inputFilePath = cmd.getOptionValue("input");
            String outputFilePath = cmd.getOptionValue("output");

            Invertor invertor = new Invertor(inputFilePath, outputFilePath);
            vecInt sortedTuple = mySort(invertor.unsorted_vector);
            invertor.set_sorted_vector(sortedTuple.nums);
            invertor.set_inversions(sortedTuple.num);
            invertor.write_sorted_vector();

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }


    }
}
