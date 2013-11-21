
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
@NamedQuery(name = "findProduit", query = "SELECT o FROM Produit o "
+ "WHERE o.id = :id ")
public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String titre;
    private boolean peutEtreReemprunter;
    
    @ManyToMany
    @JoinTable(name="produits_adherents",
            joinColumns=@JoinColumn(name="produit_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="adherent_id", referencedColumnName="id"))
    private List<Adherent> adherentsDemandeurs;
    
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
    
    
    @OneToMany(mappedBy="produit")
    private List<Exemplaire> exemplaires;
    
    //PENDING A-t'on besoin de l'objet catalogueMediatheque qui sera juste un
    // singleton avec la liste de tous les produits. On doit pouvoir la
    // récupérer directement depuis la BDD
    //private UUID catalogueMediatheque
    /*=============================================================*
     * GETTER AND SETTER
     *=============================================================*/
    
     public Produit() {
    }

    
     public Produit(String titre, boolean peutEtreReemprunter, Date datePublication, List<Auteur> auteurs, List<Genre> genres, ProduitType type) {
        this.titre = titre;
        this.peutEtreReemprunter = peutEtreReemprunter;
        this.datePublication = datePublication;
        this.auteurs = auteurs;
        this.genres = genres;
        this.type = type;
    }
 
     public int nbExemplairesDisponibles()
     {
         int nb=0;
         for(Exemplaire e:exemplaires)
         {
             if(e.getStatut().equals(Statut.DISPONIBLE) && e.getDateArriveeStock().before(new Date()))
                 nb++;
         }
         return nb;
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

    public List<Adherent> getAdherentsDemandeurs() {
        return adherentsDemandeurs;
    }

    public void addAdherentsDemandeur(Adherent adherentsDemandeur) {
        this.adherentsDemandeurs.add(adherentsDemandeur);
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
