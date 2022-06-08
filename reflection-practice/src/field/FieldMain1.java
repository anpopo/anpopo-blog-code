package field;

import java.lang.reflect.Field;

public class FieldMain1 {
    public static void main(String[] args) throws IllegalAccessException {
//        printDeclaredFieldsInfo(Movie.class);
//        System.out.println("------------------------");
//        printDeclaredFieldsInfo(Movie.MovieStats.class);
//        System.out.println("------------------------");
//        printDeclaredFieldsInfo(Category.class);

        Movie movie = new Movie("반지의 제왕", 2001, 12.99, true, Category.ADVENTURE);
        printDeclaredFieldsInfo(Movie.class, movie);

    }


    public static <T> void printDeclaredFieldsInfo(Class<? extends T> clazz, T instance) throws IllegalAccessException {

        for (Field field : clazz.getDeclaredFields()) {
            System.out.printf("Field name : %s, type : %s\n", field.getName(), field.getType().getName());

            System.out.printf("is synthetic field : %s\n", field.isSynthetic());
            System.out.printf("field value is : %s\n", field.get(instance));
            System.out.println();
        }
    }
    public static class Movie extends Product {
        public static final double MINIMUM_PRICE = 10.99;
        private boolean isReleased;
        private Category category;
        private double actualPrice;

        public Movie(String name, int year, double price, boolean isReleased, Category category) {
            super(name, year);
            this.isReleased = isReleased;
            this.category = category;
            this.actualPrice = price;
        }

        public class MovieStats {
            private double timesWatched;

            public MovieStats(double timesWatched) {
                this.timesWatched = timesWatched;
            }

            public double getRevenue () {
                return timesWatched * actualPrice;
            }
        }

    }

    public static class Product {
        protected String name;
        protected int year;
        protected double actualPrice;

        public Product(String name, int year) {
            this.name = name;
            this.year = year;
        }
    }

    public enum Category {
        ADVENTURE,
        ACTION,
        COMEDY
    }
}
