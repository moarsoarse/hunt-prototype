package functions.hunter;

import com.hunt.worker.common.functions.Create;
import com.hunt.worker.hunter.entity.Hunter;

import java.util.Optional;

public class CreateHunter implements Create<Hunter> {
    @Override
    public Optional<Hunter> apply(Hunter hunter) {
        hunter.setName("муча");
        System.out.println("IM HERE!!!");
        return Optional.of(hunter);
        //return Optional.of(repository.save(hunter));return Optional.empty();
    }


}
