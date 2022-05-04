package basic.of.javastudy.absinter;

public class AbstractInterfacePractice {
    public static void main(String[] args) {

//        Book book = new Book("책", "작가");  // 추상 클래스로는 객체를 생성할 수 없습니다 - 컴파일 에러


        Novel novel1 = new Novel("해리포터", "판타지", "J.K.롤링");
        Comics comics1 = new Comics("원피스", "오다 에이치로", "액션", "오다 에이치로");

        // 자식 타입으로 객체를 생성하는 경우
        novel1.coverCase("hard");
        comics1.coverCase("hard");
        novel1.changeGenre("판타지 sf");
        comics1.changeGenre("성장 액션");


        System.out.println("\n===================================================\n");

        Book novel2 = new Novel("해리포터", "판타지", "J.K.롤링");
        Book comics2 = new Comics("원피스", "오다 에이치로", "액션", "오다 에이치로");

        novel2.flipThePages();
        comics2.flipThePages();

        // 부모 타입으로 받은 객체는 부모 타입에 존재하는 메소드만 사용할 수 있기 때문에 자식에 정의된 메소드는 사용할 수 없습니다.
//        novel2.changeGenre("판타지 sf");
//        comics2.changeGenre("성장 액션");

        System.out.println("\n===================================================\n");

        Book novel3 = new Novel("해리포터", "판타지", "J.K.롤링");
        Book comics3 = new Comics("원피스", "오다 에이치로", "액션", "오다 에이치로");

        // ClassCastException 이 발생할 수 있기 때문에 instanceof 를 이용하여 부모 타입의 변수에 어떤 객체가 할당되어 있는지 확인 후 강제 형변환을 시켜줍니다.
        // 자식 클래스로 타입 변환 후에는 자식 메소드를 사용할 수 있습니다.
        if (novel3 instanceof Novel) {
            ((Novel) novel3).changeGenre("판타지 sf");
        }

        if (comics3 instanceof Comics) {
            ((Comics)comics3).changeGenre("성장 액션");
        }

    }
}
