package helpers;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

public class HelperTest {

    @Test
    public void shouldReturnThatDatesAreOverlapping() throws ParseException {

        String startDate = "10/11/2019";
        String endDate = "12/11/2019";

        String startDate2 = "11/11/2019";
        String endDate2 = "13/11/2019";

        Assert.assertTrue(Helper.overlap(Helper.stringToDate(startDate), Helper.stringToDate(endDate), Helper.stringToDate(startDate2), Helper.stringToDate(endDate2)));
    }

    @Test
    public void shouldReturnThatDatesAreOverlapping2() throws ParseException {

        String startDate = "11/11/2019";
        String endDate = "13/11/2019";

        String startDate2 = "10/11/2019";
        String endDate2 = "12/11/2019";

        Assert.assertTrue(Helper.overlap(Helper.stringToDate(startDate), Helper.stringToDate(endDate), Helper.stringToDate(startDate2), Helper.stringToDate(endDate2)));
    }

    @Test
    public void shouldReturnThatDatesAreNotOverlapping() throws ParseException {

        String startDate = "10/11/2019";
        String endDate = "11/11/2019";

        String startDate2 = "12/11/2019";
        String endDate2 = "13/11/2019";

        Assert.assertFalse(Helper.overlap(Helper.stringToDate(startDate), Helper.stringToDate(endDate), Helper.stringToDate(startDate2), Helper.stringToDate(endDate2)));
    }

    @Test
    public void shouldReturnThatDatesAreNotOverlapping2() throws ParseException {

        String startDate = "12/11/2019";
        String endDate = "13/11/2019";

        String startDate2 = "10/11/2019";
        String endDate2 = "11/11/2019";

        Assert.assertFalse(Helper.overlap(Helper.stringToDate(startDate), Helper.stringToDate(endDate), Helper.stringToDate(startDate2), Helper.stringToDate(endDate2)));
    }


}