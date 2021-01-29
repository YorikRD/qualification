package melnikov.qualification.controllers;



import melnikov.qualification.entity.Party;
import melnikov.qualification.exception.JoinedQualificationExeption;
import melnikov.qualification.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Party addmasterByID(@RequestParam int playerID, @RequestParam int partyID){
        Party updated;
        try {
            updated= service.addMasterBuyId(playerID, partyID);
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



}
