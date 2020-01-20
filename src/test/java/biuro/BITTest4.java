package biuro;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BITTest4 {

    private BIT bit;
    private String STAFF_NAME = "testName";
    private String STAFF_SURNAME = "testSurname";

    @Before
    public void setUp() throws Exception {
        bit = new BIT();
    }

    @Test
    public void shouldCorrectlyRegisterUser() {

        //when
        bit.register(STAFF_NAME, STAFF_SURNAME);

        //then
        assertEquals("testName", bit.getStaff().get(0).getStaffName());
    }
}