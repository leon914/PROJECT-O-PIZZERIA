import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by lhi06 on 26/06/2017.
 */
class CustomerTest {
    Customer customer;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        customer = new Customer("John", 32);
    }

    @org.junit.jupiter.api.Test
    void testSetAge() {
        customer.setAge(12);
        assertEquals(12, customer.getAge());
    }

    @org.junit.jupiter.api.Test
    void testSetName() {
        customer.setName("John");
        assertEquals("John", customer.getName());
    }

    @org.junit.jupiter.api.Test
    void getName() {

    }

    @org.junit.jupiter.api.Test
    void getAge() {

    }

    @org.junit.jupiter.api.Test
    void getOrder() {

    }

    @org.junit.jupiter.api.Test
    void setOrder() {

    }

}