package pl.edu.wszib.orderinfrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaOrderDao extends JpaRepository<OrderEntity, Long> {
    boolean existsByPublicId(String id);

    Optional<OrderEntity> findByPublicId(String id);
}
