package basic.of.javastudy.variabletype;

import java.util.Arrays;

public class ReferenceTypePractice {
    public static void main(String[] args) {
        int[] intArray = new int[10];

        // 주소값은 해당 인스턴스를 해시화 한후 16진수로 표현한 값입니다.
        System.out.println(intArray);
        System.out.println(Integer.toHexString(System.identityHashCode(intArray)));

        System.out.println("==== 다른 변수에 같은 주소값 할당 ====");
        // 다른 변수에 같은 주소값을 할당한다.
        int[] intArray2 = intArray;
        int[] intArray3 = intArray;

        System.out.println(intArray2);
        System.out.println(intArray3);

        System.out.println("=========== 하나의 데이터를 가리킨다. ===============");
        System.out.println(Arrays.toString(intArray));
        intArray3[0] = 100;
        System.out.println(Arrays.toString(intArray3));
    }
}