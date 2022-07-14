package nbacards.controllers;

import nbacards.domain.TeamService;
import nbacards.models.Team;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }
    @GetMapping
    public List<Team> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Team findById(@PathVariable int id) {return service.findById(id);}
}
