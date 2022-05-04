package basic.of.javastudy.absinter;

import lombok.Getter;

@Getter
public abstract class Book {
    protected String title;
    protected double price;
    protected String author;
    protected String publisher;
    protected int pages;
    protected String nation;
    protected int currentPages;
    protected String caseType;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.currentPages = 0;
        System.out.printf("%s 추상 클래스를 생성합니다.%n", title);
    }

    protected void flipThePages() {
        if (currentPages < pages) {
            currentPages++;
            System.out.println("다음 페이지로 넘깁니다.");
        } else {
            System.out.println("마지막 장입니다.");
        }
    }

    protected abstract void coverCase(String caseType);
}
