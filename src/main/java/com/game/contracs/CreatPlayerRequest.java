package com.game.contracs;

import com.game.entity.Profession;
import com.game.entity.Race;






public class CreatPlayerRequest {
    //@Size(min = 1, max = 12)
    //@NotBlank(message = "имя обязательно")
    private String name;
    //@Size(min = 1, max = 30)
    //@NotBlank(message = "титул обязателен")
    private String title;
    //@NotNull(message = "раса обязательна")
    private Race race;
    //@NotNull(message = "профессия обязательна")
    private Profession profession;
    private Boolean banned = false;
    //@Min(946684800L)//01,01,2000
    //@Max(32535215999L)//31.12.3000
    //@NotNull(message = "день рождения обязателен")
    private Long birthday;
    //@Max(10000000)
    //@Min(0)
    //@NotNull(message = "опыт обязателен")
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

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public boolean validate(){
        boolean result = true;
        if(name == null || name.length()<1 || name.length()>12){
            result = false;
        }
        if(title == null || title.length()<1 || title.length()>12){
            result = false;
        }
        if(birthday == null || birthday<946684800L || birthday > 988059600000L){
            result = false;
        }
        if(experience == null || experience<0 || experience > 10000000 ){
            result = false;
        }
        if(race == null){
            result = false;
        }
        if(profession == null){
            result = false;
        }
        if(banned == null ){
            result = false;
        }
        return result;
    }
}
