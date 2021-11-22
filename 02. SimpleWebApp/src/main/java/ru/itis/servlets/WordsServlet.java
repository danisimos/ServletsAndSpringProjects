package ru.itis.servlets;

import ru.itis.service.WordCounter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WordsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("folder");
        response.setCharacterEncoding("utf-8");

        WordCounter wordCounter = new WordCounter();
        String htmlResponse = wordCounter.getHtmlResonse(path);

        response.setContentType("text/html");
        response.getWriter().println(htmlResponse);
    }
}
