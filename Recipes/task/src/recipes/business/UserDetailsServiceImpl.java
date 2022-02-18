package recipes.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import recipes.persistence.UserRepository;
import recipes.persistence.models.UserEntity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findById(username).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("Not found: " + username);
        }

        return new UserDetailsImpl(user);
    }
}
