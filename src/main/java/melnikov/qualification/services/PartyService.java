package melnikov.qualification.services;


import melnikov.qualification.entity.Master;
import melnikov.qualification.entity.Party;
import melnikov.qualification.entity.Player;
import melnikov.qualification.exception.JoinedQualificationExeption;
import melnikov.qualification.repository.PartyRepository;
import melnikov.qualification.specifications.PartySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartyService {
    private PartyRepository repository;
    private PlayerService playerService;
    private MasterService masterService;


    public PartyRepository getRepository() {
        return repository;
    }

    @Autowired
    public PartyService(PartyRepository repository, PlayerService playerService, MasterService masterService) {
        this.repository = repository;
        this.playerService = playerService;
        this.masterService = masterService;

    }

    public Party add(Party party) {
        if (repository.existsById(party.getId())
                || repository.count(PartySpecification.partyByTitle(party.getTitle())) > 0) {
            throw new JoinedQualificationExeption("This party already exists");
        }
        return repository.save(party);
    }

    public Page<Party> getByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Party> coursesPage = repository.findAll(pageable);
        if (coursesPage.getContent().isEmpty()) {
            throw new JoinedQualificationExeption("Not found");
        }
        return coursesPage;
    }

    public Party update(Party party) {
        if (!repository.existsById(party.getId())) {
            throw new JoinedQualificationExeption("This Party does not exists");
        }
        return repository.save(party);
    }

    public Party addPlayerBuyId(int playerID, int partyId) {
        if (!repository.existsById(partyId)) {
            throw new JoinedQualificationExeption("This Party does not exists");
        }
        Optional<Player> optionalPlayer;
        optionalPlayer = playerService.findById(playerID);
        Party party = repository.findById(partyId).get();
        Player player = optionalPlayer.get();
        player.getGames().add(party);
//        party.getPlayers().add(player);
        repository.save(party);
        return party;
    }
    public Party addMasterBuyId(int playerID, int partyId) {
        if (!repository.existsById(partyId)) {
            throw new JoinedQualificationExeption("This Party does not exists");
        }
        Optional<Master> optionalMaster;
        optionalMaster = masterService.findById(playerID);
        Party party = repository.findById(partyId).get();
        Master master = optionalMaster.get();
        party.setMaster(master);
        repository.save(party);
        return party;
    }


    public void delete(int id) {
        if (!repository.existsById(id)) throw new JoinedQualificationExeption("This Party does not exists");
        repository.deleteById(id);
    }


    public Optional<Party> findById(int id) {
        return repository.findById(id);
    }


}
