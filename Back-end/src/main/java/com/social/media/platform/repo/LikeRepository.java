package com.social.media.platform.repo;

import com.social.media.platform.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Integer> {
}
