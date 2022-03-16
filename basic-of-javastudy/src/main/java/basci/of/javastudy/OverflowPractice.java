package basci.of.javastudy;

public class OverflowPractice {
    public static void main(String[] args) {

        int intMaxValue = Integer.MAX_VALUE;
        int intMinValue = Integer.MIN_VALUE;


        System.out.println("This is max value of integer : " + intMaxValue);
        System.out.println("This is max value plus one of integer : " + (intMaxValue + 1));
        System.out.println("This is min value of integer : " + intMinValue);
        System.out.println("This is min value minus one of integer : " + (intMinValue - 1));


        System.out.println("========== 오버 플로우 ===========");
        // 실수형의 오버플로우
        double doubleMaxValue = Double.MAX_VALUE;
        System.out.println("doubleMaxValue = " + doubleMaxValue);
        System.out.println("doubleMaxValue + * 2 = " + (doubleMaxValue * 2));
        System.out.println("doubleMaxValue * -2 = " + (doubleMaxValue * -2));

        System.out.println("============언더 플로우==============");
        // 실수형의 언더플로우
        for(int i = 1073; i <= 1076; i++) {
            System.out.println("2^" + i + " = " + Math.pow(2, -i));
        }

        System.out.println("=============실수형 정밀도==============");
        // 실수형의 정밀도 (정말도란 정밀도 만큼의 10진수의 자릿수를 오차없이 저장할 수 있다는 뜻)
        System.out.println(0.12345678901234567890d);
        System.out.println(0.12345678901234567890f);



    }
}
