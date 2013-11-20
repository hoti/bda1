
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
import javax.persistence.Temporal;

/**
 *
 * @author GaspardP <gaspardp@kth.se>
 */
@Entity
public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String titre;
    private boolean peutEtreReemprunter;
    private int nombreDeDemandes;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePublication;
    
    @ManyToMany
    @JoinTable(name="produits_auteurs",
            joinColumns=@JoinColumn(name="produit_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="auteur_id", referencedColumnName="id"))
    private List<Auteur> auteurs;
    
    @ManyToMany
    @JoinTable(name="produits_genres",
            joinColumns=@JoinColumn(name="produit_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="genre_id", referencedColumnName="id"))
    private List<Genre> genres;
    
    
    private ProduitType type;
    
    
    //PENDING A-t'on besoin de l'objet catalogueMediatheque qui sera juste un
    // singleton avec la liste de tous les produits. On doit pouvoir la
    // récupérer directement depuis la BDD
    //private UUID catalogueMediatheque
    /*=============================================================*
     * GETTER AND SETTER
     *=============================================================*/
    
     public Produit() {
    }

    
     public Produit(String titre, boolean peutEtreReemprunter, int nombreDeDemandes, Date datePublication, List<Auteur> auteurs, List<Genre> genres, ProduitType type) {
        this.titre = titre;
        this.peutEtreReemprunter = peutEtreReemprunter;
        this.nombreDeDemandes = nombreDeDemandes;
        this.datePublication = datePublication;
        this.auteurs = auteurs;
        this.genres = genres;
        this.type = type;
    }
 
     
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public ProduitType getType() {
        return type;
    }

    public void setType(ProduitType type) {
        this.type = type;
    }
    
    public boolean isPeutEtreReemprunter() {
        return peutEtreReemprunter;
    }

    public void setPeutEtreReemprunter(boolean peutEtreReemprunter) {
        this.peutEtreReemprunter = peutEtreReemprunter;
    }

    public int getNombreDeDemandes() {
        return nombreDeDemandes;
    }

    public void setNombreDeDemandes(int nombreDeDemandes) {
        this.nombreDeDemandes = nombreDeDemandes;
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
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "bda1.entity.Produit[ id=" + id + " ]";
    }

}
