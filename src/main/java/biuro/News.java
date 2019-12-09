package biuro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class News {
    private String name;
    private String description;
    private LocalDate date;
    private Staff staff;
    private ArrayList<String> tags;

    @Override
    public String toString() {
        return "\nNAME : " + this.getName() + "\nDESCRIPTION : " + this.getDescription() + "\nDATE : " + this.getDate()
                + "\nSTAFF : " + this.getStaff().toString() + "\nTAGS : " + this.getTags().toString() + "\n";
    }
}