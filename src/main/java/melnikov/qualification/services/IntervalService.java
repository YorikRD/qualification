package melnikov.qualification.services;


import melnikov.qualification.entity.Interval;
import melnikov.qualification.entity.Party;
import melnikov.qualification.exception.JoinedQualificationExeption;
import melnikov.qualification.repository.IntervalRepository;
import melnikov.qualification.specifications.MasterSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IntervalService {
    private IntervalRepository repository;
    private PartyService partyService;
    private PlayerService playerService;

    @Autowired
    public IntervalService(IntervalRepository repository, PartyService partyService, PlayerService playerService) {
        this.repository = repository;
        this.partyService = partyService;
        this.playerService = playerService;
    }

    public IntervalRepository getRepository() {
        return repository;
    }

    public Interval add(Interval interval) {
        if (repository.existsById(interval.getId())) {
            throw new JoinedQualificationExeption("This interval already exists");
        }
        return repository.save(interval);
    }

    public Page<Interval> getByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Interval> intervalPage = repository.findAll(pageable);
        if (intervalPage.getContent().isEmpty()) {
            throw new JoinedQualificationExeption("Not found");
        }
        return intervalPage;
    }

    public Interval update(Interval interval) {
        if (!repository.existsById(interval.getId())) {
            throw new JoinedQualificationExeption("This Interval does not exists");
        }
        return repository.save(interval);
    }

    public Interval addPartyBuyId(int intervalID, int partyID) {
        if (!repository.existsById(intervalID)) {
            throw new JoinedQualificationExeption("This Interval does not exists");
        }
        Optional<Party> partyOptional = partyService.findById(partyID);
        Party party = partyOptional.get();
        Interval updated = repository.findById(intervalID).get();
        updated.setGame(party);
        return repository.save(updated);
    }

    public void delete(int id) {
        if (!repository.existsById(id)) throw new JoinedQualificationExeption("This Interval does not exists");
        repository.deleteById(id);
    }


    public Optional<Interval> findById(int id) {
        return repository.findById(id);
    }


}
