package q.bit.qcommerce.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CleanInactiveUsersService {

    @Autowired
    private UserService userService;

    private static final Logger log = Logger.getLogger(CleanInactiveUsersService.class);

    @Scheduled(cron = "${delete.inactive.users.cron}")
    public void cleanInactiveUsers() {
        log.info("start cleaning inactive users");
        userService.cleanInactiveUsers();
        log.info("finished cleaning inactive users");
    }
}
