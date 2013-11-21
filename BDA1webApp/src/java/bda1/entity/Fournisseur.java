
package bda1.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author GaspardP <gaspardp@kth.se>
 */
@Entity
@NamedQuery(name = "findFournisseur", query = "SELECT o FROM Fournisseur o "
+ "WHERE o.id = :id ")
public class Fournisseur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    private String adresse;
    
    @OneToMany(mappedBy="fournisseur")
    private List<Commande> commandes;

    public Fournisseur() {
    }

    public Fournisseur(String name, String adresse) {
        this.name = name;
        this.adresse = adresse;
    }
    
    
    
    /*=============================================================*
     * GETTER AND SETTER
     *=============================================================*/
    
    /**
     * @return  Identifiant de l'instance adhérent
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
   
    
    
    /*=============================================================*
     * DEFAULT METHODS
     *=============================================================*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fournisseur)) {
            return false;
        }
        Fournisseur other = (Fournisseur) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "bda1.entity.Fournisseur[ id=" + id + " ]";
    }
    
}
