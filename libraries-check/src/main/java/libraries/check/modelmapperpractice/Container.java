package libraries.check.modelmapperpractice;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Container {
    private String containerName;
    private long productQuantity;
    private double containerVolume;
    private String location;
    private boolean isUsable;
}
