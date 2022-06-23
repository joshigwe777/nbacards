package nbacards.controllers;

import nbacards.domain.NbaCardService;
import nbacards.domain.Result;
import nbacards.domain.ResultType;
import nbacards.models.NbaCard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nba-card")
public class NbaCardController {

    private final NbaCardService service;

    public NbaCardController(NbaCardService service) {
        this.service = service;
    }
    @GetMapping()
    public List<NbaCard> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NbaCard> findById(@PathVariable int id) {
        NbaCard card = service.findById(id);
        if(card!= null) {
            return new ResponseEntity<>(card, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody @Valid NbaCard card, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }

        Result<NbaCard> result = service.add(card);
        if(result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody @Valid NbaCard card,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ErrorResponse.build(bindingResult);
        }
        if(id != card.getCardId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<NbaCard> result = service.update(card);
        if(result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(result.getResultType() == ResultType.INVALID) {
            return ErrorResponse.build(result);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        Result<Void> result = service.deleteById(id);
        if(result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
