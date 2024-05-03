package com.example.java_19_headhunter.dao.implementation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserImageDao extends BasicDaoImpl {

//    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//    KeyHolder keyHolder;
//
//    public void save(UserImage userImage) {
//        String sql = "insert into user_images (user_id, file_name) " +
//                "values ( ?, ? )";
//        jdbcTemplate.update(sql, userImage.getUserId(), userImage.getFileName());
//    }
//
//    public UserImage getImageById(long imageId) {
//        String sql = "select * from user_images where ID = ?";
//        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserImage.class), imageId));
//    }
//
//
//    public Long save(Object obj) {
//        jdbcTemplate.update(con -> {
//            UserImage ui = (UserImage) obj;
//            PreparedStatement ps = con.prepareStatement(
//                    "insert into user_images(user_id, file_name) values (?, ?)",
//                    new String[]{"id"}
//            );
//            ps.setLong(1, ui.getUserId().getId());
//            ps.setString(2, ui.getFileName());
//            return ps;
//        }, keyHolder);
//        return Objects.requireNonNull(keyHolder.getKey()).longValue();
//    }
//
//
//    public void delete(Long id) {
//        jdbcTemplate.update(
//                "delete from user_images where user_id = ?;",
//                id
//        );
//    }
//
//    public Optional<UserImage> findImageByUserId(Long movieId) {
//        return Optional.ofNullable(
//                DataAccessUtils.singleResult(
//                        jdbcTemplate.query(
//                                """
//                                        select *
//                                        from user_images
//                                        where user_id = ?;
//                                        """,
//                                new BeanPropertyRowMapper<>(UserImage.class),
//                                movieId
//                        )
//                )
//        );
//    }
}
