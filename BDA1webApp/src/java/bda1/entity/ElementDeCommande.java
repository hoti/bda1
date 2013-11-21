
package bda1.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.NamedQuery;

/**
 *
 * @author GaspardP <gaspardp@kth.se>
 */
@Entity
@NamedQuery(name = "findElementDeCommande", query = "SELECT o FROM ElementDeCommande o "
+ "WHERE o.id = :id ")
public class ElementDeCommande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nomReference;
    
    private int nbExemplaires;
    
    @ManyToOne
    @JoinColumn(name="commande_id", referencedColumnName="id")
    private Commande commande;
    
    /*=============================================================*
     * GETTER AND SETTER
     *=============================================================*/
    
     public ElementDeCommande() {
    }

    public ElementDeCommande(String nomReference, int nbExemplaires,Commande commande) {
        this.nomReference = nomReference;
        this.nbExemplaires = nbExemplaires;
        this.commande=commande;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNbExemplaires() {
        return nbExemplaires;
    }

    public void setNbExemplaires(int nbExemplaires) {
        this.nbExemplaires = nbExemplaires;
    }

    public String getNomReference() {
        return nomReference;
    }

    public void setNomReference(String nomReference) {
        this.nomReference = nomReference;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
     
    

 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElementDeCommande)) {
            return false;
        }
        ElementDeCommande other = (ElementDeCommande) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "bda1.entity.ElementDeCommande[ id=" + id + " ]";
    }

}
