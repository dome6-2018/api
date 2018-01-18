package fr.ensicaen.dome6.api.wall;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
@Entity
@Table(name = "walls")
public class Wall implements Serializable {
    @Id
    private String uuid;

    @NotNull
    private String name;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    @NotNull
    private int resX;

    @NotNull
    private int resY;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Color[][] drawing;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="wall")
    private Set<Beacon> beacons = new HashSet<>();

    private Date createdAt;
    private Date updatedAt;
    private boolean locked;

    public Wall() {
        this.uuid = UUID.randomUUID().toString();
        this.createdAt = new Date();
        this.locked = false;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getResX() {
        return resX;
    }

    public void setResX(int resX) {
        this.resX = resX;
    }

    public int getResY() {
        return resY;
    }

    public void setResY(int resY) {
        this.resY = resY;
    }

    public Color[][] getDrawing() {
        return drawing;
    }

    public void setDrawing(Color[][] drawing) {
        this.drawing = drawing;
    }

    public Set<Beacon> getBeacons() {
        return beacons;
    }

    public void setBeacons(Set<Beacon> beacons) {
        this.beacons = beacons;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Wall) {
            Wall wall = (Wall) obj;
            return this.uuid.equals(wall.uuid);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}