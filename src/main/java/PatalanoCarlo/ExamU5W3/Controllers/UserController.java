package PatalanoCarlo.ExamU5W3.Controllers;


import PatalanoCarlo.ExamU5W3.DTO.UserDTO;
import PatalanoCarlo.ExamU5W3.DTO.UserLoginDTO;
import PatalanoCarlo.ExamU5W3.DTO.UserLoginResponseDTO;
import PatalanoCarlo.ExamU5W3.Entites.Utente;
import PatalanoCarlo.ExamU5W3.Service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utente")
public class UserController {

    @Autowired
    private UtenteService userService;

    @GetMapping
    public ResponseEntity<List<Utente>> getAllUsers() {
        List<Utente> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody UserDTO userDTO) {
        try {
            userService.registerUser(userDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginDTO userDTO) {
        try {
            String username = userDTO.getUsername();
            String password = userDTO.getPassword();

            if (userService.authenticate(username, password)) {
                Long userId = userService.getUserIdByUsername(username);
                String token = userService.generateToken(userId);
                UserLoginResponseDTO responseDTO = new UserLoginResponseDTO(token);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

