/**
 * Nom de classe : BeerMapper
 * Description   : Modifiction de la requête DTO reçu en un objet et inversement grâce à ModelMapper
 * Date          : 13/05/2022
 * Copyright     : NoConsulting
 */

package fr.noconsulting.restcrudapplication.mapper;

import fr.noconsulting.restcrudapplication.dto.BeerDTORequest;
import fr.noconsulting.restcrudapplication.dto.BeerDTOResponse;
import fr.noconsulting.restcrudapplication.entities.Beer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeerMapper{

    private final ModelMapper modelMapper;

    public BeerDTOResponse convertBeerEntityToBeerDTOResponse(Beer beer){
        return modelMapper.map(beer, BeerDTOResponse.class);
    }

    public Beer convertBeerDTORequestToBeerEntity(BeerDTORequest request){
        return modelMapper.map(request, Beer.class);
    }

    public Beer updateBeerDTOResponseToBeerEntity(BeerDTOResponse request){
        return modelMapper.map(request, Beer.class);
    }
}
