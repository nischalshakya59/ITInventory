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
@Table(name = "producttbl", catalog = "inventorymgmt", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductDTO.findAll", query = "SELECT p FROM ProductDTO p"),
    @NamedQuery(name = "ProductDTO.findByProductid", query = "SELECT p FROM ProductDTO p WHERE p.productid = :productid"),
    @NamedQuery(name = "ProductDTO.findByProductentrydate", query = "SELECT p FROM ProductDTO p WHERE p.productentrydate = :productentrydate"),
    @NamedQuery(name = "ProductDTO.findByProductcreatedby", query = "SELECT p FROM ProductDTO p WHERE p.productcreatedby = :productcreatedby"),
    @NamedQuery(name = "ProductDTO.findByProductsuppliername", query = "SELECT p FROM ProductDTO p WHERE p.productsuppliername = :productsuppliername"),
    @NamedQuery(name = "ProductDTO.findByProductmodel", query = "SELECT p FROM ProductDTO p WHERE p.productmodel = :productmodel"),
    @NamedQuery(name = "ProductDTO.findByProductbrand", query = "SELECT p FROM ProductDTO p WHERE p.productbrand = :productbrand"),
    @NamedQuery(name = "ProductDTO.findByProductcategory", query = "SELECT p FROM ProductDTO p WHERE p.productcategory = :productcategory"),
    @NamedQuery(name = "ProductDTO.findByProductqty", query = "SELECT p FROM ProductDTO p WHERE p.productqty = :productqty"),
    @NamedQuery(name = "ProductDTO.findByProductsellingprice", query = "SELECT p FROM ProductDTO p WHERE p.productsellingprice = :productsellingprice"),
    @NamedQuery(name = "ProductDTO.findByProductcostprice", query = "SELECT p FROM ProductDTO p WHERE p.productcostprice = :productcostprice"),
    @NamedQuery(name = "ProductDTO.findByProductprofit", query = "SELECT p FROM ProductDTO p WHERE p.productprofit = :productprofit"),
    @NamedQuery(name = "ProductDTO.findByProductwarranty", query = "SELECT p FROM ProductDTO p WHERE p.productwarranty = :productwarranty"),
    @NamedQuery(name = "ProductDTO.findByProductspecification", query = "SELECT p FROM ProductDTO p WHERE p.productspecification = :productspecification")})
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "productid")
    private Integer productid;
    @Column(name = "productentrydate")
    private String productentrydate;
    @Column(name = "productcreatedby")
    private String productcreatedby;
    @Column(name = "productsuppliername")
    private String productsuppliername;
    @Column(name = "productmodel")
    private String productmodel;
    @Column(name = "productbrand")
    private String productbrand;
    @Column(name = "productcategory")
    private String productcategory;
    @Column(name = "productqty")
    private Integer productqty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "productsellingprice")
    private Double productsellingprice;
    @Column(name = "productcostprice")
    private Double productcostprice;
    @Column(name = "productprofit")
    private Double productprofit;
    @Column(name = "productwarranty")
    private String productwarranty;
    @Column(name = "productspecification")
    private String productspecification;

    public ProductDTO() {
    }

    public ProductDTO(Integer productid) {
        this.productid = productid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductentrydate() {
        return productentrydate;
    }

    public void setProductentrydate(String productentrydate) {
        this.productentrydate = productentrydate;
    }

    public String getProductcreatedby() {
        return productcreatedby;
    }

    public void setProductcreatedby(String productcreatedby) {
        this.productcreatedby = productcreatedby;
    }

    public String getProductsuppliername() {
        return productsuppliername;
    }

    public void setProductsuppliername(String productsuppliername) {
        this.productsuppliername = productsuppliername;
    }

    public String getProductmodel() {
        return productmodel;
    }

    public void setProductmodel(String productmodel) {
        this.productmodel = productmodel;
    }

    public String getProductbrand() {
        return productbrand;
    }

    public void setProductbrand(String productbrand) {
        this.productbrand = productbrand;
    }

    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }

    public Integer getProductqty() {
        return productqty;
    }

    public void setProductqty(Integer productqty) {
        this.productqty = productqty;
    }

    public Double getProductsellingprice() {
        return productsellingprice;
    }

    public void setProductsellingprice(Double productsellingprice) {
        this.productsellingprice = productsellingprice;
    }

    public Double getProductcostprice() {
        return productcostprice;
    }

    public void setProductcostprice(Double productcostprice) {
        this.productcostprice = productcostprice;
    }

    public Double getProductprofit() {
        return productprofit;
    }

    public void setProductprofit(Double productprofit) {
        this.productprofit = productprofit;
    }

    public String getProductwarranty() {
        return productwarranty;
    }

    public void setProductwarranty(String productwarranty) {
        this.productwarranty = productwarranty;
    }

    public String getProductspecification() {
        return productspecification;
    }

    public void setProductspecification(String productspecification) {
        this.productspecification = productspecification;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productid != null ? productid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductDTO)) {
            return false;
        }
        ProductDTO other = (ProductDTO) object;
        if ((this.productid == null && other.productid != null) || (this.productid != null && !this.productid.equals(other.productid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "inventory.dto.ProductDTO[ productid=" + productid + " ]";
    }

}
