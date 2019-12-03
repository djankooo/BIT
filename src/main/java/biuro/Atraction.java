package biuro;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Atraction {
    private String name;
    private String description;
    private String type;
    private ContactDetails contactDetails;
    private Integer price;
}
