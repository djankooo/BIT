package biuro;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant {
    private String name;
    private String cousine;
    private ContactDetails contactDetails;
}
