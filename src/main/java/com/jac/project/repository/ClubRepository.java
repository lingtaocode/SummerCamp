package com.jac.project.repository;

import com.jac.project.exception.ClubNotFoundException;
import com.jac.project.exception.DatabaseException;
import com.jac.project.model.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClubRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Club newClub(Club club){

        try {
            jdbcTemplate.update("insert into club (title, sdate, pfile, comments) values(?,?,?,?)",
                    club.getTitle(), club.getSdate(), club.getPfile(), club.getComments());
        } catch (Exception e){
            throw new DatabaseException("newClub() is wrong.");
        }
        return club;
    }

    public Club updateClub(Club club){
        try {
            jdbcTemplate.update("update club set title=?, sdate=?, pfile=?, comments=? where cid = ?",
                    club.getTitle(), club.getSdate(), club.getPfile(), club.getComments(), club.getCid());
        } catch (DatabaseException e){
            throw new DatabaseException("updateClub() is wrong.");
        }
        return club;
    }

    public Club getClubByCid(int cid){
        try {
            String sql = "select * from club where cid = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{cid}, new ClubRowMapper());
        } catch (DatabaseException e){
            throw new DatabaseException("getClubByCid() is wrong.");
        }
    }

    public List<Club> getAllClub(){
        List clubList = new ArrayList<Club>();
        try {
            String sql = "select * from club";
            clubList = jdbcTemplate.query(sql, new ClubRowMapper());

        } catch (DatabaseException e){
            throw new DatabaseException("getAllClub() is wrong.");
        }

        return clubList;
    }


}
