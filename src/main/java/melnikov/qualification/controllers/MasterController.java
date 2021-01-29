package melnikov.qualification.controllers;

import melnikov.qualification.entity.Master;
import melnikov.qualification.exception.JoinedQualificationExeption;
import melnikov.qualification.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/masters")
public class MasterController {
    @Autowired
    private MasterService service;

    @GetMapping("/{id}")
    public Master getByID(@PathVariable int id){
        System.out.println("the id for surch= "+id);
        Optional<Master> optionalMaster;
        try {
            optionalMaster=service.findById(id);
        } catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return optionalMaster.get();
    }
    @PostMapping
    public Master addPlayer(@RequestBody Master master){
        try {
            service.add(master);
        } catch (QueryCreationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return master;
    }

    @GetMapping
    public Page<Master> getAll(@RequestParam int page, @RequestParam int size){
        Page<Master> masterPage = null;
        try {
            masterPage= service.getByPage(page, size);
        }  catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return masterPage;
    }

    @PutMapping
    public Master updateMaster(@RequestBody Master course){
        Master updated;
        try {
            updated = service.update(course);
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

}
