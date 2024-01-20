package functions.hunter;

import com.hunt.hunter.model.Hunter;
import com.hunt.worker.common.functions.Search;

import java.util.List;


public class SearchHunter implements Search<Hunter> {
    @Override
    public List<Object> apply(String searchQuery) {
        return  List.of(1,2,3);
    }
}
