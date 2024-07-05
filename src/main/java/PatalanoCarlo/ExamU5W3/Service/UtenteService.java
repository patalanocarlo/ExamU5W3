package PatalanoCarlo.ExamU5W3.Service;

import PatalanoCarlo.ExamU5W3.Configuration.JWTTools;
import PatalanoCarlo.ExamU5W3.DTO.UserDTO;
import PatalanoCarlo.ExamU5W3.Entites.Role;
import PatalanoCarlo.ExamU5W3.Entites.Utente;
import PatalanoCarlo.ExamU5W3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public void registerUser(UserDTO userDTO) {
        Utente user = new Utente();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER", user));
        user.setRoles(roles);

        userRepository.save(user);
    }

    public boolean authenticate(String username, String password) {
        Utente user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con username: " + username));

        return passwordEncoder.matches(password, user.getPassword());
    }

    public Long getUserIdByUsername(String username) {
        Utente user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con username: " + username));
        return user.getId();
    }

    public String generateToken(Long userId) {
        return jwtTools.createToken(userId);
    }
}