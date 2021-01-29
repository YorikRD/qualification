package melnikov.qualification.controllers;


import melnikov.qualification.entity.Interval;
import melnikov.qualification.entity.Party;
import melnikov.qualification.exception.JoinedQualificationExeption;
import melnikov.qualification.services.IntervalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/intervals")
public class IntervalController {

    @Autowired
    private IntervalService service;

    @GetMapping("/{id}")
    public Interval getByID(@PathVariable int id){
        System.out.println("the id for surch= "+id);
        Optional<Interval> optionalMaster;
        try {
            optionalMaster=service.findById(id);
        } catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return optionalMaster.get();
    }
    @PostMapping
    public Interval addPlayer(@RequestBody Interval interval){
        try {
            service.add(interval);
        } catch (QueryCreationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return interval;
    }

    @GetMapping
    public Page<Interval> getAll(@RequestParam int page, @RequestParam int size){
        Page<Interval> intervalPage = null;
        try {
            intervalPage= service.getByPage(page, size);
        }  catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return intervalPage;
    }

    @PutMapping
    public Interval updateMaster(@RequestBody Interval interval){
        Interval updated;
        try {
            updated = service.update(interval);
        } catch (JoinedQualificationExeption e) {
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

    @PutMapping("/many/addplayerandpartybyid")
    public Interval addPlayerByID(@RequestParam int intervalID, @RequestParam int playerID, @RequestParam int partyID){
        Interval updated;
        try {
            updated= service.addPlayerBuyId(intervalID, playerID,partyID);
        } catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return updated;
    }
}
