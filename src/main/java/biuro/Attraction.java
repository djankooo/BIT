package biuro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Attraction {
    private String name;
    private String description;
    private String type;
    private Integer price;
    private ContactDetails contactDetails;
    private List<String> tags;

    @Override
    public String toString() {
        return "\nNAME : " + this.getName() + "\nDESCRIPTION : " + this.getDescription() + "\nTYPE : " + this.getType() + "\nPRICE : " + this.getPrice() + "\nCONTACT DETAILS : " +
                this.getContactDetails().toString() + "\nTAGS : " + this.getTags().toString() + "\n";
    }

    public String toStringBooking() {
        return "\n\t\t\tNAME : " + this.getName() + "\n\t\t\tDESCRIPTION : " + this.getDescription() + "\n\t\t\tTYPE : " + this.getType() + "\n\t\t\tPRICE : "
                + this.getPrice() + "\n\t\t\tCONTACT DETAILS : " + this.getContactDetails().toStringBooking() + "\n\t\t\tTAGS : " + this.getTags().toString() + "\n";
    }
}
