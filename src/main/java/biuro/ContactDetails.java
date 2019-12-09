package biuro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class ContactDetails {
    private String address;
    private String telephoneNumber;
    private ArrayList<String> tags;

    @Override
    public String toString() {
        return "\n\tADDRESS : " + this.getAddress() + "\n\tTELEPHONE : " + this.getTelephoneNumber() +
                "\n\tCONTACT DETAILS TAGS : " + this.getTags().toString();
    }
}
