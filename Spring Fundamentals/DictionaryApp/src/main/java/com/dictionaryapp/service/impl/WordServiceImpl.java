package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.service.WordService;
import com.dictionaryapp.util.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {
    private final LanguageRepository languageRepository;
    private final WordRepository wordRepository;
    private final UserSession userSession;
    private final ModelMapper modelMapper;

    public WordServiceImpl(LanguageRepository languageRepository,
                           WordRepository wordRepository,
                           UserSession userSession,
                           ModelMapper modelMapper) {
        this.languageRepository = languageRepository;
        this.wordRepository = wordRepository;
        this.userSession = userSession;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(WordAddDTO dto) {
        //TODO
    }
}
