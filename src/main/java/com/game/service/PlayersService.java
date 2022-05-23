package com.game.service;

import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import com.mysql.cj.Query;
import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlayersService {
    private PlayerRepository playerRepository;

    private SessionFactory sessionFactory;

    /*
    public List<Player> getAllPlayers(){
     //   Session session = sessionFactory.getCurrentSession();
     //   Query query = session.createQuery("FROM Player");
     //   return  query.list();
    }
  //  public List<Player> getAllPlayerPage(int pageNumber, int pageSize){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Player ");
        List<Player> players = query.list();
        List<Player> topPlayers = new ArrayList<>();
        if(players.size()<=pageNumber){
            return players;
        }else if(pageNumber < players.size() && players.size() < (pageNumber + pageSize)){
            for (int i = pageNumber; i<players.size(); i++){
                topPlayers.add(players.get(i));
            }
            return topPlayers;
        }
        else {
            for (int i = pageNumber; i<players.size(); i++){
                topPlayers.add(players.get(i));
            }
            return topPlayers;
        }
    }
    public Player get(){
       return null;
    }
    public void add(){
    }
    public void delete(){
    }
    public void UpdatePlayer(){

    }*/

}
