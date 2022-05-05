package basic.of.javastudy.absinter;

public class AttractionFactory {
    public static Attraction of(String attractionName) {
        if ("RollerCoaster".equalsIgnoreCase(attractionName)){
            return new RollerCoaster();
        } else if ("Viking".equalsIgnoreCase(attractionName)) {
            return new Viking();
        }
        return null;
    }
}
