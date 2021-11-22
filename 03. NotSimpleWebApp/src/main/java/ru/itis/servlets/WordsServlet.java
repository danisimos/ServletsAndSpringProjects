package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.service.WordCounterService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/words")
public class WordsServlet extends HttpServlet {
    WordCounterService wordCounterService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        wordCounterService = applicationContext.getBean(WordCounterService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("folder");

        wordCounterService.saveAllFiles(path);

        Map<String, Map<String, Integer>> map = wordCounterService.getFilesTopWords();
        request.setAttribute("files", map);
        request.getRequestDispatcher("jsp/wordsTable.jsp").forward(request, response);
    }
}
