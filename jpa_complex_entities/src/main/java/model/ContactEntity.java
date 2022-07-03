package model;

import javax.persistence.*;

@Entity
@Table(name = "CONTACTS")
public class ContactEntity {

    @Id
    @Column(name = "user_id")
    private Long id;

    private String city;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId// indica che la FK é la PK nella tabella contancts
    private UserEntity userEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
