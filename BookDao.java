package com.d302.dao;

import java.util.List;

import com.d302.entity.Book;

public interface BookDao {

    /**
     *
     * @param book
     * @return
     */
    int save(Book book);

    /**
     *
     * @param id
     * @return
     */
    int delete(int id);

    /**
     *
     * @param book
     * @return
     */
    int update(Book book);

    /**
     *
     * @param id
     * @return
     */
    Book findById(int id);

    /**
     *
     * @param keyword
     * @return
     */
    List<Book> findAll(String keyword);

}
