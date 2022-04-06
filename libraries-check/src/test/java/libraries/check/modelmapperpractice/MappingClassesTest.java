package libraries.check.modelmapperpractice;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.modelmapper.*;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.spi.MappingContext;
import org.modelmapper.spi.NamingConvention;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MappingClassesTest {

    private ModelMapper modelMapper = new ModelMapper();

    private Converter<String, Boolean> isUsable1 = new AbstractConverter<>() {
        @Override
        protected Boolean convert(String source) {
            return !StringUtils.isBlank(source);
        }
    };

    private Converter<String, Boolean> isUsable2 = context -> !StringUtils.isBlank(context.getSource());

    private Condition condition = context -> context != null;


    @Test
    void modelMapperTest1() {

        Order order = new Order(
                new Customer(new Name("popo", "an")),
                new Address("super street", "mega city")
        );

        OrderDto orderDTO = modelMapper.map(order, OrderDto.class);

//        assertEquals(order.getCustomer().getName().getFirstName(), orderDTO.getCustomerFirstName());
//        assertEquals(order.getCustomer().getName().getLastName(), orderDTO.getCustomerLastName());
//        assertEquals(order.getBillingAddress().getStreet(), orderDTO.getBillingStreet());
//        assertEquals(order.getBillingAddress().getCity(), orderDTO.getBillingCity());

        System.out.println(order);
    }

    @Test
    void modelMapperTest2() {
        WareHouse wareHouse = new WareHouse("창고 이름", 1000l, 1000.2,"경기도 어딘가");

        Container container = modelMapper.map(wareHouse, Container.class);

        System.out.println(container);
    }

    @Test
    void modelMapperTest3() {
        WareHouse wareHouse = new WareHouse("창고 이름", 1000l, 1000.2,"경기도 어딘가");

        modelMapper.typeMap(WareHouse.class, Container.class)
                .addMapping(WareHouse::getWareHouseName, Container::setContainerName);

        modelMapper.getTypeMap(WareHouse.class, Container.class)
                .addMappings(mapper -> {
                    mapper.map(WareHouse::getItemStock, Container::setProductQuantity);
                    mapper.map(WareHouse::getWareHouseArea, Container::setContainerVolume);
                });

        Container container = modelMapper.map(wareHouse, Container.class);

        System.out.println(container);
    }

    @Test
    void modelMapperTest4() {
        WareHouse wareHouse = new WareHouse("창고 이름", 1000l, 1000.2,"경기도 어딘가");

        modelMapper.typeMap(WareHouse.class, Container.class)
                .addMapping(WareHouse::getWareHouseName, Container::setContainerName);

        modelMapper.getTypeMap(WareHouse.class, Container.class)
                .addMappings(mapper -> {
                    mapper.map(WareHouse::getItemStock, Container::setProductQuantity);
                    mapper.map(WareHouse::getWareHouseArea, Container::setContainerVolume);
                    mapper.using(this.isUsable1).map(WareHouse::getLocation, Container::setUsable);
                });

        Container container = modelMapper.map(wareHouse, Container.class);

        System.out.println(container);
    }

    @Test
    void modelMapperTest5() {
        WareHouse wareHouse = new WareHouse("창고 이름", 1000l, 1000.2,"경기도 어딘가");

        modelMapper.typeMap(WareHouse.class, Container.class)
                .addMapping(WareHouse::getWareHouseName, Container::setContainerName);

        modelMapper.getTypeMap(WareHouse.class, Container.class)
                .addMappings(mapper -> {
                    mapper.map(WareHouse::getItemStock, Container::setProductQuantity);
                    mapper.map(WareHouse::getWareHouseArea, Container::setContainerVolume);
                    mapper.using(this.isUsable1).map(WareHouse::getLocation, Container::setUsable);
                    mapper.skip(Container::setLocation);
                });

        Container container = modelMapper.map(wareHouse, Container.class);
        System.out.println(container);
    }

    @Test
    void modelMapperTest6() {
        WareHouse wareHouse = new WareHouse("창고 이름", 1000l, 1000.2,"창고 위치");

        modelMapper.typeMap(WareHouse.class, Container.class)
                .addMapping(WareHouse::getWareHouseName, Container::setContainerName);

        modelMapper.getTypeMap(WareHouse.class, Container.class)
                .addMappings(mapper -> {
                    mapper.map(WareHouse::getItemStock, Container::setProductQuantity);
                    mapper.map(WareHouse::getWareHouseArea, Container::setContainerVolume);
                    mapper.using(this.isUsable1).map(WareHouse::getLocation, Container::setUsable);
                    mapper.when(condition).map(WareHouse::getLocation, Container::setLocation);
                });

        Container container = modelMapper.map(wareHouse, Container.class);
        System.out.println(container);
    }

    @Test
    void modelMapperTest7() {
        modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMethodAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setCollectionsMergeEnabled(true)
                .setFieldMatchingEnabled(true)
                .setDestinationNamingConvention(NamingConventions.JAVABEANS_ACCESSOR)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR)
                .setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);

    }

}