package nbacards.security;

import nbacards.data.AppUserRepository;
import nbacards.domain.Result;
import nbacards.domain.ResultType;
import nbacards.models.AppUser;
import nbacards.models.Credentials;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;


@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final Validator validator;
    private final PasswordEncoder encoder;

    public AppUserService(AppUserRepository appUserRepository, Validator validator, PasswordEncoder encoder) {
        this.appUserRepository = appUserRepository;
        this.validator = validator;
        this.encoder = encoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null || !user.isEnabled()) {
            throw new UsernameNotFoundException(username + " not found.");
        }
        return user;
    }
    public Result<AppUser> add(Credentials credentials) {
        Result<AppUser> result = validate(credentials);

        try {
            String hashedPassword = encoder.encode(credentials.getPassword());

            AppUser appUser = appUserRepository.create(
                    new AppUser(0, credentials.getUsername(), hashedPassword, false, List.of("USER")));

            result.setPayload(appUser);
        } catch (DuplicateKeyException ex) {
            result.addMessage("'username' is already in use.", ResultType.CONFLICT);
        }
        return result;
    }
    private Result<AppUser> validate(Credentials credentials) {
        Result<AppUser> result = new Result<>();
        if (credentials == null) {
            result.addMessage("credential cannot be null", ResultType.INVALID);
            return result;
        }
        Set<ConstraintViolation<Credentials>> violations = validator.validate(credentials);
        for (ConstraintViolation<Credentials> violation : violations) {
            result.addMessage(violation.getMessage(), ResultType.INVALID);
        }

        if (!isValidPassword(credentials.getPassword())) {
            result.addMessage("password must contain a digit, a letter, and a non-digit/non-letter", ResultType.INVALID);
        }

        return result;
    }
    private boolean isValidPassword(String password) {
        int digits = 0;
        int letters = 0;
        int others = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                digits++;
            } else if (Character.isLetter(c)) {
                letters++;
            } else {
                others++;
            }
        }

        return digits != 0 && letters != 0 && others != 0;
    }
}
