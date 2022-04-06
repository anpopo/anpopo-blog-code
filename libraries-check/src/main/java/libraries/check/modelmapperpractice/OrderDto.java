package libraries.check.modelmapperpractice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private String customerFirstName;
    private String customerLastName;
    private String billingStreet;
    private String billingCity;
}
