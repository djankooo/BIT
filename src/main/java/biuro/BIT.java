package biuro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

class BIT {

    boolean notLoggedIn = true;
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

    private String getInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private void createNews() {

        String name = getInput("name");
        String content = getInput("content");

        ArrayList<String> tagsNews = createTags("news");
        Staff person = null;

        News news = new News(name, content, LocalDate.now(), person, tagsNews);
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
        System.out.print("staffSurname");
        String staffSurname = getInput();

        Optional<Staff> loginMatched = staff.stream().filter(staff -> staffName.equals(staff.getStaffName())).findFirst();
        notLoggedIn = !loginMatched.get().getStaffSurname().equals(staffSurname);
    }

    private void register() {
        Staff staff = createStaff();
        addStaff(staff);
        notLoggedIn = true;
    }

    void service() {

        while (notLoggedIn) {
            Scanner in = new Scanner(System.in);
            System.out.println("    1. Login \n" +
                    "    2. Register \n" +
                    "Other. Exit");
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
                    "\n   10. Get Staff");
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
            }
        }
    }
}
