package com.sushanta;

import java.util.*;

public class BooksController {

    public void addList(ArrayList<Books> allBooks,Books book){

        allBooks.add(book);

    }

    public void showDetails(ArrayList<Books> allBooks){
        for (Books book : allBooks){
            System.out.println("\nBook Name :" + book.getB_name() + "\nAuthor :" + book.getB_author() + "\nPrice :" + book.getB_price());
        }

    }
}
