/*

package functions.hunter;

import com.hunt.hunter.dto.HunterMapper;
import com.hunt.hunter.model.Hunter;
import com.hunt.hunter.repository.HunterRepository;
import com.hunt.worker.common.functions.Create;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;


public class CreateHunter implements Create<Hunter> {


    @Override
    @Transactional
    public Optional<UUID> apply(TreeMap<String,Object> hunterMap) {
        Hunter hunter = hunterMapper.dtoToEntity(hunterMap);
        UUID newHunterId = hunterRepository.save(hunter).getId();
        return Optional.of(newHunterId);
        //return Optional.of(repository.save(hunter));return Optional.empty();
    }


}

*/
