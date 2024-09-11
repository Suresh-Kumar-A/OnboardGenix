package app.web.onboardgenix.serviceimpl;

import app.web.onboardgenix.config.DefaultAdminConfig;
import app.web.onboardgenix.constants.AppRoleEnum;
import app.web.onboardgenix.entity.Role;
import app.web.onboardgenix.entity.User;
import app.web.onboardgenix.repository.RoleRepository;
import app.web.onboardgenix.repository.UserRepository;
import app.web.onboardgenix.utils.CommonUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Slf4j
@Data
@Service
public class LoadAppDefaultsServiceImpl {

    final CommonUtils commonUtils;
    final DefaultAdminConfig defaultAdminConfig;
    final RoleRepository roleRepository;
    final UserRepository userRepository;

    @Value("{app.default.admin.password}")

    public void createAppRoles() {
        for (AppRoleEnum appRole : AppRoleEnum.values()) {
            roleRepository.findByRoleName(appRole.name())
                    .ifPresentOrElse(value -> {
                    }, () -> log.info("Added Role : {}", roleRepository.save(Role.builder().roleName(appRole.name()).build()).getRoleName()));
        }
    }

    public void createDefaultAdminUser() {
        userRepository.findByUid(defaultAdminConfig.getEmpid())
                .ifPresentOrElse(value -> {
                        },
                        () -> log.info("Added Default Admin with Emp-Id : {}", userRepository.save(
                                        User.builder().uid(defaultAdminConfig.getEmpid()).name(defaultAdminConfig.getName())
                                                .verified(true).email(defaultAdminConfig.getEmail()).phoneNumber(defaultAdminConfig.getPhone())
                                                .createdAt(Date.from(Instant.now())).password(commonUtils.generatePassword(defaultAdminConfig.getPassword()))
                                                .role(roleRepository.findByRoleName(AppRoleEnum.ADMIN.name()).orElseThrow()).build())
                                .getUid()));
    }
}
