package com.game.repository;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.jpa.domain.Specification;
import java.util.Date;

public class PlayerSpecs {
    public static Specification<Player> nameContains(String word) {
        return (Specification<Player>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"),
                "%" + word + "%");
    }

    public static Specification<Player> titleContains(String word) {
        return (Specification<Player>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"),
                "%" + word + "%");
    }
    public static Specification<Player> findByRaceEquals(Race race) {
        return (Specification<Player>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("race"), race);
    }

    public static Specification<Player> findByProfessionEquals(Profession profession) {
        return (root, query, cb) -> cb.equal(root.get("profession"), profession);
    }

    public static Specification<Player> findDateAfter(Long after) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("birthday"), new Date(after));
    }

    public static Specification<Player> findDateBefore(Long before) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("birthday"), new Date(before));
    }

    public static Specification<Player> banned(Boolean banned) {
        return (root, query, cb) -> cb.equal(root.get("banned"), banned);
    }

    public static Specification<Player> findGreaterOrEqualExperience(Integer minExperience) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("experience"), minExperience));
    }

    public static Specification<Player> findLessOrEqualExperience(Integer maxExperience) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("experience"), maxExperience));
    }

    public static Specification<Player> findGreaterOrEqualLevel(Integer minLevel) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("level"), minLevel));
    }

    public static Specification<Player> findLessOrEqualLevel(Integer maxLevel) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("level"), maxLevel));
    }
}
