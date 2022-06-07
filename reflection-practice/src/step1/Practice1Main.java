package step1;

import java.util.Collection;
import java.util.HashMap;

public class Practice1Main {
    public static void main(String[] args) {
        System.out.println(ClassAnalyzer.createPopupTypeInfoFromClass(int.class));
        System.out.println(ClassAnalyzer.createPopupTypeInfoFromClass(Collection.class));
        System.out.println(ClassAnalyzer.createPopupTypeInfoFromClass(HashMap.class));

    }
}
