
package bda1.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author GaspardP <gaspardp@kth.se>
 */
@Entity
public class Adherent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToMany
    private List<CarteMagnetique> cartesMagnetiques;

    /****************************************************************
     * GETTER AND SETTER
     *
     ***************************************************************/
    
    /**
     * @return  Identifiant de l'instance adhérent
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<CarteMagnetique> getCartesMagnetiques() {
        return cartesMagnetiques;
    }

    public void setCartesMagnetiques(List<CarteMagnetique> cartesMagnetiques) {
        this.cartesMagnetiques = cartesMagnetiques;
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
        if (!(object instanceof Adherent)) {
            return false;
        }
        Adherent other = (Adherent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bda1.entity.Adherent[ id=" + id + " ]";
    }
    
}