package melnikov.qualification.services;

import melnikov.qualification.entity.Player;
import melnikov.qualification.exception.JoinedQualificationExeption;
import melnikov.qualification.repository.PlayerRepository;
import melnikov.qualification.specifications.PlayerSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    private PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public PlayerRepository getRepository() {
        return repository;
    }

    public Player add(Player player){
//        (repository.count(PlayerSpecifications.playerEmail(player.getEmail()))>0
//                &&repository.count(PlayerSpecifications.playerByName(player.getName()))>0)
        if (repository.existsById(player.getId())
                ||repository.count(PlayerSpecifications.playerByNameAndEmail(player.getName(), player.getEmail()))>0) {
            throw new JoinedQualificationExeption("This player already exists");
        }
            return repository.save(player);
    }

    public Optional<Player> findById(int id){
        return repository.findById(id);
    }




}
