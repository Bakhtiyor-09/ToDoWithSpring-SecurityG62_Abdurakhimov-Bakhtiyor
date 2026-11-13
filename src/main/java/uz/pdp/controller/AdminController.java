package uz.pdp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.entity.AuthUser;
import uz.pdp.entity.ToDo;
import uz.pdp.service.AdminService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("users")
    public ResponseEntity<List<AuthUser>> users(){
     Optional<List<AuthUser>> users = adminService.getAllUsers();

      return ResponseEntity.ok(users.get());
    }

}
