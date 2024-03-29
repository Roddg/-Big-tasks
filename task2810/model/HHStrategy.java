package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class HHStrategy implements Strategy{

    private static final String  URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) throws IOException {

        List<Vacancy> vacancies = new ArrayList<>();

        try {
            int pageNumber = 0;
            Document document = getDocument(searchString, pageNumber);

            while (true) {
                Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");

                if (elements.size() == 0) {
                    break;
                }

                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setSiteName(URL_FORMAT);

                    String salary = element.getElementsByAttributeValueContaining("data-qa", "compensation").text();
                    vacancy.setSalary(salary.length() == 0 ? "" : salary);

                    vacancy.setTitle(element.getElementsByAttributeValueContaining("data-qa", "-title").text());
                    vacancy.setCity(element.getElementsByAttributeValueContaining("data-qa", "address").text());
                    vacancy.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                    vacancy.setUrl(element.getElementsByAttributeValueContaining("data-qa", "-title").attr("href"));

                    vacancies.add(vacancy);
                }

                pageNumber++;
                document = getDocument(searchString, pageNumber);
            }

        } catch (IOException ignore) {
        }

        return vacancies;

    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36").referrer("no-referrer-when-downgrade").get();
    }
}
