package com.paintingscollectors.repository;

import com.paintingscollectors.model.entity.Painting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {

    List<Painting> findAllByOwnerId(long id);

}
