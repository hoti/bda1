package bda1.entity;

import java.io.Serializable;
import java.util.Date;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author GaspardP <gaspardp@kth.se>
 */
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"nom", "prenom","dateNaissance"}))
public class Compte implements Serializable {
    private static final long serialVersionUID = 1L;
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nom;
    
    private String prenom;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateInscription;
    
    @ManyToOne(cascade = ALL)
    private Coordonnees coordonnees;
    
    /**
     * Cotisation pour l'utilisation du système d'emprunt à la
     * bibliothèque
     */
    private float somme;

    
    private Adherent adherent;
    
    public Compte() {
    }
    
    public Compte(String nom, String prenom, Date dateNaissance, Date dateInscription, Coordonnees coordonnees) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.dateInscription = dateInscription;
        this.coordonnees = coordonnees;
        this.somme = 0;
    }
    
    
    
    /****************************************************************
     * DEFAULT METHODS
     ***************************************************************/
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "bda1.entity.Compte[ id=" + id + " ]";
    }
    
    /****************************************************************
     * GETTER AND SETTER
     ***************************************************************/
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
    }

    public float getSomme() {
        return somme;
    }

    public void setSomme(float somme) {
        this.somme = somme;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
    
}
