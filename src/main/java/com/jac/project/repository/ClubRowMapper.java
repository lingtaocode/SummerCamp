package com.jac.project.repository;
import com.jac.project.model.Club;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ClubRowMapper implements RowMapper<Club> {
    @Override
    public Club mapRow(ResultSet rs, int rowNum) throws SQLException {
        Club club = new Club();

        club.setCid(rs.getInt("cid"));
        club.setTitle(rs.getString("title"));
        club.setSdate(rs.getDate("sdate"));
        club.setPfile(rs.getString("pfile"));
        club.setComments(rs.getString("comments"));

        return club;

    }
}
