package com.likebookapp.service;

import com.likebookapp.model.dto.AddPostDTO;
import com.likebookapp.model.dto.PostInfoDTO;

import java.util.List;

public interface PostService {

    void addPost(AddPostDTO addPostDTO, long id);

    List<PostInfoDTO> getAllPostsByUser(long userId);

    List<PostInfoDTO> getAllOtherPosts(long id);

    void likePost(long postId, long userId);
    boolean removePost(long postId);

}
