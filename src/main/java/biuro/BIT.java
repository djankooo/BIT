package biuro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class BIT {

    private static final String STAFF_NAME = "staffName";
    private static final String STAFF_SURNAME = "staffSurname";
    private static final String ADDRESS = "addr1ess";
    private static final String TELEPHONE = "telephone";
    private static final String TYPE = "type";
    private static final String DESCRIPTION = "description";
    private static final String CONTENT = "content";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final Logger LOG = Logger.getLogger(BIT.class.getName());
    private Staff loggedUser = null;
    private ArrayList<Accommodation> accommodations = new ArrayList<>();
    private ArrayList<Attraction> attractions = new ArrayList<>();
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<News> news = new ArrayList<>();
    private ArrayList<Staff> staffList = new ArrayList<>();

    private static ArrayList<String> createTags(String type) {
        ArrayList<String> tags = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Podaj pierwszy tag [" + type + "]");
        String input = in.nextLine();
        while (!input.isEmpty()) {
            tags.add(input);
            System.out.println("Podaj kolejny tag [" + type + "], wcisnij ENTER, zeby opuscic");
            input = in.nextLine();
        }
        return tags;
    }

    private static ContactDetails createContactDetails(String address, String telephone) {
        ArrayList<String> tagContactDetails = createTags("contact details");
        return new ContactDetails(address, telephone, tagContactDetails);
    }

    private void createRestaurant() {

        String name = getInput("name");
        String typeOfCousine = getInput("type of cousine");
        String address = getInput(ADDRESS);
        String telephone = getInput(TELEPHONE);

        ArrayList<String> tagsRestaurant = createTags("restaurant");
        ContactDetails contactDetails = createContactDetails(address, telephone);
        Restaurant restaurant = new Restaurant(name, typeOfCousine, contactDetails, tagsRestaurant);
        addRestaurant(restaurant);
    }

    private String getInput(String input) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter " + input + " -> ");
        return in.nextLine();
    }

    private void createNews() {

        String name = getInput(NAME);
        String content = getInput(CONTENT);

        ArrayList<String> tagsNews = createTags("news");

        addNews(new News(name, content, LocalDate.now(), loggedUser, tagsNews));
    }

    private void createAttraction() {

        String name = getInput(NAME);
        String description = getInput(DESCRIPTION);
        String type = getInput(TYPE);
        String price = getInput(PRICE);
        String address = getInput(ADDRESS);
        String telephone = getInput(TELEPHONE);

        ArrayList<String> tagsAttraction = createTags("attraction");
        Attraction attraction = new Attraction(name, description, type, Integer.parseInt(price), createContactDetails(address, telephone), tagsAttraction);
        addAttraction(attraction);
    }

    private void createAccommodation() {

        String name = getInput(NAME);
        String type = getInput(TYPE);
        String price = getInput(PRICE);
        String address = getInput(ADDRESS);
        String telephone = getInput(TELEPHONE);

        ContactDetails contactDetails = createContactDetails(address, telephone);
        ArrayList<String> tagsAccommodation = createTags("accommodation");
        Accommodation accommodation = new Accommodation(name, type, contactDetails, Integer.parseInt(price), tagsAccommodation);
        addAccommodation(accommodation);
    }

    public void createStaff(String staffName, String staffSurname) {
        Staff staff = new Staff(staffName, staffSurname);
        addStaff(staff);
    }

    private void addAccommodation(Accommodation accommodation) {
        this.accommodations.add(accommodation);
    }

    private void addAttraction(Attraction attraction) {
        this.attractions.add(attraction);
    }

    private void addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
    }

    private void addNews(News news) {
        this.news.add(news);
    }

    private void addStaff(Staff staff) {
        staffList.add(staff);
    }

    private ArrayList<Accommodation> getAccommodations() {
        return accommodations;
    }

    private ArrayList<Attraction> getAttractions() {
        return attractions;
    }

    private ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    private ArrayList<News> getNews() {
        return news;
    }

    private ArrayList<Staff> getStaff() {
        return staffList;
    }

    private Optional<Staff> findStaffByNameAndSurname(String staffName, String staffSurname) {
        return staffList.stream()
                .filter(staff -> staff.getStaffName().equals(staffName) && staff.getStaffSurname().equals(staffSurname))
                .findFirst();
    }

    private void login() {
        String staffName = getInput(STAFF_NAME);
        String staffSurname = getInput(STAFF_SURNAME);

        Optional<Staff> staffByNameAndSurname = findStaffByNameAndSurname(staffName, staffSurname);

        staffByNameAndSurname.ifPresent(staff -> loggedUser = staff);

        if (!staffByNameAndSurname.isPresent()) {
            throw new NoSuchElementException("No user found with name : " + staffName + " and surname : " + staffSurname);
        }
    }

    private void register() {
        String staffName = getInput(STAFF_NAME);
        String staffSurname = getInput(STAFF_SURNAME);

        Optional<Staff> staffByNameAndSurname = findStaffByNameAndSurname(staffName, staffSurname);

        staffByNameAndSurname.ifPresent(staff -> {
            throw new NoSuchElementException("There is user with name : " + staffName + " and surname : " + staffSurname);
        });

        addStaff(new Staff(staffName, staffSurname));
    }

    private Set<String> getAllTags() {
        Set<String> tags = new HashSet<>();
        for (Restaurant restaurant : restaurants) {
            tags.addAll(restaurant.getTags());
        }
        for (Attraction attraction : attractions) {
            tags.addAll(attraction.getTags());
        }
        for (Accommodation accommodation : accommodations) {
            tags.addAll(accommodation.getTags());
        }
        for (News info : news) {
            tags.addAll(info.getTags());
        }
        return tags;
    }

    private void printTags() {
        Set<String> allTags = getAllTags();
        int i = 0;
        LOG.log(Level.INFO, "____TAGS____");
        for (String s : allTags) {
            System.out.println(i++ + ". " + s);
        }
        LOG.log(Level.INFO, "_END OF TAGS_");

    }

    private void collectServicesByTags() {
        String tag = getInput("tag");

        ArrayList<Restaurant> restaurantsTag = new ArrayList<>();
        ArrayList<Accommodation> accommodationsTag = new ArrayList<>();
        ArrayList<Attraction> attractionsTag = new ArrayList<>();
        ArrayList<News> newsTag = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            if (restaurant.getTags().contains(tag)) {
                restaurantsTag.add(restaurant);
            }
        }

        for (Accommodation accommodation : accommodations) {
            if (accommodation.getTags().contains(tag)) {
                accommodationsTag.add(accommodation);
            }
        }

        for (Attraction attraction : attractions) {
            if (attraction.getTags().contains(tag)) {
                attractionsTag.add(attraction);
            }
        }

        for (News info : news) {
            if (info.getTags().contains(tag)) {
                newsTag.add(info);
            }
        }

        System.out.println(restaurantsTag.toString());
        System.out.println(accommodationsTag.toString());
        System.out.println(attractionsTag.toString());
        System.out.println(newsTag.toString());
    }

    public void bookTour(String staffName, String staffSurname, String startDateString, String endDateString, String desc) throws ParseException {

        Date startDate = stringToDate(startDateString);
        Date endDate = stringToDate(endDateString);

        Optional<Staff> guide = findStaffByNameAndSurname(staffName, staffSurname);

        if (guide.isPresent()) {
            if (guide.get().getTours().stream().noneMatch(tour -> overlap(startDate, endDate, tour.getStartDate(), tour.getEndDate()))) {
                guide.get().getTours().add(new Tour(startDate, endDate, desc));
            }
        } else {
            throw new IllegalArgumentException("Date is overlapping with other tour");
        }
    }

    public boolean overlap(Date start1, Date end1, Date start2, Date end2) {
        return start1.getTime() <= end2.getTime() && start2.getTime() <= end1.getTime();
    }

    public Date stringToDate(String stringDate) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
    }

    void service() throws ParseException {

        while (loggedUser == null) {
            Scanner in = new Scanner(System.in);
            System.out.println("    1. Login \n" +
                    "    2. Register \n" +
                    "    0. Exit");
            System.out.print("Input -> ");
            String input = in.nextLine();
            switch (input) {
                case "1":
                    login();
                    break;
                case "2":
                    register();
                    break;
                default:
                    return;
            }

        }

        while (loggedUser != null) {
            Scanner in = new Scanner(System.in);
            System.out.println("    1. Create Restaurant" +
                    "\n    2. Create Accommodation" +
                    "\n    3. Create Attraction" +
                    "\n    4. Create News" +
                    "\n    5. Create Staff" +
                    "\n    6. Get Restaurants" +
                    "\n    7. Get Accommodation " +
                    "\n    8. Get Attraction" +
                    "\n    9. Get News" +
                    "\n   10. Get Staff" +
                    "\n   11. Get Tags" +
                    "\n   12. Get objects by tag" +
                    "\n   13. Create Tour");
            System.out.print("Input -> ");
            String input = in.nextLine();
            switch (input) {
                case "1":
                    createRestaurant();
                    break;
                case "2":
                    createAccommodation();
                    break;
                case "3":
                    createAttraction();
                    break;
                case "4":
                    createNews();
                    break;
                case "5":
                    String staffName = getInput(STAFF_NAME);
                    String staffSurname = getInput(STAFF_SURNAME);
                    createStaff(staffName, staffSurname);
                    break;
                case "6":
                    System.out.println(getRestaurants().toString());
                    break;
                case "7":
                    System.out.println(getAccommodations().toString());
                    break;
                case "8":
                    System.out.println(getAttractions().toString());
                    break;
                case "9":
                    System.out.println(getNews().toString());
                    break;
                case "10":
                    System.out.println(getStaff().toString());
                    break;
                case "11":
                    printTags();
                    break;
                case "12":
                    collectServicesByTags();
                    break;
                case "13":
                    String guideName = getInput(STAFF_NAME);
                    String guideSurname = getInput(STAFF_SURNAME);
                    String startDate = getInput("startDate (dd/MM/yyyy)");
                    String endDate = getInput("staffSurname (dd/MM/yyyy)");
                    String desc = getInput("tour description");

                    bookTour(guideName, guideSurname, startDate, endDate, desc);
                    break;
                default:
                    continue;
            }
        }
    }
}
