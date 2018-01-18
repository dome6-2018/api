package fr.ensicaen.dome6.api.wall;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface WallRepository extends JpaRepository<Wall, Long> {
    Wall findByUuid(String uuid);

    @Transactional
    void deleteByUuid(String uuid);
}