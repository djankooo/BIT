package biuro;

import helpers.Helper;

import java.text.ParseException;
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
    private static final String COUSINE = "type of cousine";
    private static final String RESTAURANT = "restaurnat";
    private static final String ATTRACTION = "attraction";
    private static final Logger LOG = Logger.getLogger(BIT.class.getName());
    private Staff loggedUser = null;
    private List<Accommodation> accommodations = new ArrayList<>();
    private List<Attraction> attractions = new ArrayList<>();
    private List<Restaurant> restaurants = new ArrayList<>();
    private List<News> news = new ArrayList<>();
    private List<Staff> staffList = new ArrayList<>();

    public static List<String> createTags(String type) {
        List<String> tags = new ArrayList<>();
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

    public static ContactDetails createContactDetails(String address, String telephone) {
        return new ContactDetails(address, telephone);
    }

    public void createRestaurant(String name, String telephone, String typeOfCousine, String address) {
        List<String> tagsRestaurant = createTags(RESTAURANT);
        Restaurant restaurant = new Restaurant(name, typeOfCousine, createContactDetails(address, telephone), tagsRestaurant);

        addRestaurant(restaurant);
    }

    public String getInput(String input) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter " + input + " -> ");
        return in.nextLine();
    }

    public void createNews() {

        String name = getInput(NAME);
        String content = getInput(CONTENT);

        List<String> tagsNews = createTags("news");

        addNews(new News(name, content, LocalDate.now(), loggedUser, tagsNews));
    }

    public void createAttraction() {

        String name = getInput(NAME);
        String description = getInput(DESCRIPTION);
        String type = getInput(TYPE);
        String price = getInput(PRICE);
        String address = getInput(ADDRESS);
        String telephone = getInput(TELEPHONE);

        List<String> tagsAttraction = createTags("attraction");
        Attraction attraction = new Attraction(name, description, type, Integer.parseInt(price), createContactDetails(address, telephone), tagsAttraction);
        addAttraction(attraction);
    }

    public void createAccommodation() {

        String name = getInput(NAME);
        String type = getInput(TYPE);
        String price = getInput(PRICE);
        String address = getInput(ADDRESS);
        String telephone = getInput(TELEPHONE);

        ContactDetails contactDetails = createContactDetails(address, telephone);
        List<String> tagsAccommodation = createTags("accommodation");
        Accommodation accommodation = new Accommodation(name, type, contactDetails, Integer.parseInt(price), tagsAccommodation);
        addAccommodation(accommodation);
    }

    public void createStaff(String staffName, String staffSurname) {
        Staff staff = new Staff(staffName, staffSurname);
        addStaff(staff);
    }

    public void addAccommodation(Accommodation accommodation) {
        this.accommodations.add(accommodation);
    }

    public void addAttraction(Attraction attraction) {
        this.attractions.add(attraction);
    }

    public void addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
    }

    public void addNews(News news) {
        this.news.add(news);
    }

    public void addStaff(Staff staff) {
        staffList.add(staff);
    }

    public List<Accommodation> getAccommodations() {
        return accommodations;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<News> getNews() {
        return news;
    }

    public List<Staff> getStaff() {
        return staffList;
    }

    public Optional<Staff> findStaffByNameAndSurname(String staffName, String staffSurname) {
        return staffList.stream()
                .filter(staff -> staff.getStaffName().equals(staffName) && staff.getStaffSurname().equals(staffSurname))
                .findFirst();
    }

    public void login(String staffName, String staffSurname) {
        Optional<Staff> staffByNameAndSurname = findStaffByNameAndSurname(staffName, staffSurname);

        staffByNameAndSurname.ifPresent(staff -> loggedUser = staff);

        if (!staffByNameAndSurname.isPresent()) {
            throw new NoSuchElementException("No user found with name : " + staffName + " and surname : " + staffSurname);
        }
    }

    public void register(String staffName, String staffSurname) {
        Optional<Staff> staffByNameAndSurname = findStaffByNameAndSurname(staffName, staffSurname);

        staffByNameAndSurname.ifPresent(staff -> {
            throw new NoSuchElementException("There is user with name : " + staffName + " and surname : " + staffSurname);
        });

        addStaff(new Staff(staffName, staffSurname));
    }

    public Set<String> getAllTags() {
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

    public void printTags() {
        Set<String> allTags = getAllTags();
        int i = 0;
        LOG.log(Level.INFO, "____TAGS____");
        for (String s : allTags) {
            System.out.println(i++ + ". " + s);
        }
        LOG.log(Level.INFO, "_END OF TAGS_");

    }

    public void collectServicesByTags() {
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

    public void bookTour(String staffName, String staffSurname, String startDateString, String endDateString, Attraction attraction) throws ParseException {

        Date startDate = Helper.stringToDate(startDateString);
        Date endDate = Helper.stringToDate(endDateString);

        Optional<Staff> guide = findStaffByNameAndSurname(staffName, staffSurname);

        if (guide.isPresent()) {
            if (guide.get().getTours().stream().noneMatch(tour -> Helper.overlap(startDate, endDate, tour.getStartDate(), tour.getEndDate()))) {
                guide.get().getTours().add(new Tour(startDate, endDate, attraction));

            }
        } else {
            throw new IllegalArgumentException("Date is overlapping with other tour");
        }
    }

    private void logout() {
        loggedUser = null;
    }

    void service() throws ParseException {
        while (true) {
            while (Objects.isNull(loggedUser)) {
                Scanner in = new Scanner(System.in);
                displayContent("0:  Exit" +
                        "\n1.  Login" +
                        "\n2.  Register" +
                        "\n3.  Show Restaurants" +
                        "\n4.  Show Accommodations" +
                        "\n5.  Show Attractions" +
                        "\n6.  Show News" +
                        "\n7.  Show Staff" +
                        "\n8.  Show Tags" +
                        "\n9.  Get objects by tag" +
                        "\n10. Book Tour");
                displayContent("Input -> ");
                switch (in.nextLine()) {
                    case "0":
                        return;
                    case "1":
                        String staffNameLogin = getInput(STAFF_NAME);
                        String staffSurnameLogin = getInput(STAFF_SURNAME);
                        try {
                            login(staffNameLogin, staffSurnameLogin);
                        } catch (NoSuchElementException e) {
                            displayContent(e.getMessage());
                        }
                        break;
                    case "2":
                        String staffNameRegister = getInput(STAFF_NAME);
                        String staffSurnameRegister = getInput(STAFF_SURNAME);
                        try {
                            register(staffNameRegister, staffSurnameRegister);
                        } catch (NoSuchElementException e) {
                            displayContent(e.getMessage());
                        }
                        break;
                    case "3":
                        displayContent(getRestaurants().toString());
                        break;
                    case "4":
                        displayContent(getAccommodations().toString());
                        break;
                    case "5":
                        displayContent(getAttractions().toString());
                        break;
                    case "6":
                        displayContent(getNews().toString());
                        break;
                    case "7":
                        displayContent(getStaff().toString());
                        break;
                    case "8":
                        displayContent(getAllTags().toString());
                        break;
                    case "9":
                        collectServicesByTags();
                        break;
                    case "10":
                        processBookingTour();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + in.nextLine());
                }
            }
            while (loggedUser != null) {
                Scanner in = new Scanner(System.in);
                displayContent("    1. Create Restaurant" +
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
                        "\n   13. Create Tour" +
                        "\n    0. Logout");
                displayContent("Input -> ");
                String input = in.nextLine();
                switch (input) {
                    case "0":
                        logout();
                        break;
                    case "1":
                        String name = getInput(NAME);
                        String typeOfCousine = getInput(COUSINE);
                        String address = getInput(ADDRESS);
                        String telephone = getInput(TELEPHONE);
                        createRestaurant(name, telephone, typeOfCousine, address);
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
                        processBookingTour();
                        break;
                }
            }
        }
    }

    private void displayContent(String content) {
        System.out.println(content + "\n");
    }

    private void processBookingTour() throws ParseException {
        System.out.println("Pick one of listed attractions");
        System.out.println(getAttractions().toString());

        String attractionName = getInput(ATTRACTION);

        String guideName = getInput(STAFF_NAME);
        String guideSurname = getInput(STAFF_SURNAME);
        String startDate = getInput("startDate (dd/MM/yyyy)");
        String endDate = getInput("endDate (dd/MM/yyyy)");

        try {
            Attraction attraction = findAttractionByName(attractionName);
            bookTour(guideName, guideSurname, startDate, endDate, attraction);
        } catch (IllegalArgumentException e) {
            displayContent(e.getMessage());
        }
    }

    private Attraction findAttractionByName(String name) {
        for (Attraction attraction : attractions) {
            if (attraction.getName().equals(name))
                return attraction;
        }
        throw new IllegalArgumentException("There is no attraction with given name");
    }

}
