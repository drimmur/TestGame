package com.game.contracs;


import com.game.entity.Profession;
import com.game.entity.Race;

public class PlayerDto {
    public Long id;
    public String name;
    public String title;
    public Race race;
    public Profession profession;
    public Long birthday;
    public Boolean banned;
    public Integer experience;
    public Integer level;
    public Integer untilNextLevel;
}
