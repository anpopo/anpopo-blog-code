package libraries.check.modelmapperpractice;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WareHouse {
    private String wareHouseName;
    private long itemStock;
    private double wareHouseArea;
    private String location;
}

