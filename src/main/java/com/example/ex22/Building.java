package com.example.ex22;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "Building")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Building implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    // private static long nextId = 1;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "created")
    private String creationDate;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "building")
    private List<Address> addresses;

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        addresses.forEach(e -> sb.append(e).append(' '));

        return "Building{ id: " + id + " type: '" + type + "' created: " + creationDate + " Addresses: " + sb.toString() + "}";
    }
}
