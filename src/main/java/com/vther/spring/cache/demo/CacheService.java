package com.vther.spring.cache.demo;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Wither
 */
@Component
@CacheConfig(cacheManager = "cacheManager")
public class CacheService {

    private static void sleep2Seconds() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 自动使用注解
    @Cacheable(cacheManager = "cacheManager", cacheNames = {"books"}, key = "#id")
    public Book findBook(int id) {
        sleep2Seconds();
        Book book = new Book(id, "name", LocalDateTime.now());
        System.out.println("findBook = " + book);
        return book;
    }

    // 每次都会执行，适用于更新的场景，可以避免再去查询一次
    @CachePut(cacheManager = "cacheManager", cacheNames = {"books", "books2"}, key = "#book.id")
    public Book updateBook(Book book) {
        System.out.println("updateBook() book = " + book);
        sleep2Seconds();
        return book;
    }

    // 每次都会执行，适用于更新的场景，可以避免再去查询一次
    @CacheEvict(cacheManager = "cacheManager", cacheNames = {"books", "books2"}, key = "#book.id")
    public Book deleteBook(Book book) {
        System.out.println("deleteBook() book = " + book);
        sleep2Seconds();
        return book;
    }

    @Scheduled(fixedDelay = 1000)
    @CacheEvict(cacheManager = "cacheManager", cacheNames = {"books", "books2"}, allEntries = true)
    public void scheduledEvict() {
    }

    @Scheduled(fixedDelay = 500)
    @CacheEvict(cacheManager = "cacheManager2", cacheNames = {"books3"}, key = "5")
    public void scheduledEvict2() {
        System.out.println("true = " + true);
    }

    @Cacheable(cacheManager = "cacheManager2", cacheNames = {"books3"}, key = "#id")
    public Book findBook2(String id) {
        sleep2Seconds();
        Book book = new Book(123, "name", LocalDateTime.now());
        System.out.println("findBook = " + book);
        return book;
    }
}
