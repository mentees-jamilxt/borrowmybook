package mentees.jamilxt.borrowmybook.model.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationArgs {

    private Long pageNo;

    private Long pageSize;

    private String sortBy;

    private AscOrDesc ascOrDesc;
}
