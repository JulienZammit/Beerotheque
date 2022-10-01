/**
 * Nom de classe : BeerRepository
 * Description   : Utilisation de l'interface JpaRepository
 * Date          : 13/05/2022
 * Copyright     : NoConsulting
 */

package fr.noconsulting.restcrudapplication.repository;

import fr.noconsulting.restcrudapplication.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer> {
}
