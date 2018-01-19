package fr.ensicaen.dome6.api.wall;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "beacons")
public class Beacon {
    @Id
    private String uuid;

    @NotEmpty
    private String minor;

    @NotEmpty
    private String major;

    @ManyToOne
    @JsonIgnore
    private Wall wall;

    private Date addedAt;

    public Beacon() {
        this.addedAt = new Date();
    }

    public Beacon(String uuid) {
        this();
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Beacon) {
            Beacon beacon = (Beacon) obj;
            return this.uuid.equals(beacon.uuid);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}