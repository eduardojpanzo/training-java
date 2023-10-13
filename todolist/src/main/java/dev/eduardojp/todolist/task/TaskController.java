package dev.eduardojp.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.eduardojp.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  
  @Autowired
  private ITaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity create (@RequestBody TaskModel taskModel, HttpServletRequest request){
    taskModel.setIdUser((UUID)request.getAttribute("idUser"));

    var currentDate = LocalDateTime.now();

    if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de ínicio / data de fim deve ser maior do que a atual");
    }

    if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de fim deve ser maior que a de ínico");
    }

    var task = this.taskRepository.save(taskModel);

    return ResponseEntity.status(HttpStatus.CREATED).body(task);
  }
  
  @GetMapping("/")
  public ResponseEntity list (HttpServletRequest request){
    var idUser = request.getAttribute("idUser");
    var tasks = this.taskRepository.findByIdUser((UUID) idUser);

    return ResponseEntity.status(HttpStatus.OK).body(tasks);
  }
  
  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){
      var task = this.taskRepository.findById(id).orElse(null);

      if (task == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não encotrada");
      }
      var idUser = request.getAttribute("idUser");

      if (!task.getIdUser().equals(idUser)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sem permissão para alterar");
      }

      Utils.copyNonNullProperties(taskModel, task);
      var taskupdated = this.taskRepository.save(task);

      return ResponseEntity.status(HttpStatus.OK).body(taskupdated);
  }
}