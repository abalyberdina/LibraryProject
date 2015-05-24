package com.epam.library.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.epam.library.domain.book.Book;

public class OrderView {

    private Long userId;
    private Long bookID;
    private String bookName;
    private String bookAuthors;
    private String bookGenres;
    private String dateTaken;
    public OrderView() {

    }

    public OrderView(Long userId, Book book) {
        this.userId = userId;
        this.bookID = book.getId();
        this.bookName = book.getBookName();
        StringBuilder sb = new StringBuilder(book.getGenres().get(0).getGenreName());
        for (int i = 1; i < book.getGenres().size(); i++) {
            sb.append(", ");
            sb.append(book.getGenres().get(i).getGenreName());
        }
        bookGenres = sb.toString();
        sb = new StringBuilder(book.getAuthors().get(0).getName());
        for (int i = 1; i < book.getAuthors().size(); i++) {
            sb.append(", ");
            sb.append(book.getAuthors().get(i).getName());
            sb.append(" ");
            if (book.getAuthors().get(i).getPseudonym() != null) {
                sb.append("(");
                sb.append(book.getAuthors().get(i).getPseudonym());
                sb.append(")");
            }
            sb.append(" ");
            if (book.getAuthors().get(i).getYearOfBirth() != null) {
                sb.append("-");
                sb.append(book.getAuthors().get(i).getYearOfBirth());
            }
        }
        bookAuthors = sb.toString();
        LocalDateTime actualDateTaken = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.dateTaken = formatter.format(actualDateTaken);
        // this.dateToReturn = formatter.format(current
        // .plusDays(NUM_OF_DAYS_TO_KEEP_BOOK));
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthors() {
        return bookAuthors;
    }

    public String getBookGenres() {
        return bookGenres;
    }

    public String getDateTaken() {
        return dateTaken;
    }
}
