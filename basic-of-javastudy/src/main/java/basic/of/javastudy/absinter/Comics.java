package basic.of.javastudy.absinter;

import lombok.Getter;

@Getter
public class Comics extends Book{

    private String drawer;
    private String genre;
    private int seriesCount;
    private int currentSeries;
    private final String defaultCaseType = "soft";

    public Comics(String title, String author, String genre, String drawer) {
        super(title, author);
        this.genre = genre;
        this.drawer = drawer;
        seriesCount++;
        currentSeries++;
    }

    public void changeGenre(String genre) {
        this.genre = genre;
        System.out.printf("만화책 장르를 %s로 변경합니다.%n", genre);
    }

    @Override
    protected void flipThePages() {
        if (currentPages < pages) {
            currentPages++;
            System.out.println("만화책을 다음 페이지로 넘깁니다.");
        } else {
            System.out.printf("마지막 장입니다.%s", currentSeries <= seriesCount ? " 다음권이 곧 발매 예정입니다." : " 다음권을 읽어보세요!");
        }
    }

    @Override
    protected void coverCase(String caseType) {
        if("hard".equalsIgnoreCase(caseType)) {
            caseType = defaultCaseType;
        }
         super.caseType = caseType;
        System.out.printf("만화책 케이스를 %s 케이스로 코팅합니다.%n", caseType);
    }
}
