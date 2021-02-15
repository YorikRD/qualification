package melnikov.qualification.services;


import melnikov.qualification.auxiliary.MeetingCreator;
import melnikov.qualification.auxiliary.PartyExcelExporter;
import melnikov.qualification.auxiliary.mailnotify.AttachmentEmailSender;
import melnikov.qualification.auxiliary.mailnotify.SimpleEmailSender;
import melnikov.qualification.entity.Interval;
import melnikov.qualification.entity.Master;
import melnikov.qualification.entity.Party;
import melnikov.qualification.entity.Player;
import melnikov.qualification.exception.JoinedQualificationExeption;
import melnikov.qualification.repository.PartyRepository;
import melnikov.qualification.specifications.PartySpecification;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PartyService {
    private PartyRepository repository;
    private PlayerService playerService;
    private MasterService masterService;
    private SimpleEmailSender sender;
    private AttachmentEmailSender sender2;


    public PartyRepository getRepository() {
        return repository;
    }

    @Autowired
    public PartyService(PartyRepository repository, PlayerService playerService, MasterService masterService,SimpleEmailSender sender,AttachmentEmailSender sender2) {
        this.repository = repository;
        this.playerService = playerService;
        this.masterService = masterService;
        this.sender=sender;
        this.sender2=sender2;

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

    public List<Party> listALL(){
        List<Party> partyList = new ArrayList<>();
        Iterable<Party> iterable = repository.findAll(Sort.by("master").ascending());
        for (Party party : iterable) {
            partyList.add(party);
        }
        return partyList;

    }

    public List<Interval> getPsMtByID(int partyId){
        if (!repository.existsById(partyId)) {
            throw new JoinedQualificationExeption("This Party does not exists");
        }
        Party party = repository.findById(partyId).get();
        MeetingCreator mc= new MeetingCreator(party);
        mc.createMeetings2();
        if (mc.getCreatedMeetings().isEmpty()) throw new JoinedQualificationExeption("No possible meeting available for all players exists for this party");
        return mc.getCreatedMeetings();
    }

    public String writeExel(){
        List<Party> partyList = listALL();
        PartyExcelExporter excelExporter = new PartyExcelExporter(partyList);
        String route = excelExporter.export("All1");
        return route;
    }

    public String sendPartiesToEmail(String email){
        EmailValidator validator = EmailValidator.getInstance();
        if (!validator.isValid(email)){
            throw new JoinedQualificationExeption("This email is not valid");
        }
        String route = writeExel();
        String reply;
        try {
           reply=sender2.sendAttachmentEmail(email,route);
        } catch (MessagingException e) {
            throw  new JoinedQualificationExeption(e.getMessage());
        }
        return reply;
    }


    public void crPsMtByIDNotify(int partyId){
        List<Interval> forThisId = getPsMtByID(partyId);
        try {
            for (Interval interval : forThisId) {
                Set<Player> playerSet = interval.getAvailableOnes();
                for (Player player : playerSet) {
                    sender.sendSimpleEmailResponse(player.getEmail(),interval.toString());
                }
            }
        } catch (RuntimeException e){
            throw new JoinedQualificationExeption(e.getMessage());
        }

    }



}
