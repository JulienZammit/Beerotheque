/**
 * Nom de classe : Beer
 * Description   : Classe de l'entité Beer
 * Date          : 13/05/2022
 * Copyright     : NoConsulting
 */

package fr.noconsulting.restcrudapplication.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "beer") // Référence la table
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Beer {
    @Id // Référence la clé primaire de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génération automatique par la bdd
    private int id;

    @Column(nullable = false, unique = true, length = 45)
    private String name;

    @Column()
    private int priceExcludingTaxCents;

    @Column(nullable = false, length = 400)
    private String remark;

    @Column()
    private int alcoholDegrees;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KindOfBeer kindOfBeer;

    @Column(nullable = false, length = 45)
    private String owner;
}
