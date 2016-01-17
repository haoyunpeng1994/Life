package com.hyp.life.model.Travel;

import java.util.List;

/**
 * Created by acer on 2015/11/17.
 */
public class TravelData {
    private List<TravelBooks> books;

    private int count;

    public List<TravelBooks> getBooks() {
        return books;
    }

    public void setBooks(List<TravelBooks> books) {
        this.books = books;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "Data{" +
                "books=" + books +
                ", count=" + count +
                '}';
    }
}
