package nbacards.domain;

import nbacards.data.TeamRepository;
import nbacards.models.Team;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;

@Service
public class TeamService {
    private final TeamRepository repository;
    private final Validator validator;


    public TeamService(TeamRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public List<Team> findAll() {
        return repository.findAll();
    }
    public Team findById(int id) {
        return repository.findById(id);
    }
}
