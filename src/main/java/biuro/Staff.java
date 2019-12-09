package biuro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Staff {
    private String staffName;
    private String staffSurname;

    @Override
    public String toString() {
        return "\n\tSTAFF NAME : " + this.getStaffName() + "\n\tSTAFF SURNAME : " + this.getStaffSurname() + "\n";
    }
}
