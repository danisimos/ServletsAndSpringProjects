package ru.itis.headhunter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.headhunter.models.VacancyResponse;

import java.util.List;

public interface VacancyResponseRepository extends JpaRepository<VacancyResponse, Long> {
    @Query(value = "select vr.id as \"id\", vr.account as \"account\", vr.vacancy as \"vacancy\"\n" +
            "from vacancy_response vr left join vacancy v on vr.vacancy = v.id\n" +
            "left join account a on vr.account = a.id\n" +
            "where a.id in (select account\n" +
            "               from vacancy_response\n" +
            "               group by account\n" +
            "               having count(account) = 1) and v.company in (select company\n" +
            "                                                            from vacancy\n" +
            "                                                            group by company\n" +
            "                                                            having count(company) = 1)",
            nativeQuery = true)
    List<VacancyResponse> complexQueryExample();
}
