package melnikov.qualification.controllers;

import melnikov.qualification.entity.Player;
import melnikov.qualification.exception.JoinedQualificationExeption;
import melnikov.qualification.services.PlayerService;
import melnikov.qualification.specifications.PlayerSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerContoller {
    @Autowired
    private PlayerService service;

    @GetMapping("/{id}")
    public Player getByID(@PathVariable int id){
        System.out.println("the id for surch= "+id);
        Optional<Player> optionalPlayer;
        try {
            optionalPlayer=service.findById(id);
        } catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return optionalPlayer.get();
    }
    @PostMapping
    public Player addPlayer(@RequestBody Player player){
        try {
            service.add(player);
        } catch (QueryCreationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return player;
    }

    @GetMapping
    public Page<Player> getAll(@RequestParam int page,@RequestParam int size){
        Page<Player> playerPage = null;
        try {
            playerPage= service.getByPage(page, size);
        }  catch (JoinedQualificationExeption e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return playerPage;
    }

    @PutMapping
    public Player updatePlayer(@RequestBody Player player){
        Player updated;
        try {
            updated = service.update(player);
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
