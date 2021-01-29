package melnikov.qualification.services;

import melnikov.qualification.entity.Master;
import melnikov.qualification.exception.JoinedQualificationExeption;
import melnikov.qualification.repository.MasterRepository;
import melnikov.qualification.specifications.MasterSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MasterService {
    private MasterRepository repository;

    public MasterRepository getRepository() {
        return repository;
    }

    @Autowired
    public MasterService(MasterRepository repository) {
        this.repository = repository;
    }

    public Master add(Master master){
        if (repository.existsById(master.getId())
                ||repository.count(MasterSpecifications.masterByNameAndEmail(master.getName(), master.getEmail()))>0) {
            throw new JoinedQualificationExeption("This master already exists");
        }
        return repository.save(master);
    }
    public Page<Master> getByPage(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Master> coursesPage = repository.findAll(pageable);
        if (coursesPage.getContent().isEmpty()) {
            throw new JoinedQualificationExeption("Not found");
        }
        return coursesPage;
    }
    public Master update(Master course){
        if (!repository.existsById(course.getId())) {
            throw new JoinedQualificationExeption("This Master does not exists");
        }
        return repository.save(course);
    }

    public void delete(int id){
        if (!repository.existsById(id)) throw new JoinedQualificationExeption("This Masterdoes not exists");
        repository.deleteById(id);
    }


    public Optional<Master> findById(int id){
        return repository.findById(id);
    }




}
