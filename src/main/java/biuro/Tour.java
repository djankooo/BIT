package biuro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Tour {
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    @Override
    public String toString() {
        return "\nSTART DATE : " + this.getStartDate() + "\nEND DATER : " + this.getEndDate() + "\nDESCRIPTION : " + this.getDescription() + "\n";
    }
}
