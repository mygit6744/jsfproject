package com.yugandhar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.yugandhar.entity.Books;

public class DatabaseOperations {
    private static final String PERSISTENCE_UNIT_NAME = "myUnit";
    private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    private static EntityTransaction transactionObj = entityMgrObj.getTransaction();
    
    @SuppressWarnings("unchecked")
    public static List<Books> getAllBooks() {
    	System.out.println("entityMgrObj>>>" + entityMgrObj);
        Query queryObj = entityMgrObj.createQuery("SELECT s FROM Books s");
        List<Books> bookList = queryObj.getResultList();
        if (bookList != null && bookList.size() > 0) {           
            return bookList;
        } else {
            return null;
        }
    }
    public static void createNewBook(Books book) {
        if(!transactionObj.isActive()) {
            transactionObj.begin();
        }
        entityMgrObj.persist(book);
        transactionObj.commit();
       
    }
 
}