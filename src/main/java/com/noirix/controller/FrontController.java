package com.noirix.controller;

import com.google.gson.Gson;
import com.noirix.controller.command.Commands;
import com.noirix.domain.Car;
import com.noirix.domain.User;
import com.noirix.repository.CarRepository;
import com.noirix.repository.UserRepository;
import com.noirix.repository.impl.CarRepositoryImpl;
import com.noirix.repository.impl.UserRepositoryImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.stream.Collectors;

@Controller
//@RestController (for Spring Rest and Spring MVC)
public class FrontController extends HttpServlet {
  public static final UserRepository userRepository = new UserRepositoryImpl();
  public static final CarRepository carRepository = new CarRepositoryImpl();

  public FrontController() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    processGetRequests(req, resp);
    //    doRequest(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    processPostRequests(req, resp);
  }

  //    private void doRequest(HttpServletRequest req, HttpServletResponse resp)
  //        throws ServletException, IOException {
  //
  //      RequestDispatcher dispatcher = req.getRequestDispatcher("/bye");
  //      if (dispatcher != null) {
  //        System.out.println("Forward will be done!");
  //        req.setAttribute("userName", userRepository.findAll().stream().map(User::getName)
  //                .collect(Collectors.joining(",")));
  //        req.setAttribute("carModel",
  //   carRepository.findAll().stream().map(Car::getModel).collect(Collectors.joining(", ")));
  //        //check method findById()
  //        req.setAttribute("findCarById", carRepository.findById(1l));
  //        //check delete() method
  //        req.setAttribute("check_delete", carRepository.delete(carRepository.findById(4l)));
  //
  //        //check save() method
  //        Car newCar = new Car();
  //        newCar.setModel("TEST");
  //        newCar.setCreationYear(2015);
  //        newCar.setColor("RED");
  //        newCar.setPrice(8500.0);
  //        newCar.setUser_id(1l);
  //
  //        req.setAttribute("saved_object", carRepository.save(newCar));
  //
  //        //check update() method
  //
  //        newCar.setModel("Test2-GUT-Test");
  //        newCar.setId(10l);
  //        req.setAttribute("check_update", carRepository.update(newCar));
  //
  //        dispatcher.forward(req, resp);
  //      }
  //
  //    //        перенаправляет на страницу bye.jsp
  //    //        RequestDispatcher dispatcher = req.getRequestDispatcher("/bye");
  //    //        if (dispatcher != null) {
  //    //            System.out.println("Forward will be done!");
  //    //            dispatcher.forward(req, resp);
  //    //        }
  //
  //    // 5 task: при нажатии кнопки на index.jsp  переходит на страницу FrontController
  //    // и выводит текст-приветствие
  //
  //    //        resp.setContentType("text/html");
  //    //        PrintWriter out = resp.getWriter();
  //    //        out.println("<html><head>");
  //    //        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;
  //    // charset=utf-8\">");
  //    //        out.println("<title>Titke</title>");
  //    //        out.println("</head><body>");
  //    //        out.println("<h1> Hello, word from FrontController!!! </h1>");
  //    //        out.println("</body></html>");
  //
  //    // 6 task перенаправляет на страницу main.jsp
  //    //        RequestDispatcher requestDispatcher =
  //    // req.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
  //    //        requestDispatcher.forward(req, resp);
  //    }

  private void processGetRequests(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Commands commandName = Commands.findByCommandName(req.getParameter("command"));
    try {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/bye");
      if (dispatcher != null) {
        resolveGetRequestCommands(req, commandName);
        dispatcher.forward(req, resp);
      }
    } catch (Exception e) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/error");
      if (dispatcher != null) {
        req.setAttribute("trace", e.getMessage());
        dispatcher.forward(req, resp);
      }
    }
  }

  private void resolveGetRequestCommands(HttpServletRequest req, Commands commandName) {

    // http://localhost:8080/testTomcat/FrontController?command=findAll&page=0&limit=10 (add offset
    // to query)
    switch (commandName) {
        // http://localhost:8080/testTomcat/FrontController?command=findAll
      case FIND_ALL:
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        req.setAttribute("users", userRepository.findAll());
        break;
      case FIND_BY_ID:
        String id = req.getParameter("id");
        long userId = Long.parseLong(id);
        // http://localhost:8080/testTomcat/FrontController?command=findById&id=5
        req.setAttribute("users", Collections.singletonList(userRepository.findById(userId)));
        req.setAttribute("singleUser", userRepository.findById(userId));
        break;

      case CAR_FIND_ALL:
        // http://localhost:8080/testTomcat/FrontController?command=carFindAll
        String carPage = req.getParameter("page");
        String carLimit = req.getParameter("limit");
        req.setAttribute("cars", carRepository.findAll());
        break;
      case CAR_FIND_BY_ID:
        String carIdString = req.getParameter("id");
        long carID = Long.parseLong(carIdString);
        // http://localhost:8080/testTomcat/FrontController?command=carFindById&id=1
        req.setAttribute("car", Collections.singletonList(carRepository.findById(carID)));
        break;
      default:
        break;
    }
  }

  private void processPostRequests(HttpServletRequest req, HttpServletResponse resp) {
    Commands commandName = Commands.findByCommandName(req.getParameter("command"));
    try {
      switch (commandName) {
        case CREATE:
          String body = IOUtils.toString(req.getInputStream(), Charset.defaultCharset());
          User user = new Gson().fromJson(body, User.class);
          req.setAttribute("users", Collections.singletonList(userRepository.save(user)));
          break;
        case UPDATE:
          String updateBody = IOUtils.toString(req.getInputStream(), Charset.defaultCharset());
          User updateUser = new Gson().fromJson(updateBody, User.class);
          req.setAttribute("users", Collections.singletonList(userRepository.update(updateUser)));
          break;
        case DELETE:
          String id = req.getParameter("id");
          long userId = Long.parseLong(id);
          userRepository.delete(userRepository.findById(userId));
          req.setAttribute("users", userRepository.findAll());
          break;

        case CAR_CREATE:
          // http://localhost:8080/testTomcat/FrontController?command=carCreate
          String car_body = IOUtils.toString(req.getInputStream(), Charset.defaultCharset());
          Car car = new Gson().fromJson(car_body, Car.class);
          req.setAttribute("cars", Collections.singletonList(carRepository.save(car)));
          break;
        case CAR_UPDATE:
          // http://localhost:8080/testTomcat/FrontController?command=carUpdate
          String carUpdateBody = IOUtils.toString(req.getInputStream(), Charset.defaultCharset());
          Car carUpdateUser = new Gson().fromJson(carUpdateBody, Car.class);
          req.setAttribute("cars", Collections.singletonList(carRepository.update(carUpdateUser)));
          break;
        case CAR_DELETE:
          // http://localhost:8080/testTomcat/FrontController?command=carDelete&id=24
          String carIdString = req.getParameter("id");
          long carId = Long.parseLong(carIdString);
          carRepository.delete(carRepository.findById(carId));
          req.setAttribute("cars", carRepository.findAll());
          break;
        default:
          break;
      }
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
