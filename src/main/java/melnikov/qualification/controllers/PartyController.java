package melnikov.qualification.controllers;



import melnikov.qualification.entity.Interval;
import melnikov.qualification.entity.Party;
import melnikov.qualification.exception.JoinedQualificationExeption;
import melnikov.qualification.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parties")
public class PartyController {
    @Autowired
    private PartyService service;

    @GetMapping("/{id}")
    public Party getByID(@PathVariable int id){
        System.out.println("the id for surch= "+id);
        Optional<Party> optionalParty;
        try {
            optionalParty=service.findById(id);
        } catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return optionalParty.get();
    }
    @PostMapping
    public Party addPlayer(@RequestBody Party party){
        try {
            service.add(party);
        } catch (QueryCreationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return party;
    }

    @GetMapping
    public Page<Party> getAll(@RequestParam int page, @RequestParam int size){
        Page<Party> partyPage = null;
        try {
            partyPage= service.getByPage(page, size);
        }  catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return partyPage;
    }

    @PutMapping
    public Party updateParty(@RequestBody Party party){
        Party updated;
        try {
            updated = service.update(party);
        } catch (JoinedQualificationExeption e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return updated;
    }
    @PutMapping("/many/addplayerbyid")
    public Party addPlayerByID(@RequestParam int playerID, @RequestParam int partyID){
       Party updated;
       try {
           updated= service.addPlayerBuyId(playerID, partyID);
       } catch (JoinedQualificationExeption e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
       }
       return updated;
    }
    @PutMapping("/many/addmasterbyid")
    public Party addmasterByID(@RequestParam int masterID, @RequestParam int partyID){
        Party updated;
        try {
            updated= service.addMasterBuyId(masterID, partyID);
        } catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return updated;
    }

    @DeleteMapping
    public void deleteByID(@RequestParam int id){
        try {
            service.delete(id);
        } catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
    @GetMapping("/fpmeeting")
    public List<Interval> getPossibleMeetings(@RequestParam int partyID){
        List<Interval> possibleMeetings;
        try {
            possibleMeetings=service.getPsMtByID(partyID);
        } catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return possibleMeetings;
    }
    @GetMapping("/fpmeetingnotify")
    public void generateAndSetToPartisipants(@RequestParam int partyID){
        try {
            service.crPsMtByIDNotify(partyID);
        }catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/allaslist")
    public List<Party> getAllasList(){
        List<Party> parties;
        try {
           parties=service.listALL();
        } catch (JoinedQualificationExeption e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return parties;
    }
    @GetMapping("/writeAllxls")
    public String response(){
        String response;
        try{
            service.writeExel();
        } catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        response = "Success";
        return response;
    }
    @GetMapping("/getemailtable")
    public  String getAndSendTable(@RequestParam String email){
        String reply;
        try {
            reply=service.sendPartiesToEmail(email);
        } catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
        }
        return reply;
    }



}
