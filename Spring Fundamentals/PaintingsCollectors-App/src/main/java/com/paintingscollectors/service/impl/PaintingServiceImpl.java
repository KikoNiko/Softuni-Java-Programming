package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.model.dto.PaintingDisplayDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.PaintingRepository;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.PaintingService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PaintingServiceImpl implements PaintingService {

    private final PaintingRepository paintingRepository;
    private final UserRepository userRepository;
    private final StyleRepository styleRepository;

    public PaintingServiceImpl(PaintingRepository paintingRepository, UserRepository userRepository, StyleRepository styleRepository) {
        this.paintingRepository = paintingRepository;
        this.userRepository = userRepository;
        this.styleRepository = styleRepository;
    }


    @Override
    public void addPainting(AddPaintingDTO paintingDTO, long userId) {
        Painting painting = new Painting();
        User user = userRepository.findById(userId).orElse(null);
        Style style = styleRepository.findByName(paintingDTO.getStyleName()).orElse(null);

        painting.setName(paintingDTO.getName());
        painting.setImgUrl(paintingDTO.getImgUrl());
        painting.setAuthor(paintingDTO.getAuthor());
        painting.setOwner(user);
        painting.setStyle(style);

        paintingRepository.save(painting);
        user.getPaintings().add(painting);
        userRepository.save(user);
    }

    @Override
    public List<PaintingDisplayDTO> getAllByUserId(long userId) {
        return paintingRepository.findAllByOwnerId(userId)
                .stream()
                .map(PaintingDisplayDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaintingDisplayDTO> getAllOther(long userId) {
        return paintingRepository.findAll()
                .stream()
                .filter(p -> p.getOwner().getId() != userId)
                .map(PaintingDisplayDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void addToFavorites(long userId, long paintingId) {
        Painting painting = paintingRepository.findById(paintingId).orElse(null);
        User currentUser = userRepository.getById(userId);
        painting.setFavorite(true);
        currentUser.getFavoritePaintings().add(painting);
        paintingRepository.save(painting);
        userRepository.save(currentUser);
    }

    @Override
    public void removeFromFavorites(long userId, long paintingId) {
        Painting painting = paintingRepository.findById(paintingId).orElse(null);
        User currentUser = userRepository.getById(userId);
        currentUser.getFavoritePaintings().remove(painting);
        painting.setFavorite(false);
        userRepository.save(currentUser);
        paintingRepository.save(painting);
    }

    @Override
    public boolean deletePainting(long id) {
        Optional<Painting> byId = paintingRepository.findById(id);
        if (byId.isEmpty()) {
            return false;
        }
        Painting painting = byId.get();
        if (painting.isFavorite()) {
            return false;
        }

        paintingRepository.delete(byId.get());
        return true;
    }

    @Override
    public void vote(long userId, long paintingId) {
        User user = userRepository.findById(userId).orElse(null);
        Painting painting = paintingRepository.findById(paintingId).orElse(null);
        if (user.getRatedPaintings().contains(painting)) {
            return;
        }
        painting.setVotes(painting.getVotes() + 1);
        user.getRatedPaintings().add(painting);
        paintingRepository.save(painting);
        userRepository.save(user);
    }

    @Override
    public Set<PaintingDisplayDTO> getMostRated() {
        return paintingRepository.findAll()
                .stream()
                .filter(p -> p.getVotes() > 0)
                .sorted(Comparator.comparing(Painting::getVotes)
                        .thenComparing(Painting::getName)
                        .reversed())
                .map(PaintingDisplayDTO::new)
                .collect(Collectors.toSet());

    }

}
