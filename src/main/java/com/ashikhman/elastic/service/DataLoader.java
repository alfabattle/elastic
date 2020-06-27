package com.ashikhman.elastic.service;

import com.ashikhman.elastic.config.AppProperties;
import com.ashikhman.elastic.document.LoanDocument;
import com.ashikhman.elastic.document.PersonDocument;
import com.ashikhman.elastic.repository.LoanRepository;
import com.ashikhman.elastic.repository.PersonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Log4j2
public class DataLoader {

    private final PersonRepository personRepository;
    private final LoanRepository loanRepository;
    private final AppProperties properties;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void init() throws IOException {
        personRepository.deleteAll();

        var personsFile = new File(properties.getPersonsFile());
        var data = objectMapper.readValue(personsFile, new TypeReference<Map<String, List<Map<String, Object>>>>() {
        });

        int i = 0;
        for (var item : data.get("persons")) {
            var person = new PersonDocument()
                    .setId(item.get("ID").toString())
                    .setDocId(item.get("DocId").toString())
                    .setFio(item.get("FIO").toString())
                    .setBirthday(getBirthday(item.get("Birthday").toString()))
                    .setSalary(getSalary(item.get("Salary").toString()))
                    .setGender(item.get("Gender").toString());

            personRepository.save(person);

            ++i;
        }

        log.error("{} persons persisted", i);

        addLoans();
    }

    private void addLoans() throws IOException {
        loanRepository.deleteAll();

        var personsFile = new File(properties.getLoansFile());
        var data = objectMapper.readValue(personsFile, new TypeReference<Map<String, List<Map<String, Object>>>>() {
        });

        int i = 0;
        for (var item : data.get("loans")) {
            var person = personRepository.findById(item.get("PersonId").toString());
            if (!person.isPresent()) {
                log.error("Person `{}` not found", item.get("PersonId"));
                continue;
            }

            var loan = new LoanDocument()
                    .setId(item.get("Loan").toString())
                    .setDocId(person.get().getDocId())
                    .setAmount(Math.round(Double.valueOf(item.get("Amount").toString())))
                    .setPeriod(Integer.valueOf(item.get("Amount").toString()) * 12)
                    .setStartDate(getBirthday(item.get("StartDate").toString()))
                    ;

            ++i;
        }

        log.error("{} loans persisted", i);
    }

    private Long getSalary(String fileSalary) {
        return Math.round(Double.valueOf(fileSalary));
    }

    private String getBirthday(String fileBirthday) {
        Pattern pattern = Pattern.compile("^([0-9]+)/([0-9]+)/([0-9]+)$");
        Matcher matcher = pattern.matcher(fileBirthday);

        if (matcher.find()) {
            return matcher.group(2) + "-" + matcher.group(0) + "-" + matcher.group(1);
        } else {
            log.error("Not matchig value found for birthday {}", fileBirthday);
        }

        return "";
    }
}
