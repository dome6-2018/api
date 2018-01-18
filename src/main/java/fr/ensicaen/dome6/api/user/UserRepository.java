package fr.ensicaen.dome6.api.user;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(int id);
    User findByUsername(String username);

    @Transactional
    User deleteById(int id);
}