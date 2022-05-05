package basic.of.javastudy.absinter;

import java.util.List;

public class InterMain {
    public static void main(String[] args) {
        String rollerCoaster = "RollerCoaster";
        String viking = "Viking";
        List<String> attractionName = List.of(rollerCoaster, viking);

        for (int i = 0; i < attractionName.size(); i++) {
            Attraction attraction = AttractionFactory.of(attractionName.get(i));
            if (attraction != null ) {
                attraction.startAttraction();
            }
            System.out.println("=============================================");
        }

        for (int i = 0; i < attractionName.size(); i++) {
            Attraction attraction = AttractionFactory.of(attractionName.get(i));
            if (attraction != null ) {
                attraction.emergencyStop();
                Attraction.fix();
            }
            System.out.println("=============================================");
        }
    }
}
