package biuro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

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
    void shouldReturnExceptionBecauseThatUserDoesNotExist() {

        assertThrows(NoSuchElementException.class, () -> {
            bit.login("nonExistingName", "nonExistingSurname");
        });
    }

    @Test
    void shouldCorrectlyBookTour() {

        //when
        String START_DATE_STRING = "10/11/2019";
        String END_DATE_STRING = "10/11/2019";

        ContactDetails contactDetails = new ContactDetails("testAddress", "testTelephone");
        Attraction attractionToBook = new Attraction("testAttraction", "testDesc", "testType", 1, contactDetails, Collections.emptyList());

        bit.register(STAFF_NAME, STAFF_SURNAME);
        bit.createAttraction("testAttraction", "testDesc", "testType", "1", "testAddress", "1", Collections.emptyList());


        assertDoesNotThrow(() -> bit.bookTour(STAFF_NAME, STAFF_SURNAME, START_DATE_STRING, END_DATE_STRING, attractionToBook));
    }

    @Test
    void shouldFailBookTourBecauseNonMatchingGuide() throws ParseException {

        //when
        String START_DATE_STRING = "10/11/2019";
        String END_DATE_STRING = "10/11/2019";

        ContactDetails contactDetails = new ContactDetails("testAddress", "testTelephone");
        Attraction attractionToBook = new Attraction("testAttraction", "testDesc", "testType", 1, contactDetails, Collections.emptyList());

        bit.createAttraction("testAttraction", "testDesc", "testType", "1", "testAddress", "1", Collections.emptyList());

        //then
        assertThrows(IllegalArgumentException.class, () -> bit.bookTour(STAFF_NAME, STAFF_SURNAME, START_DATE_STRING, END_DATE_STRING, attractionToBook));
    }

    @Test
    void shouldFailBookTourBecauseOverlappingDate() throws ParseException {

        //when
        String START_DATE_STRING = "10/11/2019";
        String END_DATE_STRING = "10/11/2019";

        ContactDetails contactDetails = new ContactDetails("testAddress", "testTelephone");
        Attraction attractionToBook = new Attraction("testAttraction", "testDesc", "testType", 1, contactDetails, Collections.emptyList());

        bit.register(STAFF_NAME, STAFF_SURNAME);
        bit.createAttraction("testAttraction", "testDesc", "testType", "1", "testAddress", "1", Collections.emptyList());
        bit.bookTour(STAFF_NAME, STAFF_SURNAME, START_DATE_STRING, END_DATE_STRING, attractionToBook);

        //then
        assertThrows(IllegalArgumentException.class, () -> bit.bookTour(STAFF_NAME, STAFF_SURNAME, START_DATE_STRING, END_DATE_STRING, attractionToBook));
    }

    @Test
    void shouldFindAttractionByName() {

        //given
        ContactDetails contactDetails = new ContactDetails("testAddress", "testTelephone");
        Attraction attractionToBook = new Attraction("testAttraction", "testDesc", "testType", 1, contactDetails, Collections.emptyList());

        //when
        bit.addAttraction(attractionToBook);

        //then
        assertEquals(bit.findAttractionByName("testAttraction"), attractionToBook);
    }

    @Test
    void shouldNotFindAttractionByName() {

        //given
        ContactDetails contactDetails = new ContactDetails("testAddress", "testTelephone");
        Attraction attractionToBook = new Attraction("testAttraction", "testDesc", "testType", 1, contactDetails, Collections.emptyList());

        //when


        //then
        assertThrows(IllegalArgumentException.class, () -> bit.findAttractionByName("testAttraction"));
    }
}