package jdbi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//CHECKSTYLE:OFF
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Player
{
    private String name;
    private int wins;
}
