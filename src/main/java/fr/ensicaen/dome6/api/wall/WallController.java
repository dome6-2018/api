package fr.ensicaen.dome6.api.wall;

import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import javax.persistence.NoResultException;
import java.util.List;

@RestController
@RequestMapping("/walls")
public class WallController {

    private WallRepository wallRepository;
    private BeaconRepository beaconRepository;

    public WallController(WallRepository wallRepository, BeaconRepository beaconRepository) {
        this.wallRepository = wallRepository;
        this.beaconRepository = beaconRepository;
    }

    @ApiOperation(value = "Récupération de tous les murs.")
    @RequestMapping(method = RequestMethod.GET)
    public List<Wall> getWalls() {
        return wallRepository.findAll();
    }

    @ApiOperation(value = "Récupération d'un mur.")
    @RequestMapping(value = "/{wallUuid}", method = RequestMethod.GET)
    public Wall getWall(@PathVariable("wallUuid") String wallUuid) {
        Wall wall = wallRepository.findByUuid(wallUuid);

        if (wall == null) {
            throw new NoResultException();
        }

        return wall;
    }

    @ApiOperation(value = "Modification de certaines données d'un mur.")
    @RequestMapping(value = "/{wallUuid}/drawing", method = RequestMethod.PATCH)
    public void updateWallDrawing(@PathVariable("wallUuid") String wallUuid, @RequestBody Wall wall) {
        Wall actualWall = wallRepository.findByUuid(wallUuid);

        if (actualWall == null) {
            throw new NoResultException();
        }

        actualWall.setDrawing(wall.getDrawing());
        wallRepository.save(actualWall);
    }

    @ApiOperation(value = "Ajout d'un nouveau mur.")
    @RequestMapping(method = RequestMethod.POST)
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void newWall(@RequestBody Wall wall) {
        wallRepository.save(wall);
    }

    @ApiOperation(value = "Modification du dessin du mur.")
    @RequestMapping(value = "/{wallUuid}", method = RequestMethod.PUT)
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateWall(@PathVariable("wallUuid") String wallUuid, @RequestBody Wall wall) {
        Wall actualWall = wallRepository.findByUuid(wallUuid);

        if (actualWall == null || wall.equals(actualWall)) {
            throw new NoResultException();
        }

        wallRepository.save(wall);
    }

    @ApiOperation(value = "Suppression d'un mur.")
    @RequestMapping(value = "/{wallUuid}", method = RequestMethod.DELETE)
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteWall(@PathVariable("wallUuid") String wallUuid) {
        wallRepository.deleteByUuid(wallUuid);
    }

    @ApiOperation(value = "Affectation d'un beacon à un mur.")
    @RequestMapping(value = "/{wallUuid}/beacons", method = RequestMethod.POST)
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addBeaconToWall(@PathVariable("wallUuid") String wallUuid, @RequestBody Beacon beacon) {
        Wall wall = wallRepository.findByUuid(wallUuid);

        if (wall == null) {
            throw new NoResultException();
        }

        beacon.setWall(wall);
        beaconRepository.save(beacon);
    }

    @ApiOperation(value = "Suppression d'un beacon d'un mur.")
    @RequestMapping(value = "/{wallUuid}/beacons/{beaconUuid}", method = RequestMethod.DELETE)
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteBeaconFromWall(@PathVariable("wallUuid") String wallUuid, @PathVariable("beaconUuid") String beaconUuid) {
        Wall wall = wallRepository.findByUuid(wallUuid);

        if (wall == null || !wall.getBeacons().contains(new Beacon(beaconUuid))) {
            throw new NoResultException();
        }

        beaconRepository.deleteByUuid(beaconUuid);
    }

}