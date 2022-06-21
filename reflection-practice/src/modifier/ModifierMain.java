package modifier;

import modifier.auction.Auction;
import modifier.auction.Bid;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ModifierMain {

    public static void main(String[] args) {
        runAuction();

        printClassModifier(Auction.class);
        printClassModifier(Bid.class);
        printClassModifier(Bid.Builder.BidImpl.class);
        printClassModifier(Bid.Builder.class);

        System.out.println("======================");

        printMethodModifier(Auction.class.getDeclaredMethods());

        System.out.println("======================");

        printFieldModifier(Auction.class.getDeclaredFields());
        printFieldModifier(Bid.class.getDeclaredFields());
        printFieldModifier(Bid.Builder.BidImpl.class.getDeclaredFields());
        printFieldModifier(Bid.Builder.class.getDeclaredFields());

    }

    private static void printFieldModifier(Field[] fields) {
        for (Field field : fields) {
            int modifiers = field.getModifiers();

            System.out.println(String.format("Field \"%s\" access modifier is %s", field.getName(), getAccessModifier(modifiers)));

            if (Modifier.isFinal(modifiers)) {
                System.out.println("the class is final");
            }

            if (Modifier.isTransient(modifiers)) {
                System.out.println("the class is transient");
            }

            if (Modifier.isVolatile(modifiers)) {
                System.out.println("the class is volatile");
            }
            System.out.println();
        }
    }

    private static void printMethodModifier(Method[] methods) {
        for (Method method : methods) {
            int modifiers = method.getModifiers();

            System.out.println(String.format("%s() access modifier is %s", method.getName(), getAccessModifier(modifiers)));

            if (Modifier.isStatic(modifiers)) {
                System.out.println("method modifier is static");
            }

            if (Modifier.isSynchronized(modifiers)) {
                System.out.println("method modifier is synchronized");
            }
        }
    }

    private static void printClassModifier(Class<?> clazz) {
        int modifiers = clazz.getModifiers();

        System.out.println(String.format("Class %s access modifier is %s", clazz.getSimpleName(), getAccessModifier(modifiers)));


        if (Modifier.isAbstract(modifiers)) {
            System.out.println("the class is abstract");
        }

        if (Modifier.isInterface(modifiers)) {
            System.out.println("the class is interface");
        }

        if (Modifier.isFinal(modifiers)) {
            System.out.println("the class is final");
        }
    }

    private static String getAccessModifier(int modifier) {
        if (Modifier.isPublic(modifier)) return "public";
        else if (Modifier.isPrivate(modifier)) return "private";
        else if (Modifier.isProtected(modifier)) return "protected";
        else return "package-private";
    }


    public static void runAuction() {
        Auction auction = new Auction();

        auction.startAuction();

        Bid bid1 = Bid.builder()
                .price(10)
                .bidderName("test1")
                .build();
        auction.addBid(bid1);

        Bid bid2 = Bid.builder()
                .price(15)
                .bidderName("test2")
                .build();

        auction.addBid(bid2);

        auction.stopAuction();
        System.out.println(auction.getAllBids());

        System.out.println("highest bid : " + auction.getHighestBid());

    }
}
