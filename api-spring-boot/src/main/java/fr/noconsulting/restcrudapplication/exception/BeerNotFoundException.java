/**
 * Nom de classe : BeerNotFoundException
 * Description   : Classe gérant les erreures relatives à la recherche de beer dans la bdd
 * Date          : 13/05/2022
 * Copyright     : NoConsulting
 */

package fr.noconsulting.restcrudapplication.exception;

public class BeerNotFoundException extends RuntimeException {
    /**
     * @Constructor
     * @param message
     */
    public BeerNotFoundException(String message) {
            super(message);
        }
}
