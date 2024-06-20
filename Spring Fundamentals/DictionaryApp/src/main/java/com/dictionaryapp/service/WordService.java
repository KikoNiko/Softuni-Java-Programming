package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.WordAddDTO;
import com.dictionaryapp.model.entity.LanguageEnum;
import com.dictionaryapp.model.entity.Word;

import java.util.List;

public interface WordService {

    void add(WordAddDTO dto, long id);

    List<Word> findAllByLanguage(LanguageEnum languageEnum);

    long countAllWords();

    void removeById(Long id);
}
