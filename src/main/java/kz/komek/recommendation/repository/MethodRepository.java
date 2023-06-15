package kz.komek.recommendation.repository;

import kz.komek.recommendation.model.Method;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MethodRepository extends JpaRepository<Method, Long> {
    List<Method> findAllBySpecialistId(Long specialistId);
}
