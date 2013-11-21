
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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author GaspardP <gaspardp@kth.se>
 */
@Entity
@NamedQuery(name = "findAdherent", query = "SELECT o FROM Adherent o "
+ "WHERE o.id = :id ")
public class Adherent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne(cascade = ALL)
    @JoinColumn(name="carteMagnetique_id", referencedColumnName="id")
    private CarteMagnetique carteMagnetique;
    
    @OneToOne(cascade = ALL)
    @JoinColumn(name="compte_id", referencedColumnName="id")
    private Compte compte;
    
    @OneToOne(cascade = ALL)
    @JoinColumn(name="panier_id", referencedColumnName="id")
    private Panier panier;
    
   
    @ManyToMany(mappedBy = "adherentsDemandeurs")
    private List<Produit> produitsReserves;
    
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
    
    public CarteMagnetique getCarteMagnetique() {
        return carteMagnetique;
    }

    public void setCarteMagnetique(CarteMagnetique carteMagnetique) {
        this.carteMagnetique = carteMagnetique;
    }
     
    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Panier getPanier() {
        return panier;
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
        if (!(object instanceof Adherent)) {
            return false;
        }
        Adherent other = (Adherent) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "bda1.entity.Adherent[ id=" + id + " ]";
    }
    
}
