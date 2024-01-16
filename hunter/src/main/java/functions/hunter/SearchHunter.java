package functions.hunter;

import com.hunt.worker.hunter.entity.Hunter;
import com.hunt.worker.common.functions.Search;
import java.util.ArrayList;
import java.util.List;


public class SearchHunter implements Search<Hunter> {
    @Override
    public List<Hunter> apply(String searchQuery) {
        return new ArrayList<>();
    }
}
