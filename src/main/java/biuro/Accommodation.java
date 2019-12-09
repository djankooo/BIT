package biuro;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class Accommodation {
    private String name;
    private String type;
    private ContactDetails contactDetails;
    private Integer price;
    private ArrayList<String> tags;

    @Override
    public String toString() {
        return "\nNAME : " + this.getName() + "\nTYPE : " + this.getType() + "\nPRICE : " + this.getPrice() + "\nCONTACT DETAILS : " +
                this.getContactDetails().toString() + "\nTAGS : " + this.getTags().toString() + "\n";
    }
}
