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
@Table(name = "employeetbl", catalog = "inventorymgmt", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeDTO.findAll", query = "SELECT e FROM EmployeeDTO e"),
    @NamedQuery(name = "EmployeeDTO.findByEmployeeid", query = "SELECT e FROM EmployeeDTO e WHERE e.employeeid = :employeeid"),
    @NamedQuery(name = "EmployeeDTO.findByEmployeename", query = "SELECT e FROM EmployeeDTO e WHERE e.employeename = :employeename"),
    @NamedQuery(name = "EmployeeDTO.findByEmployeelname", query = "SELECT e FROM EmployeeDTO e WHERE e.employeelname = :employeelname"),
    @NamedQuery(name = "EmployeeDTO.findByEmployeecontactno", query = "SELECT e FROM EmployeeDTO e WHERE e.employeecontactno = :employeecontactno"),
    @NamedQuery(name = "EmployeeDTO.findByEmployeeaddress", query = "SELECT e FROM EmployeeDTO e WHERE e.employeeaddress = :employeeaddress"),
    @NamedQuery(name = "EmployeeDTO.findByEmployeepost", query = "SELECT e FROM EmployeeDTO e WHERE e.employeepost = :employeepost"),
    @NamedQuery(name = "EmployeeDTO.findByEmployeejoindate", query = "SELECT e FROM EmployeeDTO e WHERE e.employeejoindate = :employeejoindate"),
    @NamedQuery(name = "EmployeeDTO.findByEmployeesalary", query = "SELECT e FROM EmployeeDTO e WHERE e.employeesalary = :employeesalary"),
    @NamedQuery(name = "EmployeeDTO.findByEmployeeworkingyears", query = "SELECT e FROM EmployeeDTO e WHERE e.employeeworkingyears = :employeeworkingyears"),
    @NamedQuery(name = "EmployeeDTO.findByEmployeeleavedate", query = "SELECT e FROM EmployeeDTO e WHERE e.employeeleavedate = :employeeleavedate"),
    @NamedQuery(name = "EmployeeDTO.findByUsername", query = "SELECT e FROM EmployeeDTO e WHERE e.username = :username"),
    @NamedQuery(name = "EmployeeDTO.findByPassword", query = "SELECT e FROM EmployeeDTO e WHERE e.password = :password"),
    @NamedQuery(name = "EmployeeDTO.findByUsertype", query = "SELECT e FROM EmployeeDTO e WHERE e.usertype = :usertype")})
public class EmployeeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "employeeid")
    private Integer employeeid;
    @Column(name = "employeename")
    private String employeename;
    @Column(name = "employeelname")
    private String employeelname;
    @Column(name = "employeecontactno")
    private String employeecontactno;
    @Column(name = "employeeaddress")
    private String employeeaddress;
    @Column(name = "employeepost")
    private String employeepost;
    @Column(name = "employeejoindate")
    private String employeejoindate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "employeesalary")
    private Double employeesalary;
    @Column(name = "employeeworkingyears")
    private String employeeworkingyears;
    @Column(name = "employeeleavedate")
    private String employeeleavedate;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "usertype")
    private String usertype;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getEmployeelname() {
        return employeelname;
    }

    public void setEmployeelname(String employeelname) {
        this.employeelname = employeelname;
    }

    public String getEmployeecontactno() {
        return employeecontactno;
    }

    public void setEmployeecontactno(String employeecontactno) {
        this.employeecontactno = employeecontactno;
    }

    public String getEmployeeaddress() {
        return employeeaddress;
    }

    public void setEmployeeaddress(String employeeaddress) {
        this.employeeaddress = employeeaddress;
    }

    public String getEmployeepost() {
        return employeepost;
    }

    public void setEmployeepost(String employeepost) {
        this.employeepost = employeepost;
    }

    public String getEmployeejoindate() {
        return employeejoindate;
    }

    public void setEmployeejoindate(String employeejoindate) {
        this.employeejoindate = employeejoindate;
    }

    public Double getEmployeesalary() {
        return employeesalary;
    }

    public void setEmployeesalary(Double employeesalary) {
        this.employeesalary = employeesalary;
    }

    public String getEmployeeworkingyears() {
        return employeeworkingyears;
    }

    public void setEmployeeworkingyears(String employeeworkingyears) {
        this.employeeworkingyears = employeeworkingyears;
    }

    public String getEmployeeleavedate() {
        return employeeleavedate;
    }

    public void setEmployeeleavedate(String employeeleavedate) {
        this.employeeleavedate = employeeleavedate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeid != null ? employeeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeDTO)) {
            return false;
        }
        EmployeeDTO other = (EmployeeDTO) object;
        if ((this.employeeid == null && other.employeeid != null) || (this.employeeid != null && !this.employeeid.equals(other.employeeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "inventory.dto.EmployeeDTO[ employeeid=" + employeeid + " ]";
    }
    
}
