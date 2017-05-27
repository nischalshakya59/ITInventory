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


@Entity
@Table(name = "saletbl", catalog = "inventorymgmt", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SaleDTO.findAll", query = "SELECT s FROM SaleDTO s"),
    @NamedQuery(name = "SaleDTO.findBySaleid", query = "SELECT s FROM SaleDTO s WHERE s.saleid = :saleid"),
    @NamedQuery(name = "SaleDTO.findBySaledate", query = "SELECT s FROM SaleDTO s WHERE s.saledate = :saledate"),
    @NamedQuery(name = "SaleDTO.findBySalecustomername", query = "SELECT s FROM SaleDTO s WHERE s.salecustomername = :salecustomername"),
    @NamedQuery(name = "SaleDTO.findBySalecustomeraddress", query = "SELECT s FROM SaleDTO s WHERE s.salecustomeraddress = :salecustomeraddress"),
    @NamedQuery(name = "SaleDTO.findBySalecustomercontactno", query = "SELECT s FROM SaleDTO s WHERE s.salecustomercontactno = :salecustomercontactno"),
    @NamedQuery(name = "SaleDTO.findBySalesupplier", query = "SELECT s FROM SaleDTO s WHERE s.salesupplier = :salesupplier"),
    @NamedQuery(name = "SaleDTO.findBySalebrand", query = "SELECT s FROM SaleDTO s WHERE s.salebrand = :salebrand"),
    @NamedQuery(name = "SaleDTO.findBySaleproduct", query = "SELECT s FROM SaleDTO s WHERE s.saleproduct = :saleproduct"),
    @NamedQuery(name = "SaleDTO.findBySaleqty", query = "SELECT s FROM SaleDTO s WHERE s.saleqty = :saleqty"),
    @NamedQuery(name = "SaleDTO.findBySaleamtperpiece", query = "SELECT s FROM SaleDTO s WHERE s.saleamtperpiece = :saleamtperpiece"),
    @NamedQuery(name = "SaleDTO.findBySaletotal", query = "SELECT s FROM SaleDTO s WHERE s.saletotal = :saletotal")})
public class SaleDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "saleid")
    private Integer saleid;
    @Column(name = "saledate")
    private String saledate;
    @Column(name = "salecustomername")
    private String salecustomername;
    @Column(name = "salecustomeraddress")
    private String salecustomeraddress;
    @Column(name = "salecustomercontactno")
    private String salecustomercontactno;
    @Column(name = "salesupplier")
    private String salesupplier;
    @Column(name = "salebrand")
    private String salebrand;
    @Column(name = "saleproduct")
    private String saleproduct;
    @Column(name = "saleqty")
    private Integer saleqty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saleamtperpiece")
    private Double saleamtperpiece;
    @Column(name = "saletotal")
    private Double saletotal;

    public SaleDTO() {
    }

    public SaleDTO(Integer saleid) {
        this.saleid = saleid;
    }

    public Integer getSaleid() {
        return saleid;
    }

    public void setSaleid(Integer saleid) {
        this.saleid = saleid;
    }

    public String getSaledate() {
        return saledate;
    }

    public void setSaledate(String saledate) {
        this.saledate = saledate;
    }

    public String getSalecustomername() {
        return salecustomername;
    }

    public void setSalecustomername(String salecustomername) {
        this.salecustomername = salecustomername;
    }

    public String getSalecustomeraddress() {
        return salecustomeraddress;
    }

    public void setSalecustomeraddress(String salecustomeraddress) {
        this.salecustomeraddress = salecustomeraddress;
    }

    public String getSalecustomercontactno() {
        return salecustomercontactno;
    }

    public void setSalecustomercontactno(String salecustomercontactno) {
        this.salecustomercontactno = salecustomercontactno;
    }

    public String getSalesupplier() {
        return salesupplier;
    }

    public void setSalesupplier(String salesupplier) {
        this.salesupplier = salesupplier;
    }

    public String getSalebrand() {
        return salebrand;
    }

    public void setSalebrand(String salebrand) {
        this.salebrand = salebrand;
    }

    public String getSaleproduct() {
        return saleproduct;
    }

    public void setSaleproduct(String saleproduct) {
        this.saleproduct = saleproduct;
    }

    public Integer getSaleqty() {
        return saleqty;
    }

    public void setSaleqty(Integer saleqty) {
        this.saleqty = saleqty;
    }

    public Double getSaleamtperpiece() {
        return saleamtperpiece;
    }

    public void setSaleamtperpiece(Double saleamtperpiece) {
        this.saleamtperpiece = saleamtperpiece;
    }

    public Double getSaletotal() {
        return saletotal;
    }

    public void setSaletotal(Double saletotal) {
        this.saletotal = saletotal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (saleid != null ? saleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaleDTO)) {
            return false;
        }
        SaleDTO other = (SaleDTO) object;
        if ((this.saleid == null && other.saleid != null) || (this.saleid != null && !this.saleid.equals(other.saleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "inventory.dto.SaleDTO[ saleid=" + saleid + " ]";
    }
    
}
