package mentees.jamilxt.borrowmybook.constant;

import mentees.jamilxt.borrowmybook.model.pagination.AscOrDesc;
import mentees.jamilxt.borrowmybook.model.pagination.PaginationArgs;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class AppUtils {

    private AppUtils() {}

    public Pageable getPageable(PaginationArgs paginationArgs) {
        int pageNo = paginationArgs.getPageNo();
        int pageSize = paginationArgs.getPageSize();
        String sortBy = paginationArgs.getSortBy();
        AscOrDesc ascOrDesc = paginationArgs.getAscOrDesc();

        if (sortBy != null && !sortBy.isBlank()) {
            Sort sort;
            if (ascOrDesc.equals(AscOrDesc.ASC)) {
                sort = Sort.by(sortBy).ascending();
            }
            else {
                sort = Sort.by(sortBy).descending();
            }

            return PageRequest.of(pageNo, pageSize, sort);
        }
        return PageRequest.of(pageNo, pageSize);
    }
}
