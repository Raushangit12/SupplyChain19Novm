package com.example.supplychain19novm;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChain1 extends Application {
   public static final int height=600,width=700,upperLine=50;

    Pane bodyPane=new Pane();
     public Login login=new Login();
     productDetails productdetails=new productDetails();
     boolean loggedIn=false;
    Label loginLabel;
    Button orderButton;


     private GridPane hearBar(){
        GridPane gridPane=new GridPane();
        gridPane.setPrefSize(width,upperLine-5);
        gridPane.setAlignment(Pos.CENTER);
         gridPane.setHgap(5);
        TextField searchText=new TextField();
        searchText.setMinWidth(250);
        searchText.setPromptText("please search here");

        loginLabel=new Label("please Login!");
        Button loginButton=new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(loggedIn==false) {
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(loginPage());

                }
            }
        });

        Button searchButton=new Button("search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                bodyPane.getChildren().clear();
               // bodyPane.getChildren().add(productdetails.getAllProducts());
                String search=searchText.getText();
                bodyPane.getChildren().add(productdetails.getProductsByName(search));

            }
        });
        gridPane.add(searchText,0,0);
        gridPane.add(searchButton,1,0);
        gridPane.add(loginLabel,2,0);
        gridPane.add(loginButton,3,0);
        //gridPane.getChildren().remove(3,0);


        return gridPane;


     }
     private GridPane footerBar(){
        GridPane gridPane=new GridPane();
        orderButton=new Button("Buy Now");
        orderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //productdetails.getSelectedProduct();
                if(loggedIn==false){
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(loginPage());
                }
                else{
                    product Product=productdetails.getSelectedProduct();
                    if(Product!=null){
                        String email=loginLabel.getText();
                        email = email.substring(8);
                        System.out.println(email);
                        if(Order.placeSingleOrder(Product,email)){
                            System.out.println("Order Placed");
                        }
                        else{
                            System.out.println("Order Failed");
                        }

                    }
                    else{
                        System.out.println("please select a product");
                    }
                }
            }
        });
        gridPane.add(orderButton,0,0);
        gridPane.setMinWidth(width);
         gridPane.setTranslateY(height);
        return gridPane;
     }

    private  GridPane loginPage(){
        Label emailLabel=new Label("Email");
        Label passwordLabel=new Label("Password");
        Label messageLabel=new Label("i am message");
        TextField emailTextField=new TextField();
        emailTextField.setPromptText("please enter emailId");
        PasswordField passwordField=new PasswordField();
        passwordField.setPromptText("enter password");
        Button loginButton=new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email=emailTextField.getText();
                String password=passwordField.getText();
                if(login.customerLogin(email,password)){
                     loginLabel.setText("welcome: "+email);
                     bodyPane.getChildren().clear();
                     bodyPane.getChildren().add(productdetails.getAllProducts());
                     loggedIn=true;
                    messageLabel.setText("Login successful");
                }
                else{
                    messageLabel.setText("invalid user");
                }
              //  messageLabel.setText("email - "+email+"  && Pass->"+password);
            }
        });

        GridPane gridPane =new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        gridPane.setAlignment(Pos.CENTER);
        //gridPane.setStyle("-fx-background-color: #COC0C0;");
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(emailLabel,0,0);
        gridPane.add(emailTextField,1,0);
        gridPane.add(passwordLabel,0,1);
        gridPane.add(passwordField,1,1);
        gridPane.add(loginButton,0,3);
        gridPane.add(messageLabel,1,3);

        return gridPane;

    }

    Pane createContent(){
        Pane root=new Pane();
        root.setPrefSize(width,height+upperLine);
        bodyPane.setTranslateY(upperLine);
        bodyPane.setMinSize(width,height);
        //bodyPane.setStyle("-fx-background-color: #COC9C0;");
        bodyPane.getChildren().add(productdetails.getAllProducts());
        root.getChildren().addAll(hearBar(),bodyPane,footerBar());
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Supply Chain System!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}