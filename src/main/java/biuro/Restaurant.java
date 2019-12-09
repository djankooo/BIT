package biuro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant {
    private String name;
    private String cousine;
    private ContactDetails contactDetails;
    private ArrayList<String> tags;

    @Override
    public String toString() {
        return "\nNAME : " + this.getName() + "\nCOUSINE : " + this.getCousine() + "\nCONTACT DETAILS : " +
                this.getContactDetails().toString() + "\nTAGS : " + this.getTags().toString() + "\n";
    }
}
