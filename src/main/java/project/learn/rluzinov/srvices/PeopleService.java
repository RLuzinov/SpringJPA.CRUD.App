package project.learn.rluzinov.srvices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.learn.rluzinov.models.People;
import project.learn.rluzinov.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public List<People> findAll(){
        return peopleRepository.findAll();
    }
    public People findById(int id){
        Optional<People> people = peopleRepository.findById(id);
        return people.orElse(null);
    }

    @Transactional
    public void save(People people){
        peopleRepository.save(people);
    }

    @Transactional
    public void update(People updatePeople, int id){
        updatePeople.setId(id);
        peopleRepository.save(updatePeople);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

}
