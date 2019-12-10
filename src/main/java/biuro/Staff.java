package biuro;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;

@Getter
@Setter
public class Staff {
    private String staffName;
    private String staffSurname;
    private ArrayList<Tour> tours;

    Staff(String staffName, String staffSurname) {
        this.staffName = staffName;
        this.staffSurname = staffSurname;
        this.tours = new ArrayList<>(Collections.emptyList());
    }

    @Override
    public String toString() {
        return "\n\tSTAFF NAME : " + this.getStaffName() + "\n\tSTAFF SURNAME : " + this.getStaffSurname() + "\n\tTOURS : " + this.getTours().toString() + "\n";
    }
}
