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
@Table(name = "suppliertbl", catalog = "inventorymgmt", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierDTO.findAll", query = "SELECT s FROM SupplierDTO s"),
    @NamedQuery(name = "SupplierDTO.findBySupplierid", query = "SELECT s FROM SupplierDTO s WHERE s.supplierid = :supplierid"),
    @NamedQuery(name = "SupplierDTO.findBySupplierentrydate", query = "SELECT s FROM SupplierDTO s WHERE s.supplierentrydate = :supplierentrydate"),
    @NamedQuery(name = "SupplierDTO.findBySuppliername", query = "SELECT s FROM SupplierDTO s WHERE s.suppliername = :suppliername"),
    @NamedQuery(name = "SupplierDTO.findBySuppliercontactno", query = "SELECT s FROM SupplierDTO s WHERE s.suppliercontactno = :suppliercontactno"),
    @NamedQuery(name = "SupplierDTO.findBySupplieraddress", query = "SELECT s FROM SupplierDTO s WHERE s.supplieraddress = :supplieraddress"),
    @NamedQuery(name = "SupplierDTO.findBySupplieremailaddress", query = "SELECT s FROM SupplierDTO s WHERE s.supplieremailaddress = :supplieremailaddress"),
    @NamedQuery(name = "SupplierDTO.findBySupplierdescription", query = "SELECT s FROM SupplierDTO s WHERE s.supplierdescription = :supplierdescription")})
public class SupplierDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "supplierid")
    private Integer supplierid;
    @Column(name = "supplierentrydate")
    private String supplierentrydate;
    @Column(name = "suppliername")
    private String suppliername;
    @Column(name = "suppliercontactno")
    private String suppliercontactno;
    @Column(name = "supplieraddress")
    private String supplieraddress;
    @Column(name = "supplieremailaddress")
    private String supplieremailaddress;
    @Column(name = "supplierdescription")
    private String supplierdescription;

    public SupplierDTO() {
    }

    public SupplierDTO(Integer supplierid) {
        this.supplierid = supplierid;
    }

    public Integer getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(Integer supplierid) {
        this.supplierid = supplierid;
    }

    public String getSupplierentrydate() {
        return supplierentrydate;
    }

    public void setSupplierentrydate(String supplierentrydate) {
        this.supplierentrydate = supplierentrydate;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public String getSuppliercontactno() {
        return suppliercontactno;
    }

    public void setSuppliercontactno(String suppliercontactno) {
        this.suppliercontactno = suppliercontactno;
    }

    public String getSupplieraddress() {
        return supplieraddress;
    }

    public void setSupplieraddress(String supplieraddress) {
        this.supplieraddress = supplieraddress;
    }

    public String getSupplieremailaddress() {
        return supplieremailaddress;
    }

    public void setSupplieremailaddress(String supplieremailaddress) {
        this.supplieremailaddress = supplieremailaddress;
    }

    public String getSupplierdescription() {
        return supplierdescription;
    }

    public void setSupplierdescription(String supplierdescription) {
        this.supplierdescription = supplierdescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierid != null ? supplierid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierDTO)) {
            return false;
        }
        SupplierDTO other = (SupplierDTO) object;
        if ((this.supplierid == null && other.supplierid != null) || (this.supplierid != null && !this.supplierid.equals(other.supplierid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "inventory.dto.SupplierDTO[ supplierid=" + supplierid + " ]";
    }

}
