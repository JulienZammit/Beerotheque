/**
 * Nom de classe : BeerDTOResponse
 * Description   : Classe fille de BeerDTO --> Class représentant les request en Json venant de l'utilisateur
 * Date          : 16/05/2022
 * Copyright     : NoConsulting
 */
package fr.noconsulting.restcrudapplication.dto;

import lombok.Data;

@Data
public class BeerDTOResponse extends BeerDTO {
    private int id;
}
