package testPack.Controllers;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import testPack.models.*;

import java.util.List;

public class DaoClass {
    private static final JdbcTemplate jdbcTemplate;

    static {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/pm_db");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("postgres");

        jdbcTemplate = new JdbcTemplate(driverManagerDataSource);
    }

    public static List<User> getUsersEmails() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public static UserPass getPassword(int id) {
        return jdbcTemplate.query("SELECT password from users where id = ?;", new Object[]{id}, new BeanPropertyRowMapper<>(UserPass.class)).get(0);
    }

    public static void addUser (String email, String name, String surname, String password) {
        jdbcTemplate.update("INSERT INTO users (email, name, surname, password) VALUES (?, ?, ?, ?);", email, name, surname, password);
    }

    public static void addDesk(int user_id, String name) {
        jdbcTemplate.update("INSERT INTO desks (user_id, name) VALUES (?, ?);", user_id, name);
    }

    public static void addTask(int desk_id, String name, String description, String worker) {
        jdbcTemplate.update("INSERT INTO tasks (desk_id, name, description, worker) VALUES (?, ?, ?, ?);", desk_id, name, description, worker);
    }

    public static void completeTask(int id) {
        jdbcTemplate.update("DELETE FROM tasks WHERE id = ?;", id);
    }

    public static LastId getId() {
        return jdbcTemplate.query("SELECT id FROM users ORDER BY id DESC", new BeanPropertyRowMapper<>(LastId.class)).get(0);
    }

    public static List<Desk> getDesks(int id) {
        return jdbcTemplate.query("SELECT * FROM desks where user_id = ?;", new Object[]{id}, new BeanPropertyRowMapper<>(Desk.class));
    }

    public static Desk getDeskName(int id) {
        return jdbcTemplate.query("SELECT * FROM desks where id = ?;", new Object[]{id}, new BeanPropertyRowMapper<>(Desk.class)).get(0);
    }

    public static List<Task> getTasks(int id) {
        return jdbcTemplate.query("SELECT * FROM tasks where desk_id = ?;", new Object[]{id}, new BeanPropertyRowMapper<>(Task.class));
    }

    public static Task getTask(int id) {
        return jdbcTemplate.query("SELECT * FROM tasks where id = ?;", new Object[]{id}, new BeanPropertyRowMapper<>(Task.class)).get(0);
    }

    public static List<UserData> getUsersData() {
        return jdbcTemplate.query("SELECT name, surname, email FROM users", new BeanPropertyRowMapper<>(UserData.class));
    }
}