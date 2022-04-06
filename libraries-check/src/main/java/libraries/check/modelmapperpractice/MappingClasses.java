package libraries.check.modelmapperpractice;

import lombok.*;

public class MappingClasses {
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Order {
    private Customer customer;
    private Address billingAddress;
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Customer {
    private Name name;
}
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Name {
    private String firstName;
    private String lastName;
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Address {
    private String street;
    private String city;
}




