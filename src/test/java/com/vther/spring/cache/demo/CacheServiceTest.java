package com.vther.spring.cache.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheServiceTest {


    @Autowired
    private CacheService service;
    @Autowired
    private CacheManager cacheManager; //cacheManager = org.springframework.cache.concurrent.ConcurrentMapCacheManager@35f26e72

    @Test
    public void findBook() throws Exception {
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < 3; i++) {
            LocalTime start = LocalTime.now();
            Book book = service.findBook(5);
            System.out.println(String.format("[%s] times, costs [%s] ---", i, Duration.between(start, LocalTime.now())) + book);
        }
        System.out.println("--------------------------------------------------");
    }

    @Test
    public void updateBook() throws Exception {
        System.out.println("--------------------------------------------------");
        Book book = new Book(10, "name update", LocalDateTime.now());
        service.updateBook(book);
        LocalTime start = LocalTime.now();
        book = service.findBook(10);
        System.out.println(String.format("costs [%s] ---", Duration.between(start, LocalTime.now())) + book);
        System.out.println("--------------------------------------------------");
    }

    @Test
    public void deleteBook() throws Exception {
        System.out.println("--------------------------------------------------");
        LocalTime start = LocalTime.now();
        Book book = service.findBook(10);
        System.out.println(String.format("costs [%s] ---", Duration.between(start, LocalTime.now())) + book);

        book = new Book(10, "name update", LocalDateTime.now());
        service.deleteBook(book);
        start = LocalTime.now();
        book = service.findBook(10);
        System.out.println(String.format("costs [%s] ---", Duration.between(start, LocalTime.now())) + book);
        System.out.println("--------------------------------------------------");
    }

    //@Test
    //public void scheduledEvict() throws Exception {
    //}

    @Test
    public void findBook2() throws Exception {
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < 10; i++) {
            LocalTime start = LocalTime.now();
            Book book = service.findBook2("5");
            Thread.sleep(1000);
            System.out.println(String.format("[%s] times, costs [%s] ---", i, Duration.between(start, LocalTime.now())) + book);
        }
        System.out.println("--------------------------------------------------");
    }

}