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
@Table(name = "brandtbl", catalog = "inventorymgmt", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BrandDTO.findAll", query = "SELECT b FROM BrandDTO b"),
    @NamedQuery(name = "BrandDTO.findByBrandid", query = "SELECT b FROM BrandDTO b WHERE b.brandid = :brandid"),
    @NamedQuery(name = "BrandDTO.findByBrandentrydate", query = "SELECT b FROM BrandDTO b WHERE b.brandentrydate = :brandentrydate"),
    @NamedQuery(name = "BrandDTO.findByBrandname", query = "SELECT b FROM BrandDTO b WHERE b.brandname = :brandname")})
public class BrandDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "brandid")
    private Integer brandid;
    @Column(name = "brandentrydate")
    private String brandentrydate;
    @Column(name = "brandname")
    private String brandname;

    public BrandDTO() {
    }

    public BrandDTO(Integer brandid) {
        this.brandid = brandid;
    }

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public String getBrandentrydate() {
        return brandentrydate;
    }

    public void setBrandentrydate(String brandentrydate) {
        this.brandentrydate = brandentrydate;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brandid != null ? brandid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BrandDTO)) {
            return false;
        }
        BrandDTO other = (BrandDTO) object;
        if ((this.brandid == null && other.brandid != null) || (this.brandid != null && !this.brandid.equals(other.brandid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "inventory.dto.BrandDTO[ brandid=" + brandid + " ]";
    }

}
