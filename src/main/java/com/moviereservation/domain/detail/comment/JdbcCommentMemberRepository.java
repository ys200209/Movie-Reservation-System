package com.moviereservation.domain.detail.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcCommentMemberRepository implements CommentMemberRepository{

    private final JdbcTemplate jdbcTemplate;

    private final String SELECT_COMMENT_BY_MOVIE_ID_SQL = "select c.seq, m.name, c.movies_seq, c.content, c.create_at, c.modify_at from comments c inner join members m on c.members_seq = m.seq " +
            "where c.movies_seq = ?";

    @Override
    public List<CommentMember> findByMovieId(Long id) {
        List<CommentMember> comments = jdbcTemplate.query(SELECT_COMMENT_BY_MOVIE_ID_SQL, new RowMapper<CommentMember>() {
            @Override
            public CommentMember mapRow(ResultSet rs, int rowNum) throws SQLException {
                CommentMember commentMember = new CommentMember(
                        rs.getLong("c.seq"),
                        rs.getString("m.name"),
                        rs.getLong("c.movies_seq"),
                        rs.getString("c.content"),
                        rs.getTimestamp("c.create_at"),
                        rs.getTimestamp("c.modify_at")
                );
                return commentMember;
            }
        }, id);
        return comments;
    }
}
