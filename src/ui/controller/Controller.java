package ui.controller;

import domain.model.Person;
import domain.model.Product;
import domain.model.ShopService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private ShopService service;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        Properties properties = new Properties();

        Enumeration<String> parameterNames = context.getInitParameterNames();
        while (parameterNames.hasMoreElements()) {
            String propertyName = parameterNames.nextElement();
            properties.setProperty(propertyName, context.getInitParameter(propertyName));
        }

        try {
            service = new ShopService(properties);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination;
        String action = request.getParameter("action");
        if(action == null) action= "";
        switch(action){
            case "personOverview":
                destination = readPerson(request,response);
                break;
            case "signUp":
                destination = "signUp.jsp";
                break;
            case "register":
                destination = registerPerson(request,response);
                break;
            case "goToDeletePerson":
                destination = goToDeletePerson(request,response);
                break;
            case "deletePerson":
                destination = deletePerson(request,response);
                break;

            /*PRODUCT*/
            case "productOverview":
                destination = readProduct(request, response);
                break;
            case "createProduct":
                destination = "addProduct.jsp";
                break;
            case "addProduct":
                destination = addProduct(request,response);
                break;
            case "goToDeleteProduct":
                destination = goToDeleteProduct(request,response);
                break;
            case "deleteProduct":
                destination = deleteProduct(request,response);
                break;
            case "goToUpdateProduct":
                destination = goToUpdateProduct(request,request);
                break;
            case "updateProduct":
                destination = updateProduct(request,response);
                break;

            /****COOKIES****/
            case "setQuote":
                destination = setQuote(request,response);
                break;

            default:
                destination ="index.jsp";
                break;
        }
        RequestDispatcher rd = request.getRequestDispatcher(destination);
        rd.forward(request, response);
    }

    /*******************
     *
     COOKIES
     *
     ******************/

    private String setQuote(HttpServletRequest request, HttpServletResponse response) {
        String selectedAnswer = request.getParameter("selected");
        Cookie cookie = new Cookie("answer", selectedAnswer );
        response.addCookie(cookie);
        return getQuote(selectedAnswer);
    }

    private String getQuote(String selectedAnswer) {
        if(selectedAnswer.equalsIgnoreCase("yes")){
            return "The best preparation for tomorrow is doing your best today.";
        }else{
            return "index.jsp";
        }
    }

    /*******************
     *
     USER METHODS
     *
     ******************/

    private String readPerson(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("allPeople", service.getPersons());
        return "personoverview.jsp";
    }

    private String registerPerson(HttpServletRequest request, HttpServletResponse response) {
        List<String> error = new ArrayList<>();
        Person person = new Person();

        processUserId(person, request, error);
        processFirstName(person, request, error);
        processLastName(person, request, error);
        processEmail(person, request, error);
        processPassword(person, request, error);

        if(error.size()==0){
            service.addPerson(person);
            return readPerson(request, response);
        }else{
            request.setAttribute("errors", error);
            return "signUp.jsp";
        }
    }

    private String goToDeletePerson(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("uid", request.getParameter("id"));
        request.setAttribute("user", service.getPerson(request.getParameter("id")));
        return "deletePerson.jsp";
    }

    private String deletePerson(HttpServletRequest request, HttpServletResponse response) {
        service.deletePerson(request.getParameter("id"));
        return readPerson(request,response);
    }

    /*******************
     *
        PRODUCT METHODS
     *
     ******************/

    private String readProduct(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("allProducts", service.getProducts());
        return "productoverview.jsp";
    }

    private String addProduct(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList();
        Product product = new Product();

        verwerkProductID(product,request,errors);
        verwerkName(product, request, errors);
        verwerkDescription(product, request, errors);
        verwerkPrice(product, request, errors);
        if(errors.size()==0){
            service.addProduct(product);
            return readProduct(request, response);
        }else{
            request.setAttribute("errors", errors);
            return "addProduct.jsp";
        }
    }

    private String goToDeleteProduct(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("pid", request.getParameter("pid"));
        request.setAttribute("product", service.getProduct(request.getParameter("pid")));
        return "deleteProduct.jsp";
    }

    private String deleteProduct(HttpServletRequest request, HttpServletResponse response){
        service.deleteProduct(request.getParameter("id"));
        return readProduct(request,response);
    }

    private String goToUpdateProduct(HttpServletRequest request, HttpServletRequest request1) {
        request.setAttribute("proid", request.getParameter("upid"));
        request.setAttribute("product", service.getProduct(request.getParameter("upid")));
        return "updateProduct.jsp";
    }

    private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
        String proid = request.getParameter("productId");
        Product product = service.getProduct(proid);
        List<String> errors = new ArrayList<>();

        verwerkName(product, request, errors);
        verwerkDescription(product,request,errors);
        verwerkPrice(product,request,errors);

        if(errors.size()<=0){
            service.updateProduct(product);
            return readProduct(request,response);
        }else{
            request.setAttribute("errors", errors);
            return "updateProduct.jsp";
        }
    }

    /***********************
     *
     * Helper Methods Person
     *
     *********************/

    private void processUserId(Person person, HttpServletRequest request, List<String> error) {
        String uid = request.getParameter("userid");
        try{
            request.setAttribute("uidSave", uid);
            person.setUserid(uid);
        }catch(Exception e){
            error.add(e.getMessage());
        }
    }

    private void processFirstName(Person person, HttpServletRequest request, List<String> error) {
        String naam = request.getParameter("firstName");
        try{
            request.setAttribute("nameSave", naam);
            person.setFirstName(naam);
        }catch(Exception e){
            error.add(e.getMessage());
        }
    }

    private void processLastName(Person person, HttpServletRequest request, List<String> error) {
        String fnaam = request.getParameter("lastName");
        try{
            request.setAttribute("fnameSave", fnaam);
            person.setLastName(fnaam);
        }catch(Exception e){
            error.add(e.getMessage());

        }

    }

    private void processEmail(Person person, HttpServletRequest request, List<String> error) {
        String email = request.getParameter("email");
        try{
            request.setAttribute("emailSave", email);
            person.setEmail(email);
        }catch(Exception e){
            error.add(e.getMessage());
        }

    }

    private void processPassword(Person person, HttpServletRequest request, List<String> error) {
        String pass = request.getParameter("password");
        try{
            request.setAttribute("passSave", pass);
            person.setPassword(pass);
        }catch(Exception e){
            error.add(e.getMessage());
        }

    }

    /***********************
     *
     * Helper Methods Product
     *
     *********************/

    private void verwerkProductID(Product product, HttpServletRequest request, List<String> fouten) {
        String pid = request.getParameter("productId");
        try{
            request.setAttribute("idSave", pid);
            product.setProductid(pid);
        }catch(Exception e){
            fouten.add(e.getMessage());
        }
    }
    private void verwerkName(Product product, HttpServletRequest request, List<String> fouten) {
        String name = request.getParameter("name");
        try{
            request.setAttribute("nameSave", name);
            product.setName(name);
        }catch(Exception e){
            fouten.add(e.getMessage());
        }
    }

    private void verwerkDescription(Product product, HttpServletRequest request, List<String> fouten) {
        String desc = request.getParameter("description");
        try{
            request.setAttribute("descSave", desc);
            product.setDescription(desc);;
        }catch(Exception e){
            fouten.add(e.getMessage());
        }
    }

    private void verwerkPrice(Product product, HttpServletRequest request, List<String> fouten) {
        try{
            String price = request.getParameter("price");
            request.setAttribute("priceSave", price);
            product.setPrice(price);
        }catch(Exception e){
            fouten.add(e.getMessage());
        }
    }


}
