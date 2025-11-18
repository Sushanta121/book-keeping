package com.sushanta;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println((int)(Math.random() *30));
        Books b;
        ArrayList<Books> allBooks = new ArrayList<>();
        BooksController cntr= new BooksController();

        HashMap<Integer,String> hset=new HashMap<>();
        hset.put(1,"a");
        hset.put(1,"B");
        hset.put(2,"c");

        System.out.println(hset);


        String line;
        try{
            FileReader file= new FileReader("src/main/resources/BooksData.csv");
            BufferedReader bf =new BufferedReader(file);

            while((line= bf.readLine()) != null){
                String[] data= line.split(",");
                if(!data[0].equals("b_id")){
                    b=new Books();
                    b.setB_id(Integer.parseInt(data[0]));
                    b.setB_name(data[1]);
                    b.setB_author(data[2]);
                    b.setB_price(Float.parseFloat(data[3]));
                    b.setB_disc(Float.parseFloat(data[4]));

                    cntr.addList(allBooks,b);
                }
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        cntr.showDetails(allBooks);

    }
}