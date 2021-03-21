package pl.javastart.equipy.components.assignment.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findAllByUserId(Long userId);

    List<Assignment> findAllByAssetId(Long assetId);
}
