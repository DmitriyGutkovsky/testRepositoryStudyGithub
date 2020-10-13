package com.noirix.controller;

import com.noirix.domain.Car;
import com.noirix.domain.User;
import com.noirix.repository.CarRepository;
import com.noirix.repository.UserRepository;
import com.noirix.repository.impl.CarRepositoryImpl;
import com.noirix.repository.impl.UserRepositoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class FrontController extends HttpServlet {
  public static final UserRepository userRepository = new UserRepositoryImpl();
  public static final CarRepository carRepository = new CarRepositoryImpl();

  public FrontController() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doRequest(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doRequest(req, resp);
  }

  private void doRequest(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    RequestDispatcher dispatcher = req.getRequestDispatcher("/bye");
    if (dispatcher != null) {
      System.out.println("Forward will be done!");
      req.setAttribute("userName", userRepository.findAll().stream().map(User::getName)
              .collect(Collectors.joining(",")));
      req.setAttribute("carModel", carRepository.findAll().stream().map(Car::getModel).collect(Collectors.joining(", ")));
      //check method findById()
      req.setAttribute("findCarById", carRepository.findById(1l));
      //check delete() method
      req.setAttribute("check_delete", carRepository.delete(carRepository.findById(4l)));

      //check save() method
      Car newCar = new Car();
      newCar.setModel("TEST");
      newCar.setCreationYear(2015);
      newCar.setColor("RED");
      newCar.setPrice(8500.0);
      newCar.setUser_id(1l);

      req.setAttribute("saved_object", carRepository.save(newCar));

      //check update() method

      newCar.setModel("Test2-test-Test");
      newCar.setId(10l);
      req.setAttribute("check_update", carRepository.update(newCar));

      dispatcher.forward(req, resp);
    }

    //        перенаправляет на страницу bye.jsp
    //        RequestDispatcher dispatcher = req.getRequestDispatcher("/bye");
    //        if (dispatcher != null) {
    //            System.out.println("Forward will be done!");
    //            dispatcher.forward(req, resp);
    //        }

    // 5 task: при нажатии кнопки на index.jsp  переходит на страницу FrontController
    // и выводит текст-приветствие

    //        resp.setContentType("text/html");
    //        PrintWriter out = resp.getWriter();
    //        out.println("<html><head>");
    //        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;
    // charset=utf-8\">");
    //        out.println("<title>Titke</title>");
    //        out.println("</head><body>");
    //        out.println("<h1> Hello, word from FrontController!!! </h1>");
    //        out.println("</body></html>");

    // 6 task перенаправляет на страницу main.jsp
    //        RequestDispatcher requestDispatcher =
    // req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
    //        requestDispatcher.forward(req, resp);
  }
}
