package basic.of.javastudy.constructortortor;

public class ConstructorMain {

    public static void main(String[] args) {
        Cafe ediya1 = new EdiyaCoffee("서울시 강남구 역삼동");
        System.out.println(ediya1.getName());
        System.out.println(ediya1.getLocation());


        Cafe ediya2 = new EdiyaCoffee("서울시 강남구 신사동");
        System.out.println(ediya2.getName());
        System.out.println(ediya2.getLocation());

    }

}

class Cafe {
    private String name;
    private String location;

    public Cafe(String name, String location) {
        this.name = name;
        this.location = location;
    }


    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}

class EdiyaCoffee extends Cafe {


    public EdiyaCoffee(String location) {
        super("Ediya Coffee", location);
        System.out.println("super 키워드는 반드시 첫줄에 들어가야 합니다.");

    }
}


