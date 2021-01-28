package melnikov.qualification.services;

import melnikov.qualification.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyService {
    private PartyRepository repository;

    public PartyRepository getRepository() {
        return repository;
    }

    @Autowired
    public PartyService(PartyRepository repository) {
        this.repository = repository;
    }
}
