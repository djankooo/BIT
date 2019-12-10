package biuro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

public class BITTest {

    private BIT bit;

    @Before
    public void setUp() {
        bit = new BIT();
        bit.createStaff("testName", "testSurname");
    }

    // TODO : to finish
    @Test
    public void shouldReturnException() throws ParseException {
        String staffName = "testName";
        String staffSurname = "testSurname";
        String startDate = "10/11/2019";
        String endDate = "15/11/2019";
        String desc = "tour description";

        bit.bookTour(staffName, staffSurname, startDate, endDate, desc);
    }

    @Test
    public void shouldReturnThatDatesAreOverlapping() throws ParseException {
        String startDate = "10/11/2019";
        String endDate = "15/11/2019";
        String startDate2 = "13/11/2019";
        String endDate2 = "16/11/2019";

        Assert.assertTrue(bit.overlap(bit.stringToDate(startDate), bit.stringToDate(endDate), bit.stringToDate(startDate2), bit.stringToDate(endDate2)));
    }

    @Test
    public void shouldReturnThatDatesAreNotOverlapping() throws ParseException {

        String startDate = "10/11/2019";
        String endDate = "15/11/2019";
        String startDate2 = "16/11/2019";
        String endDate2 = "17/11/2019";

        Assert.assertFalse(bit.overlap(bit.stringToDate(startDate), bit.stringToDate(endDate), bit.stringToDate(startDate2), bit.stringToDate(endDate2)));
    }
}