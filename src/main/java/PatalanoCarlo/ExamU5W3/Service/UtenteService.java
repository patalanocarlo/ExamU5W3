package PatalanoCarlo.ExamU5W3.Service;

import PatalanoCarlo.ExamU5W3.Configuration.JWTTools;
import PatalanoCarlo.ExamU5W3.DTO.UserDTO;
import PatalanoCarlo.ExamU5W3.Entites.Role;
import PatalanoCarlo.ExamU5W3.Entites.Utente;
import PatalanoCarlo.ExamU5W3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UtenteService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JWTTools jwtTools;

    public List<Utente> getAllUsers() {
        return userRepository.findAll();
    }
    public void registerUser(UserDTO userDTO) {
        Utente user = new Utente();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));


        Set<Role> roles = new HashSet<>();
        if (userDTO.isOrganizer()) {
            roles.add(new Role("ROLE_ORGANIZER", user));
        } else {
            roles.add(new Role("ROLE_USER", user));
        }
        user.setRoles(roles);

        userRepository.save(user);
    }



    public boolean authenticate(String username, String password) {
        Utente user = userRepository.findByUsername(username)
                .orElse(null);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isUserOrganizer(String username) {
        Utente user = userRepository.findByUsername(username)
                .orElse(null);

        if (user != null && user.getRoles() != null) {
            for (Role role : user.getRoles()) {
                if ("ROLE_ORGANIZER".equals(role.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public String generateOrganizerToken(Long userId) {
        return jwtTools.createToken(userId);
    }
}
