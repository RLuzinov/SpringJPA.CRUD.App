package project.learn.rluzinov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.learn.rluzinov.models.People;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {

}
