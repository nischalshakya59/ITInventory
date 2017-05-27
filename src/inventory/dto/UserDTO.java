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
@Table(name = "logintbl", catalog = "inventorymgmt", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserDTO.findAll", query = "SELECT u FROM UserDTO u"),
    @NamedQuery(name = "UserDTO.findByUserid", query = "SELECT u FROM UserDTO u WHERE u.userid = :userid"),
    @NamedQuery(name = "UserDTO.findByUsername", query = "SELECT u FROM UserDTO u WHERE u.username = :username"),
    @NamedQuery(name = "UserDTO.findByPassword", query = "SELECT u FROM UserDTO u WHERE u.password = :password"),
    @NamedQuery(name = "UserDTO.findByUsertype", query = "SELECT u FROM UserDTO u WHERE u.usertype = :usertype")})
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "usertype")
    private String usertype;

    public UserDTO() {
    }

    public UserDTO(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserDTO)) {
            return false;
        }
        UserDTO other = (UserDTO) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "inventory.dao.UserDTO[ userid=" + userid + " ]";
    }
    
}
