/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alucard
 */
@Entity
@Table(name = "categorytbl", catalog = "inventorymgmt", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoryDTO.findAll", query = "SELECT c FROM CategoryDTO c"),
    @NamedQuery(name = "CategoryDTO.findByCatid", query = "SELECT c FROM CategoryDTO c WHERE c.catid = :catid"),
    @NamedQuery(name = "CategoryDTO.findByCatentrydate", query = "SELECT c FROM CategoryDTO c WHERE c.catentrydate = :catentrydate"),
    @NamedQuery(name = "CategoryDTO.findByCatname", query = "SELECT c FROM CategoryDTO c WHERE c.catname = :catname")})
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "catid")
    private Integer catid;
    @Column(name = "catentrydate")
    private String catentrydate;
    @Column(name = "catname")
    private String catname;

    public CategoryDTO() {
    }

    public CategoryDTO(Integer catid) {
        this.catid = catid;
    }

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public String getCatentrydate() {
        return catentrydate;
    }

    public void setCatentrydate(String catentrydate) {
        this.catentrydate = catentrydate;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catid != null ? catid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoryDTO)) {
            return false;
        }
        CategoryDTO other = (CategoryDTO) object;
        if ((this.catid == null && other.catid != null) || (this.catid != null && !this.catid.equals(other.catid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "inventory.dto.CategoryDTO[ catid=" + catid + " ]";
    }

}
