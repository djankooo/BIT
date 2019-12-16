package biuro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContactDetails {
    private String address;
    private String telephoneNumber;

    @Override
    public String toString() {
        return "\n\tADDRESS : " + this.getAddress() + "\n\tTELEPHONE : " + this.getTelephoneNumber();
    }

    public String toStringBooking() {
        return "\n\t\t\t\tADDRESS : " + this.getAddress() + "\n\t\t\t\tTELEPHONE : " + this.getTelephoneNumber();
    }
}
