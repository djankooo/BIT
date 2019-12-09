package biuro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class Attraction {
    private String name;
    private String description;
    private String type;
    private Integer price;
    private ContactDetails contactDetails;
    private ArrayList<String> tags;

    @Override
    public String toString() {
        return "\nNAME : " + this.getName() + "\nDESCRIPTION : " + this.getDescription() + "\nTYPE : " + this.getType() + "\nPRICE : " + this.getPrice() + "\nCONTACT DETAILS : " +
                this.getContactDetails().toString() + "\nTAGS : " + this.getTags().toString() + "\n";
    }
}
