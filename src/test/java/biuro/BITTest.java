package biuro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class BITTest {

    private static final String STAFF_NAME = "testName";
    private static final String STAFF_SURNAME = "testSurname";
    private BIT bit;

    @Before
    public void setUp() {
        bit = new BIT();
    }


    @Test
    public void shouldCorrectlyRegisterUser() {

        //when
        bit.register(STAFF_NAME, STAFF_SURNAME);

        //then
        Assert.assertEquals("testName", bit.getStaff().get(0).getStaffName());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowRegistrationException() {

        //when, then
        bit.register(STAFF_NAME, STAFF_SURNAME);

        bit.register(STAFF_NAME, STAFF_SURNAME);
    }
}