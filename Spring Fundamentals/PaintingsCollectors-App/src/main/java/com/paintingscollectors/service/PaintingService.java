package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.model.dto.PaintingDisplayDTO;

import java.util.List;
import java.util.Set;

public interface PaintingService {

    void addPainting(AddPaintingDTO paintingDTO, long userId);

    List<PaintingDisplayDTO> getAllByUserId(long userId);

    List<PaintingDisplayDTO> getAllOther(long userId);

    void addToFavorites(long userId, long paintingId);

    void removeFromFavorites(long userId, long paintingId);

    boolean deletePainting(long id);

    void vote(long userId, long paintingId);

    Set<PaintingDisplayDTO> getMostRated();
}
