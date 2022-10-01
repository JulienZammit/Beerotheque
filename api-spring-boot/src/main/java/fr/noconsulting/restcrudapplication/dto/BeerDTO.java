/**
 * Nom de classe : BeerDTO
 * Description   : Classe abstraite extend BeerDTORequest et BeerDTOResponse
 * Date          : 13/05/2022
 * Copyright     : NoConsulting
 */

package fr.noconsulting.restcrudapplication.dto;

import fr.noconsulting.restcrudapplication.entities.KindOfBeer;
import lombok.Data;

@Data
public abstract class BeerDTO {
    private String name;
    private int priceExcludingTaxCents;
    private String remark;
    private int alcoholDegrees;
    private KindOfBeer kindOfBeer;
    private String owner;
}
