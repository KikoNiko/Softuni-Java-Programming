package com.likebookapp.init;

import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.MoodName;
import com.likebookapp.repository.MoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InitService implements CommandLineRunner {

    private final MoodRepository moodRepository;

    public InitService(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void run(String... args) {
        if (moodRepository.count() == 0) {
            Arrays.stream(MoodName.values()).forEach(m -> moodRepository.save(new Mood(m)));
        }
    }
}
