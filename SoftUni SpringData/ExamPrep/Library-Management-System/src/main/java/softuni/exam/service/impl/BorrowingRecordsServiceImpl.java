package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BorrowingRecordDto;
import softuni.exam.models.dto.BorrowingRecordsRootDto;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.models.entity.enums.GenreType;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {
    private static final String FILE_PATH = "src/main/resources/files/xml/borrowing-records.xml";
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final LibraryMemberRepository libraryMemberRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importBorrowingRecords() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        BorrowingRecordsRootDto rootDto = this.xmlParser.parse(BorrowingRecordsRootDto.class, FILE_PATH);
        List<BorrowingRecordDto> borrowingRecords = rootDto.getBorrowingRecords();
        for (BorrowingRecordDto dto : borrowingRecords) {
            Optional<Book> optionalBook = this.bookRepository.findByTitle(dto.getBook().getTitle());
            Optional<LibraryMember> optionalMember = this.libraryMemberRepository.findById(dto.getMember().getId());

            if (!validationUtil.isValid(dto) || optionalBook.isEmpty() || optionalMember.isEmpty()) {
                sb.append("Invalid borrowing record").append(System.lineSeparator());
                continue;
            }
            BorrowingRecord borrowingRecord = this.modelMapper.map(dto, BorrowingRecord.class);
            borrowingRecord.setBook(optionalBook.get());
            borrowingRecord.setMember(optionalMember.get());
            this.borrowingRecordRepository.saveAndFlush(borrowingRecord);
            sb.append(String.format("Successfully imported borrowing record %s - %s%n",
                    borrowingRecord.getBook().getTitle(), borrowingRecord.getBorrowDate().toString()));
        }
        return sb.toString().trim();
    }

    @Override
    public String exportBorrowingRecords() {
             return this.borrowingRecordRepository
                     .findAllByBorrowDateBeforeOrderByBorrowDateDesc(LocalDate.parse("2021-09-10"))
                     .stream()
                     .filter(r -> r.getBook().getGenre().equals(GenreType.SCIENCE_FICTION))
                     .map(r -> String.format("Book title: %s%n" +
                                "*Book author: %s%n" +
                                "**Date borrowed: %s%n" +
                                "***Borrowed by: %s %s%n",
                        r.getBook().getTitle(),
                        r.getBook().getAuthor(),
                        r.getBorrowDate().toString(),
                        r.getMember().getFirstName(),
                        r.getMember().getLastName()))
                .collect(Collectors.joining());
    }
}
