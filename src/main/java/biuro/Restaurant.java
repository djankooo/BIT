package biuro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant {
    private String name;
    private String cousine;
    private ContactDetails contactDetails;
    private List<String> tags;

    @Override
    public String toString() {
        return "\nNAME : " + this.getName() + "\nCOUSINE : " + this.getCousine() + "\nCONTACT DETAILS : " +
                this.getContactDetails().toString() + "\nTAGS : " + this.getTags().toString() + "\n";
    }
}
