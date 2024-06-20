package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class WordServiceImpl implements WordService {
    private final UserRepository userRepository;
    private final WordRepository wordRepository;
    private final LanguageRepository languageRepository;
    private final ModelMapper modelMapper;

    public WordServiceImpl(UserRepository userRepository,
                           WordRepository wordRepository,
                           LanguageRepository languageRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(WordAddDTO dto, long id) {
        Word mappedWord = modelMapper.map(dto, Word.class);
        User userById = userRepository.findById(id).orElse(null);
        Language language = languageRepository.findByName(dto.getLanguage()).orElseThrow();

        mappedWord.setLanguage(language);
        mappedWord.setAddedBy(userById);

        wordRepository.save(mappedWord);
        userById.getAddedWords().add(mappedWord);
        userRepository.save(userById);
    }

    @Override
    public List<Word> findAllByLanguage(LanguageEnum languageName) {
        Language language = languageRepository.findByName(languageName).orElse(null);
        return wordRepository.findAllByLanguage(language);
    }

    @Override
    public long countAllWords() {
        return wordRepository.count();
    }

    @Override
    public void removeById(Long id) {
        Word word = wordRepository.findById(id).orElse(null);
        User addedBy = word.getAddedBy();
        List<Word> addedWords = addedBy.getAddedWords();
        addedWords.remove(word);
        wordRepository.deleteById(word.getId());
        userRepository.save(addedBy);
    }
}
