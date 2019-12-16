package helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

    private Helper(){}

    public static boolean overlap(Date start1, Date end1, Date start2, Date end2) {
        return start1.getTime() <= end2.getTime() && start2.getTime() <= end1.getTime();
    }

    public static Date stringToDate(String stringDate) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
    }
}
