package biuro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BITTest5 {


    private BIT bit;
    private String STAFF_NAME = "testName";
    private String STAFF_SURNAME = "testSurname";

    @BeforeEach
    void setUp() {
        bit = new BIT();
    }

    @Test
    void shouldCorrectlyRegisterUser() {

        //when
        bit.register(STAFF_NAME, STAFF_SURNAME);

        //then
        assertEquals("testName", bit.getStaff().get(0).getStaffName());
    }

    @Test
    void shouldThrowRegistrationExceptionBecauseThatUSerExist() {

        //when
        bit.register(STAFF_NAME, STAFF_SURNAME);

        //then
        assertThrows(NoSuchElementException.class, () -> {
            bit.register(STAFF_NAME, STAFF_SURNAME);
        });
    }

    @Test
    void shouldReturnExceptionBecauseThatUSerDoesntExist() {

        assertThrows(NoSuchElementException.class, () -> {
            bit.login("asd", "asdasd");
        });
    }
}