package fr.noconsulting.restcrudapplication;

import fr.noconsulting.restcrudapplication.entities.Beer;
import fr.noconsulting.restcrudapplication.entities.KindOfBeer;
import fr.noconsulting.restcrudapplication.repository.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class BeerRepositoryTest {
    @Autowired
    private BeerRepository repo;

    @Test
    public void testAddNew() {
        // Given
        Beer beer = new Beer();
        beer.setId(1);
        beer.setKindOfBeer(KindOfBeer.DARK);
        beer.setAlcoholDegrees(3);
        beer.setName("JUJU");
        beer.setPriceExcludingTaxCents(100);
        beer.setOwner("TONTON");
        beer.setRemark("Elle est ouahouuu");

        // When
        Beer savedBeer = repo.save(beer);

        // Then
        assertThat(savedBeer.getName()).isNotNull();
        assertThat(savedBeer.getPriceExcludingTaxCents()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        // Given
        Iterable<Beer> beers = repo.findAll();

        // Then
        assertThat(beers).isNotNull();
        for (Beer beer : beers) {
            System.out.println(beer);
        }
    }

    @Test
    public void testUpdate() {

        // Given
        Integer beerId = 48;
        Optional<Beer> optionalUser = repo.findById(beerId);
        Beer beer = optionalUser.get();
        beer.setRemark("hey");

        // When
        repo.save(beer);
        Beer updateUser = repo.findById(beerId).get();

        // Then
        assertThat(updateUser.getRemark()).isEqualTo("hey");
    }

    @Test
    public void testGet() {
        // Given
        Integer beerId = 48;

        // When
        Optional<Beer> optionalBeer = repo.findById(beerId);

        // Then
        assertThat(optionalBeer).isPresent();
        System.out.println(optionalBeer.get());
    }

    @Test
    public void testDelete() {
        // Given
        Integer beerId = 48;

        // When
        repo.deleteById(beerId);
        Optional<Beer> optionalBeer = repo.findById(beerId);

        // Then
        assertThat(optionalBeer).isNotPresent();
    }

}
