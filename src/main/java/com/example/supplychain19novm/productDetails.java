package com.example.supplychain19novm;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class productDetails {

    public TableView<product> productTable;


    public  Pane getAllProducts(){
        TableColumn id=new TableColumn("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name=new TableColumn("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price=new TableColumn("price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        final ObservableList<product> data= FXCollections.observableArrayList();
        data.add(new product(1,"lenovo",8543));
        data.add(new product(2,"mac",89764));

         ObservableList<product> items=product.getAllProducts();

        productTable=new TableView<>();

        productTable.setItems(items);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SupplyChain1.width, SupplyChain1.height);

        Pane tablePane=new Pane();
        tablePane.setMinSize(SupplyChain1.width, SupplyChain1.height);
        tablePane.getChildren().add(productTable);
        return tablePane;
    }

  public Pane getProductsByName(String searchName){
      TableColumn id=new TableColumn("id");
      id.setCellValueFactory(new PropertyValueFactory<>("id"));

      TableColumn name=new TableColumn("name");
      name.setCellValueFactory(new PropertyValueFactory<>("name"));

      TableColumn price=new TableColumn("price");
      price.setCellValueFactory(new PropertyValueFactory<>("price"));



      ObservableList<product> items=product.getProductsByName(searchName);
      productTable=new TableView<>();

      productTable.setItems(items);
      productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
      productTable.getColumns().addAll(id,name,price);
      productTable.setMinSize(SupplyChain1.width, SupplyChain1.height);

      Pane tablePane=new Pane();
      tablePane.setMinSize(SupplyChain1.width, SupplyChain1.height);
      tablePane.getChildren().add(productTable);
      return tablePane;
  }
    public product getSelectedProduct(){
        if(productTable==null)
        {
            System.out.println("Table Not Found");
            return null;
        }
        if(productTable.getSelectionModel().getSelectedIndex()!=-1){
            product selectedProduct=productTable.getSelectionModel().getSelectedItem();
            System.out.println(selectedProduct.getId()+" "+
                    selectedProduct.getName()+" "+selectedProduct.getPrice()
                    );
            return selectedProduct;
        }
        else{
            System.out.println("Nothing selected");
            return null;
        }
    }

  }

