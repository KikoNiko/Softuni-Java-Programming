package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmls.JobInfoDto;
import softuni.exam.models.dto.xmls.JobsRootDto;
import softuni.exam.models.entity.Job;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.repository.JobRepository;
import softuni.exam.service.JobService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    private static final String FILE_IMPORT_PATH = "src/main/resources/files/xml/jobs.xml";
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.jobRepository.count() > 0;
    }

    @Override
    public String readJobsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_IMPORT_PATH)));
    }

    @Override
    public String importJobs() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        JobsRootDto jobsRootDto = this.xmlParser.parse(JobsRootDto.class, FILE_IMPORT_PATH);
        List<JobInfoDto> jobDtos = jobsRootDto.getJobsList();
        for (JobInfoDto dto : jobDtos) {
            if (!this.validationUtil.isValid(dto)) {
                sb.append("Invalid job\n");
                continue;
            }
            long companyId = dto.getCompanyId();
            if (companyId > 38) {
                companyId = ThreadLocalRandom.current().nextLong(1, 38);
            }
            Job job = this.modelMapper.map(dto, Job.class);
            job.setCompany(this.companyRepository.getById(companyId));
            this.jobRepository.saveAndFlush(job);
            sb.append(String.format("Successfully imported job %s%n",
                    job.getTitle()));
        }
        return sb.toString().trim();
    }

    @Override
    public String getBestJobs() {
        return this.jobRepository
                .findAllBySalaryGreaterThanAndHoursAWeekLessThanOrderBySalaryDesc(5000, 30)
                .stream()
                .map(j -> String.format("Job title %s\n" +
                        "-Salary: %.2f$\n" +
                        "--Hours a week: %.2fh.\n",
                        j.getTitle(),
                        j.getSalary(),
                        j.getHoursAWeek()))
                .collect(Collectors.joining());
    }
}
