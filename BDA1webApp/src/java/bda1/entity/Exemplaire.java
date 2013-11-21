
package bda1.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 *
 * @author GaspardP <gaspardp@kth.se>
 */
@Entity
@NamedQuery(name = "findExemplaire", query = "SELECT o FROM Exemplaire o "
+ "WHERE o.id = :id ")
public class Exemplaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Version int version;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Statut statut;
    private int NombreEmprunt;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebutEmprunt;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFinEmprunt;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DateArriveeStock;
    
    @ManyToOne
    @JoinColumn(name="produit_id", referencedColumnName="id")
    private Produit produit;

    //TODO Passer en bidirectionnel ?
    @ManyToOne
    @JoinColumn(name="panier_id", referencedColumnName="id")
    private Panier panier;
    
    @OneToOne
    @JoinColumn(name="emprunteur_id", referencedColumnName="id")
    private Adherent emprunteurActuel;
    
    @OneToOne
    @JoinColumn(name="emprunteurReserve_id", referencedColumnName="id")
    private Adherent reservePourCetAdherent;
    
    
    /*=============================================================*
     * GETTER AND SETTER
     *=============================================================*/
    public Exemplaire() {
    }

    public Exemplaire(Statut statut, Date DateArriveeStock, Produit produit) {
        this.statut = statut;
        this.DateArriveeStock = DateArriveeStock;
        this.produit = produit;
    }
  
    public boolean supposePerduEmprunt()
    {
        Calendar c = Calendar.getInstance(); 
        c.setTime(new Date()); 
        c.add(Calendar.DATE, -30);

        if(dateFinEmprunt!=null)
            return dateFinEmprunt.before(c.getTime());
        else
            return false;
    }
    
    public boolean retardEmprunt()
    {
        if(dateFinEmprunt!=null)
            return dateFinEmprunt.before(new Date());
        else
            return false;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public int getNombreEmprunt() {
        return NombreEmprunt;
    }

    public void setNombreEmprunt(int NombreEmprunt) {
        this.NombreEmprunt = NombreEmprunt;
    }

    public Date getDateDebutEmprunt() {
        return dateDebutEmprunt;
    }

    public void setDateDebutEmprunt(Date dateDebutEmprunt) {
        this.dateDebutEmprunt = dateDebutEmprunt;
    }

    public Date getDateFinEmprunt() {
        return dateFinEmprunt;
    }

    public void setDateFinEmprunt(Date dateFinEmprunt) {
        this.dateFinEmprunt = dateFinEmprunt;
    }

    

    public Date getDateArriveeStock() {
        return DateArriveeStock;
    }

    public void setDateArriveeStock(Date DateArriveeStock) {
        this.DateArriveeStock = DateArriveeStock;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Adherent getEmprunteurActuel() {
        return emprunteurActuel;
    }

    public void setEmprunteurActuel(Adherent emprunteurActuel) {
        this.emprunteurActuel = emprunteurActuel;
    }

    public Adherent getReservePourCetAdherent() {
        return reservePourCetAdherent;
    }

    public void setReservePourCetAdherent(Adherent reservePourCetAdherent) {
        this.reservePourCetAdherent = reservePourCetAdherent;
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
        if (!(object instanceof Exemplaire)) {
            return false;
        }
        Exemplaire other = (Exemplaire) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "bda1.entity.Exemplaire[ id=" + id + " ]";
    }
    
}
