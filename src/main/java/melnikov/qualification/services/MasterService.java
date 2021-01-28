package melnikov.qualification.services;

import melnikov.qualification.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterService {
    private MasterRepository repository;

    public MasterRepository getRepository() {
        return repository;
    }

    @Autowired
    public MasterService(MasterRepository repository) {
        this.repository = repository;
    }
}
