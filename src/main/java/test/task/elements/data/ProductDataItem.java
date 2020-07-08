package test.task.elements.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDataItem {
    private String title;
    private String date;
    private String price;

    public String getYear() {
        return date.split("/")[1];
    }
}
