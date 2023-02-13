package com.example.likebookapplication.service;

import com.example.likebookapplication.model.dtos.AddPostDTO;
import com.example.likebookapplication.model.dtos.PostDTO;
import com.example.likebookapplication.model.entity.Mood;
import com.example.likebookapplication.model.entity.MoodsEnum;
import com.example.likebookapplication.model.entity.Post;
import com.example.likebookapplication.model.entity.User;
import com.example.likebookapplication.repository.MoodRepository;
import com.example.likebookapplication.repository.PostRepository;
import com.example.likebookapplication.repository.UserRepository;
import com.example.likebookapplication.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final MoodRepository moodRepository;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository,
                       MoodRepository moodRepository,
                       LoggedUser loggedUser,
                       UserRepository userRepository) {
        this.postRepository = postRepository;
        this.moodRepository = moodRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    //съхрани пост
    public boolean addPost (AddPostDTO addPostDTO){
        //търсим логнат юзър, защото съобщението не може да е без юзър
        Optional<User> logUser = this.userRepository.findById (this.loggedUser.getId ());

        if(logUser.isEmpty ()){
            return false;
        }

        MoodsEnum type = switch (addPostDTO.getMood ().toUpperCase ()){
            case "HAPPY" -> MoodsEnum.HAPPY;
            case "SAD" -> MoodsEnum.SAD;
            case "INSPIRED" -> MoodsEnum.INSPIRED;
            default -> MoodsEnum.HAPPY;
        };
        Mood mood = this.moodRepository.findByMoodName (type);

        Post post = new Post ();
        post.setMood (mood);
        post.setContent (addPostDTO.getContent ());
        post.setUser (logUser.get ());

        this.postRepository.save (post);
        return true;
    }

    public List<PostDTO>getPostOwnedBy(long ownerId){
        return this.postRepository.findByUserId (ownerId)
                .stream ()
                .map (PostDTO::new)
                .collect(Collectors.toList());
    }

    public List<PostDTO> getPostNotOwnedBy(long loggedUserId) {
        return this.postRepository.findByUserIdNot (loggedUserId)
                .stream ()
                .map (PostDTO::new)
                .collect(Collectors.toList());
    }

    public void removePostById(Long id){
        postRepository.deleteById (id);
    }

    public void likePostWithId(Long postId, long userId) {
        Post post = postRepository.findById (postId).orElse (null);
        User user = userRepository.findById (userId).orElse (null);

        post.getUserLikes ().add (user);
        post.setLikes (post.getLikes ()+1);

        postRepository.save (post);
    }


}
