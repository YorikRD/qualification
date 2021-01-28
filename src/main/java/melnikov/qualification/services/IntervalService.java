package melnikov.qualification.services;

import melnikov.qualification.repository.IntervalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntervalService {
private IntervalRepository repository;
    @Autowired
    public IntervalService(IntervalRepository repository) {
        this.repository = repository;
    }

    public IntervalRepository getRepository() {
        return repository;
    }


}
