package com.game.controller;

import com.game.contracs.*;
import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class PlayerController {
    //

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping(value = "/rest/players")
    public ResponseEntity<List<PlayerDto>> GetPlayers(GetPlayersRequest request) {
        Pageable pageable = CreatPageable(request);
        Page<Player> players = playerRepository.findAll(CreateSpecification(request), pageable);
        List<PlayerDto> playerDtos = players.stream().map(p -> MapToDto(p)).collect(Collectors.toList());
        return new ResponseEntity<>(playerDtos, HttpStatus.OK);

    }

    @GetMapping(value = "/rest/players/count")
    public ResponseEntity<Long> GetPlayersCount(GetPlayersCountRequest request) {

        long count = playerRepository.count(CreateSpecification(request));
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


    @PostMapping(value = "/rest/players")
    public ResponseEntity<PlayerDto> CreatePlayer(@RequestBody CreatPlayerRequest request) {
        if (!request.validate()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Player player = new Player();
        player.setName(request.getName());
        player.setTitle(request.getTitle());
        player.setRace(request.getRace());
        player.setProfession(request.getProfession());
        player.setBanned(request.getBanned());
        player.setBirthday(new Date(request.getBirthday()));
        player.setExperience(request.getExperience());
        player.setLevel((int) (Math.floor((Math.sqrt(2500 + 200 * player.getExperience()) - 50) / 100)));
        player.setUntilNextLevel(50 * (player.getLevel() + 1) * (player.getLevel() + 2) - player.getExperience());
        playerRepository.saveAndFlush(player);
        return new ResponseEntity<>(MapToDto(player), HttpStatus.OK);
    }

    @GetMapping(value = "/rest/players/{id}")
    public ResponseEntity<PlayerDto> GetPlayer(@PathVariable Long id) {
        if (id <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Player> searchResult = playerRepository.findById(id);
        return searchResult.isPresent()
                ? new ResponseEntity<>(MapToDto(searchResult.get()), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/rest/players/{id}")
    public ResponseEntity<PlayerDto> UpdatePlayer(@PathVariable Long id, @RequestBody UpdatePlaerRequest request) {
        if (id <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Player> searchResult = playerRepository.findById(id);
        if (!searchResult.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Player player = searchResult.get();
        if (request.getName() == null && request.getTitle() == null && request.getProfession() == null
                && request.getRace() == null && request.getBirthday() == null && request.getBanned() == null && request.getExperience() == null) {
            return new ResponseEntity<>(MapToDto(player), HttpStatus.OK);
        }

        if (request.getName() != null && request.getName().length() > 1 && request.getName().length() <= 12) {
            player.setName(request.getName());
        }
        if (request.getTitle() != null && request.getTitle().length() > 1 && request.getTitle().length() <= 30) {
            player.setTitle(request.getTitle());
        }
        if (request.getProfession() != null) {
            player.setProfession(request.getProfession());
        }
        if (request.getRace() != null) {
            player.setRace(request.getRace());
        }
        if (request.getBirthday() != null && request.getBirthday() >= 946684800L && request.getBirthday() <= 48282047990000L) {
            player.setBirthday(new Date(request.getBirthday()));
        } else if (request.getBirthday() != null && request.getBirthday() < 0 || request.getBirthday() != null && request.getBirthday() > 48282047990000L) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (request.getBanned() != null) {
            player.setBanned(request.getBanned());
        }
        if (request.getExperience() != null && request.getExperience() >= 0 && request.getExperience() <= 10000000) {
            player.setExperience(request.getExperience());
            if (player.getUntilNextLevel() <= player.getExperience()) {
                player.setLevel((int) (Math.floor((Math.sqrt(2500 + 200 * player.getExperience()) - 50) / 100)));
                player.setUntilNextLevel(50 * (player.getLevel() + 1) * (player.getLevel() + 2) - player.getExperience());
            }
        } else if (request.getExperience() != null && request.getExperience() < 0 || request.getExperience() != null && request.getExperience() > 10000000) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        playerRepository.saveAndFlush(player);
        return new ResponseEntity<>(MapToDto(player), HttpStatus.OK);
    }

    @DeleteMapping(value = "/rest/players/{id}")
    public ResponseEntity<Void> DeletePlayer(@PathVariable Long id) {
        if (id <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if (!optionalPlayer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerRepository.delete(optionalPlayer.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private PlayerDto MapToDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.id = player.getId();
        playerDto.name = player.getName();
        playerDto.title = player.getTitle();
        playerDto.race = player.getRace();
        playerDto.profession = player.getProfession();
        playerDto.birthday = player.getBirthday().getTime();
        playerDto.banned = player.getBanned();
        playerDto.experience = player.getExperience();
        playerDto.level = player.getLevel();
        playerDto.untilNextLevel = player.getUntilNextLevel();
        return playerDto;
    }

    private Example<Player> CreatSearchExample(PlayerFilters request) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", match -> match.contains().ignoreCase())
                .withMatcher("title", match -> match.contains().ignoreCase());
        Player player = new Player();
        player.setName(request.getName());
        player.setTitle(request.getTitle());
        player.setRace(request.getRace());
        player.setProfession(request.getProfession());
        player.setBanned(request.getBanned());
        Example<Player> example = Example.of(player, matcher);
        return example;
    }

    private Pageable CreatPageable(GetPlayersRequest request) {// настраивает поиск, к приеру игрок с 25 по 50
        Sort sort = Sort.by(Sort.Direction.ASC, request.getOrder() == null ? PlayerOrder.ID.getFieldName() : request.getOrder().getFieldName());
        return PageRequest.of(
                request.getPageNumber() == null ? 0 : request.getPageNumber(),
                request.getPageSize() == null ? 3 : request.getPageSize(),
                sort);
    }

    private Specification<Player> CreateSpecification(PlayerFilters request) {
        return (Specification<Player>) (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            //создание условия(predicates) на основе приера (example)
            Example<Player> example = CreatSearchExample(request);
            predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, example));

            //добавление в список условий новые условия
            if (request.getAfter() != null) {
                predicates.add(builder.greaterThan(root.get("birthday"), new Date(request.getAfter())));
            }

            if (request.getBefore() != null) {
                predicates.add(builder.lessThan(root.get("birthday"), new Date(request.getBefore())));
            }

            if (request.getMinExperience() != null) {
                predicates.add(builder.greaterThan(root.get("experience"), request.getMinExperience()));
            }

            if (request.getMaxExperience() != null) {
                predicates.add(builder.lessThan(root.get("experience"), request.getMaxExperience()));
            }

            if (request.getMinLevel() != null) {
                predicates.add(builder.greaterThan(root.get("level"), request.getMinLevel()));
            }

            if (request.getMaxLevel() != null) {
                predicates.add(builder.lessThan(root.get("level"), request.getMaxLevel()));
            }


            final Predicate[] predicatesArray = predicates.toArray(new Predicate[predicates.size()]);
            return builder.and(predicatesArray);
        };
    }
}
