package biuro;

import java.time.LocalDate;
import java.util.*;

class BIT {

    private boolean notLoggedIn = true;
    private Staff loggedStaff;
    private ArrayList<Accommodation> accommodations = new ArrayList<>();
    private ArrayList<Attraction> attractions = new ArrayList<>();
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<News> news = new ArrayList<>();
    private ArrayList<Staff> staff = new ArrayList<>();

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
        String address = getInput("address");
        String telephone = getInput("telephone");

        ArrayList<String> tagsRestaurant = createTags("restaurant");
        Restaurant restaurant = new Restaurant(name, typeOfCousine, createContactDetails(address, telephone), tagsRestaurant);
        addRestaurant(restaurant);
    }

    private String getInput(String input) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter " + input + " -> ");
        return in.nextLine();
    }

    private void createNews() {

        String name = getInput("name");
        String content = getInput("content");

        ArrayList<String> tagsNews = createTags("news");

        News news = new News(name, content, LocalDate.now(), loggedStaff, tagsNews);
        addNews(news);
    }

    private void createAttraction() {

        String name = getInput("name");
        String description = getInput("description");
        String type = getInput("type");
        String price = getInput("price");
        String address = getInput("address");
        String telephone = getInput("telephone");

        ArrayList<String> tagsAttraction = createTags("attraction");
        Attraction attraction = new Attraction(name, description, type, Integer.parseInt(price), createContactDetails(address, telephone), tagsAttraction);
        addAttraction(attraction);
    }

    private void createAccommodation() {

        String name = getInput("name");
        String type = getInput("type");
        String price = getInput("price");
        String address = getInput("address");
        String telephone = getInput("telephone");

        ContactDetails contactDetails = createContactDetails(address, telephone);
        ArrayList<String> tagsAccommodation = createTags("accommodation");
        Accommodation accommodation = new Accommodation(name, type, contactDetails, Integer.parseInt(price), tagsAccommodation);
        addAccommodation(accommodation);
    }

    private Staff createStaff() {

        String staffName = getInput("staffName");
        String staffSurname = getInput("staffSurname");

        return new Staff(staffName, staffSurname);
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
        this.staff.add(staff);
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
        return staff;
    }

    private void login() {
        String staffName = getInput("staffName");
        String staffSurname = getInput("staffSurname");
        loggedStaff = findStaffByNameAndSurname(staffName, staffSurname);
    }

    private Staff findStaffByNameAndSurname(String name, String surname) {
        Optional<Staff> loginMatched = staff.stream().filter(staff -> name.equals(staff.getStaffName())).findFirst();
        if (loginMatched.get().getStaffSurname().equals(surname)) {
            notLoggedIn = false;
        }
        return loginMatched.get();
    }

    private void register() {
        Staff staff = createStaff();
        addStaff(staff);
        notLoggedIn = true;
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
        for (News news : news) {
            tags.addAll(news.getTags());
        }
        return tags;
    }

    private void printTags() {
        Set<String> allTags = getAllTags();
        int i = 0;
        System.out.println("__All tags__");
        for (String s : allTags) {
            System.out.println(i++ + ". " + s);
        }
        System.out.println("____________");

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

        for (News news : news) {
            if (news.getTags().contains(tag)) {
                newsTag.add(news);
            }
        }

        System.out.println(restaurantsTag.toString());
        System.out.println(accommodationsTag.toString());
        System.out.println(attractionsTag.toString());
        System.out.println(newsTag.toString());
    }

    void service() {

        while (notLoggedIn) {
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
            }
        }

        while (!notLoggedIn) {
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
                    "\n   12. Get objects by tag");
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
                    createStaff();
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
            }
        }
    }
}
