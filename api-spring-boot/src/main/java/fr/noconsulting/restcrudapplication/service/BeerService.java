/**
 * Nom de classe : BeerService
 * Description   : Couche service de l'entité Beer / méthode createBeer, getBeerById, deleteBeerById...
 * Date          : 13/05/2022
 * Copyright     : NoConsulting
 */

package fr.noconsulting.restcrudapplication.service;

import fr.noconsulting.restcrudapplication.dto.BeerDTO;
import fr.noconsulting.restcrudapplication.dto.BeerDTORequest;
import fr.noconsulting.restcrudapplication.dto.BeerDTOResponse;
import fr.noconsulting.restcrudapplication.entities.Beer;
import fr.noconsulting.restcrudapplication.exception.BeerNotFoundException;
import fr.noconsulting.restcrudapplication.mapper.BeerMapper;
import fr.noconsulting.restcrudapplication.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import static fr.noconsulting.restcrudapplication.entities.NameSort.*;
import static fr.noconsulting.restcrudapplication.entities.TypeSort.*;

@Service
@RequiredArgsConstructor
public class BeerService {

    private final BeerRepository beerRepository;

    private final BeerMapper mapper;

    private static final int sizePage = 4; // Taille page par défaut

    /**
     * Nom de la méthode : save
     * Description : Enregistre l'entité dans la bdd
     * @param beer
     * @return void
     */
    public void save(Beer beer) {
        beerRepository.save(beer);
    }

    /**
     * Nom de la méthode : listPage
     * Description : Liste toutes les bières de la bdd ou si un paramètre est spécifié retourne la page concernée
     * @param
     * @return List<Beer>
     */
    public Page<Beer> fetchBeer(Integer pageNumber, String nameSort, String typeSort) {
        int page = pageNumber == null ? 0 : pageNumber;
        int size = pageNumber == null ? Integer.MAX_VALUE : sizePage;
        Pageable pageOfBeer = PageRequest.of(page, size);
        if(isValueName(nameSort) && isValueType(typeSort)) {
            if (isAlpha(nameSort) && isAscending(typeSort)) {
                pageOfBeer = PageRequest.of(page, size, Sort.by("name"));
            } else if (isAlpha(nameSort) && isDescending(typeSort)) {
                pageOfBeer = PageRequest.of(page, size, Sort.by("name").descending());
            } else if (isPrice(nameSort) && isAscending(typeSort)) {
                pageOfBeer = PageRequest.of(page, size, Sort.by("priceExcludingTaxCents"));
            } else if (isPrice(nameSort) && isDescending(typeSort)) {
                pageOfBeer = PageRequest.of(page, size, Sort.by("priceExcludingTaxCents").descending());
            }
        }
        return beerRepository.findAll(pageOfBeer);
    }

    /**
     * Nom de la méthode : createBeer
     * Description : Convertit la requête en une entité Beer et appelle save pour l'enregistrer dans la bdd
     * @param request
     * @return null
     */
    public void createBeer(BeerDTORequest request) {
        Beer beer = mapper.convertBeerDTORequestToBeerEntity(request);
        save(beer);
    }

    /**
     * Nom de la méthode : getBeerById
     * Description : Récupère l'entité beer de la bdd en fonction de son identifiant
     * @param id
     * @return BeerDTOResponse
     */
    public BeerDTO getBeerById(int id) throws BeerNotFoundException {
        Beer beer = beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException("Beer not found, id : " + id));
        return mapper.convertBeerEntityToBeerDTOResponse(beer);
    }

    /**
     * Nom de la méthode : deleteBeerById
     * Description : Supprime l'entité beer de la bdd en fonction de son identifiant
     * @param id
     * @return BeerDTOResponse
     */
    public void deleteBeerById(int id){
        try{
            beerRepository.deleteById(id);
        } catch(BeerNotFoundException e) {
            throw new BeerNotFoundException("Beer not found, id : " + id);
        }
    }

    /**
     * Nom de la méthode : updateBeerById
     * Description : Met à jour l'entité beer en fonction d'une requête Json + annotation @Transactional
     * @param request
     * @return BeerDTOResponse
     */
    @Transactional
    public void updateBeerById(int id, BeerDTOResponse request){
        try{
            Beer newBeer = mapper.updateBeerDTOResponseToBeerEntity(request);
            save(newBeer);
        } catch(BeerNotFoundException e) {
            throw new BeerNotFoundException("Beer not found, id : " + id);
        }
    }
}
