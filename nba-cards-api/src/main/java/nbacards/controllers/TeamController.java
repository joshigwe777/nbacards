package nbacards.controllers;

import nbacards.domain.TeamService;
import nbacards.models.Team;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Team> findById(@PathVariable int id) {
        Team team = service.findById(id);
        if(team!=null) {
            return new ResponseEntity<>(team, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
