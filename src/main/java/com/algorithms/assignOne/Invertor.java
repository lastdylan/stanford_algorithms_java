package com.algorithms.assignOne;

import java.io.*;
import java.util.Vector;

public class Invertor
{
    String filepath;
    String output_filepath;
    Vector<Integer> unsorted_vector;
    Vector<Integer> sorted_vector;
    Integer inversions;

    public Invertor(String ipath, String opath){
        this.filepath = ipath;
        this.output_filepath = opath;
        this.set_unsorted_vector();
    }

    public void set_unsorted_vector(){

        try {
            File f = new File(filepath);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String readline = "";
            Vector<Integer> numbers = new Vector();

            while ((readline = br.readLine()) != null) {
                numbers.add(Integer.valueOf(readline));
            }

            unsorted_vector = numbers;

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void set_sorted_vector(Vector<Integer> numbers){
        sorted_vector = numbers;
    }

    public void set_inversions(int inv){
        inversions = inv;
    }

    public void write_sorted_vector() throws IOException {

        File file = new File(output_filepath);

        if (!file.exists()){
            throw new IOException();
        }

        FileWriter fw = new FileWriter(file);
        fw.write(String.format("It took %d inversions to sort this file", inversions));
        fw.write("-------------");
        fw.write("");

        for(Integer number: sorted_vector){
            fw.write(String.format("%d", number));
        }

        fw.close();
    }

}
