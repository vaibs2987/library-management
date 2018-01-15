package com.library.service;

import java.util.List;
import java.util.Set;

import com.library.model.Book;

public interface LibraryService {

	Long addBookToLibrary(Book book);

	Book getBookById(Long bookId);

	boolean deleteBookFromLibrary(Long bookId);

	Set<Book> getAllAvailableBooksByCategory(Long catgoryId);

	List<Book> searchBookByCategory(String param, Long categoryId);

	List<Book> searchAllBooks(String param);

}
