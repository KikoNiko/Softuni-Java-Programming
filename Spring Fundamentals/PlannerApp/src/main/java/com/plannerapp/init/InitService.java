package com.plannerapp.init;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.PriorityEnum;
import com.plannerapp.repo.PriorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitService implements CommandLineRunner {
    private final PriorityRepository priorityRepository;

    public InitService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (priorityRepository.count() == 0) {
            String description = "";
            for (PriorityEnum priorityName : PriorityEnum.values()) {
                switch (priorityName) {
                    case LOW:
                        description = "Should be fixed if time permits but can be postponed.";
                        break;
                    case URGENT:
                        description = "An urgent problem that blocks the system use until the issue is resolved.";
                        break;
                    case IMPORTANT:
                        description = "A core functionality that your product is explicitly supposed to perform is compromised.";
                        break;
                }
                Priority priority = new Priority(priorityName, description);
                priorityRepository.save(priority);
            }
        }
    }
}
