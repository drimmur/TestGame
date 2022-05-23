package com.game.contracs;

import com.game.entity.Profession;
import com.game.entity.Race;



public class UpdatePlaerRequest {

    //@Size(max = 12)
    private String name;
    //@Size(max = 30)
    private String title;
    private Race race;
    private Profession profession;
    //@Min(946684800)//01,01,2000
    //@Max(32535215999L)//31.12.3000
    private Long birthday;
    private Boolean banned;
    //@Max(10000000)
    //@Min(0)
    private Integer experience;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
    public boolean validate(){
        boolean result = true;
        if(name == null ){
            result = false;
        }
        if(title == null ){
            result = false;
        }
        if(birthday == null ){
            result = false;
        }
        if(experience == null ){
            result = false;
        }
        if(race == null){
            result = false;
        }
        if(profession == null){
            result = false;
        }
        if(banned == null){
            result = false;
        }
        return result;
    }
}
