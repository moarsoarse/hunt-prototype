package functions.ticket;

import com.hunt.worker.common.functions.Create;
import com.hunt.worker.ticket.model.Ticket;

import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;

public class CreateTicket implements Create<Ticket> {
    @Override
    public Optional<UUID> apply(TreeMap<String,Object> hunter) {
        System.out.println("IM HERE!!!");
        return Optional.of(UUID.randomUUID());
        //return Optional.of(repository.save(hunter));return Optional.empty();
    }


}
