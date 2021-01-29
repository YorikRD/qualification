package melnikov.qualification.services;

import melnikov.qualification.entity.Player;
import melnikov.qualification.exception.JoinedQualificationExeption;
import melnikov.qualification.repository.PlayerRepository;
import melnikov.qualification.specifications.PlayerSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        if (repository.existsById(player.getId())
                ||repository.count(PlayerSpecifications.playerByNameAndEmail(player.getName(), player.getEmail()))>0) {
            throw new JoinedQualificationExeption("This player already exists");
        }
            return repository.save(player);
    }
    public Page<Player> getByPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Player> coursesPage = repository.findAll(pageable);
        if (coursesPage.getContent().isEmpty()) {
            throw new JoinedQualificationExeption("Not found");
        }
        return coursesPage;
    }
    public Player update(Player course){
        if (!repository.existsById(course.getId())) {
            throw new JoinedQualificationExeption("This player does not exists");
        }
        return repository.save(course);
    }

    public void delete(int id){
        if (!repository.existsById(id)) throw new JoinedQualificationExeption("This player does not exists");
        repository.deleteById(id);
    }


    public Optional<Player> findById(int id){
        return repository.findById(id);
    }





}
