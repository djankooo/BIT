package biuro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Tour {
    private Date startDate;
    private Date endDate;
    private Attraction attraction;

    @Override
    public String toString() {
        return "\n\t\tSTART DATE : " + this.getStartDate() + "\n\t\tEND DATER : " + this.getEndDate() + "\n\t\tATTRACTION : " + this.getAttraction().toStringBooking() + "\n";
    }
}