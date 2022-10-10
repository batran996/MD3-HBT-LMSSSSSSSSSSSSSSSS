package rikkei.academy.controller;

import rikkei.academy.model.BaiDoc;
import rikkei.academy.model.LoTrinh;
import rikkei.academy.model.Module;
import rikkei.academy.service.baidoc.BaiDocServiceIMPL;
import rikkei.academy.service.baidoc.IBaiDocService;
import rikkei.academy.service.lotrinh.ILoTrinhService;
import rikkei.academy.service.lotrinh.LoTrinhServiceIMPL;
import rikkei.academy.service.modul.IModuleService;
import rikkei.academy.service.modul.ModuleServiceIMPL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet( value = "/UserSl")
public class UserServlet extends HttpServlet {
    private ILoTrinhService loTrinhService = new LoTrinhServiceIMPL();
    private IModuleService moduleService = new ModuleServiceIMPL();
    private IBaiDocService baiDocService = new BaiDocServiceIMPL();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "account":
                showFormAccount(request, response);
                break;
            case "home":
                showHome(request, response);
                break;
            case "lotrinh":
                showListLoTrinh(request, response);
                break;
            case "showModule":
                showListModule(request, response);
                break;
            case "showBaiDoc":
                showListBaiDoc(request, response);
                break;
        }

    }

    private void showListBaiDoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idModule = Integer.parseInt(request.getParameter("id"));
        List<BaiDoc> listBaiDoc = baiDocService.findByBaiDoc(idModule);
        request.setAttribute("listBaiDoc", listBaiDoc);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/viewUser/listBaiDoc.jsp");
        dispatcher.forward(request, response);
    }

    private void showListModule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idLoTrinh = Integer.parseInt(request.getParameter("id"));
        List<Module> listModule = moduleService.findByLoTrinh(idLoTrinh);
        request.setAttribute("listModule", listModule);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/viewUser/listModule.jsp");
        dispatcher.forward(request, response);
    }

    private void showListLoTrinh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LoTrinh> loTrinhList = loTrinhService.findAll();
        request.setAttribute("listLoTrinh", loTrinhList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/viewUser/lotrinh.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void showHome(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/profile/profile-user.jsp");
        requestDispatcher.forward(request,response);
    }



    private void showFormAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/profile/account-profile.jsp");
        requestDispatcher.forward(request, response);

    }
}
