
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
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.NamedQuery;

/**
 *
 * @author GaspardP <gaspardp@kth.se>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findCommandeByFournisseur", query = "SELECT o FROM Commande o "
    + "WHERE o.fournisseur = :fournisseur "),
    @NamedQuery(name = "findCommande", query = "SELECT o FROM Commande o "
    + "WHERE o.id = :id ")
})

public class Commande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="fournisseur_id", referencedColumnName="id")
    private Fournisseur fournisseur;
    

    
    @OneToMany(mappedBy="commande")
    private List<ElementDeCommande> elements;
    
    
    /*=============================================================*
     * GETTER AND SETTER
     *=============================================================*/
    
     public Commande() {
    }

    public Commande(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
     
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    
    
    public List<ElementDeCommande> getElements() {
        return elements;
    }

    public void setElements(List<ElementDeCommande> elements) {
        this.elements = elements;
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
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "bda1.entity.Commande[ id=" + id + " ]";
    }

}
