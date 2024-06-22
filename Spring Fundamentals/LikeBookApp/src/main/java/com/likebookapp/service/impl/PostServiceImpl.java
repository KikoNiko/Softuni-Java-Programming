package com.likebookapp.service.impl;

import com.likebookapp.model.dto.AddPostDTO;
import com.likebookapp.model.dto.PostInfoDTO;
import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final MoodRepository moodRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, MoodRepository moodRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.moodRepository = moodRepository;
    }

    @Override
    public void addPost(AddPostDTO addPostDTO, long id) {
        Post post = new Post();
        User user = userRepository.findById(id).orElse(null);
        post.setContent(addPostDTO.getContent());
        post.setUser(user);
        Mood mood = moodRepository.findByName(addPostDTO.getMood()).orElse(null);
        post.setMood(mood);
        postRepository.save(post);
    }

    @Override
    public List<PostInfoDTO> getAllPostsByUser(long userId) {
        return postRepository.findAllByUserId(userId)
                .stream()
                .map(PostInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostInfoDTO> getAllOtherPosts(long id) {
        return postRepository.findAll()
                .stream()
                .filter(p -> p.getUser().getId() != id)
                .map(PostInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void likePost(long postId, long userId) {
        Post post = postRepository.findById(postId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        post.getUserLikes().add(user);
        postRepository.save(post);
    }

    @Override
    public boolean removePost(long postId) {
        Optional<Post> byId = postRepository.findById(postId);
        if (byId.isEmpty()) {
            return false;
        }
        postRepository.delete(byId.get());
        return true;
    }

}
