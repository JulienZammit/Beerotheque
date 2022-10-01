/**
 * Nom de classe : BeerController
 * Description   : Controller de la classe beer
 * Date          : 13/05/2022
 * Copyright     : NoConsulting
 */

package fr.noconsulting.restcrudapplication.controller;

import fr.noconsulting.restcrudapplication.dto.BeerDTO;
import fr.noconsulting.restcrudapplication.dto.BeerDTORequest;
import fr.noconsulting.restcrudapplication.dto.BeerDTOResponse;
import fr.noconsulting.restcrudapplication.entities.Beer;
import fr.noconsulting.restcrudapplication.exception.BeerNotFoundException;
import fr.noconsulting.restcrudapplication.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("${remote.server.url}")
public class BeerController {

    private final BeerService service;

    @Autowired
    public BeerController(BeerService service) {
        this.service = service;
    }

    @GetMapping("/beers")
    public Page<Beer> fetchBeer(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                @RequestParam(value = "nameSort", required = false) String nameSort,
                                @RequestParam(value = "typeSort", required = false) String typeSort) {
        return service.fetchBeer(pageNumber, nameSort, typeSort);
    }

    @GetMapping("/beers/{id}")
    public BeerDTO getBeerById(@PathVariable(value = "id") int id) throws BeerNotFoundException {
        return service.getBeerById(id);
    }

    @PostMapping("/beers")
    public void createBeer(@RequestBody BeerDTORequest request) {
        service.createBeer(request);
    }

    @DeleteMapping("/beers/{id}")
    public void deleteBeerById(@PathVariable(value = "id") int id) throws BeerNotFoundException {
        service.deleteBeerById(id);
    }

    @PutMapping("/beers/{id}")
    public void updateBeerById(@PathVariable(value = "id") int id, @RequestBody BeerDTOResponse request) throws BeerNotFoundException {
        service.updateBeerById(id, request);
    }
}
