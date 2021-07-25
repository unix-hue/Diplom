package testPack.Controllers;

import org.springframework.web.bind.annotation.*;
import testPack.models.*;

import java.util.List;

@RestController
public class MyController {

    @GetMapping("/user/emails")
    public List<User> getUserEmails(){
        return DaoClass.getUsersEmails();
    }

    @GetMapping("/get/id")
    public LastId getId(){
        return DaoClass.getId();
    }

    @GetMapping("/get/userdata")
    public List<UserData> getUsersData(){
        return DaoClass.getUsersData();
    }

    @PostMapping("/add/user")
    public Integer addUser(@RequestBody User user) {
        DaoClass.addUser(user.getEmail(), user.getName(), user.getSurname(), user.getPassword());
        return 1;
    }

    @PostMapping("/add/desk")
    public Integer addDesk(@RequestBody Desk desk) {
        DaoClass.addDesk(desk.getUser_id(), desk.getName());
        return 1;
    }

    @PostMapping("/add/task")
    public Integer addTask(@RequestBody Task task) {
        DaoClass.addTask(task.getDesk_id(), task.getName(), task.getDescription(), task.getWorker());
        return 1;
    }

    @GetMapping("/get/password/{id}")
    public UserPass getPassword(@PathVariable("id") int id){
        return DaoClass.getPassword(id);
    }

    @GetMapping("/complete/task/{id}")
    public Integer completeTask(@PathVariable("id") int id){
        DaoClass.completeTask(id);
        return 1;
    }

    @GetMapping("/get/desks/{id}")
    public List<Desk> getDesks(@PathVariable("id") int id){
        return DaoClass.getDesks(id);
    }

    @GetMapping("/get/deskname/{id}")
    public Desk getDeskName(@PathVariable("id") int id){
        return DaoClass.getDeskName(id);
    }

    @GetMapping("/get/tasks/{id}")
    public List<Task> getTasks(@PathVariable("id") int id){
        return DaoClass.getTasks(id);
    }

    @GetMapping("/get/task/{id}")
    public Task getTask(@PathVariable("id") int id){
        return DaoClass.getTask(id);
    }
}