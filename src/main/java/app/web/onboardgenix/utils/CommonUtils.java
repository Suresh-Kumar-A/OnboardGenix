package app.web.onboardgenix.utils;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class CommonUtils {

    public String generatePassword(String password) {
        return password.toUpperCase();
    }
}
