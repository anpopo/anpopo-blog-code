package basic.of.javastudy.absinter;

import lombok.Getter;

@Getter
public class Novel extends Book {
    private String genre;

    public Novel(String title, String genre, String author) {
        super(title, author);
        this.genre = genre;
    }

    public void changeGenre(String genre) {
        this.genre = genre;
        System.out.printf("소설의 장르를 %s로 변경합니다.%n", genre);
    }

    @Override
    protected void coverCase(String caseType) {
        this.caseType = caseType;
//        super.caseType = caseType;
        System.out.printf("소설책 케이스를 %s 케이스로 코팅합니다.%n", caseType);
    }
}
