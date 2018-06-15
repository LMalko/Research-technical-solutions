package Model;

import java.util.ArrayList;


public class Product{

        private String name;
        private Float defaultPrice;
        private ProductCategory productCategory;
        private Integer ID;
        private static ArrayList<Product> productList = new ArrayList<Product>();

        private static Integer nextID = 1000;

        public Product(){
        }

        public Product(String name, Float defaultPrice, ProductCategory productCategory){
                this.name = name;
                this.defaultPrice = defaultPrice;
                this.productCategory = productCategory;
                this.ID = getNextID();

                productList.add(this);
        }

        @Override
        public String toString(){
                return  String.format("%s: ID: %s, NAME: %s, DEFAULT PRICE: %s, %s: ID: %s, TYPE: %s, EXPIRY DATE: %s\n",
                        this.getClass().getSimpleName(),
                        this.ID.toString(),
                        this.name,
                        this.defaultPrice.toString(),
                        this.productCategory.getClass().getSimpleName().toUpperCase(),
                        this.productCategory.getID(),
                        this.productCategory.getName(),
                        this.productCategory.getExpirationDateString());
        }

        public ArrayList<Product> getAllProducts(){
                return productList;
        }

        public ArrayList<Product> getAllProductsBy(ProductCategory productCategory){
                ArrayList<Product> result = new ArrayList<Product>();
                for (Product product : productList){
                        if(product.productCategory.equals(productCategory)){
                                result.add(product);
                        }
                }
                return result;
        }

        private Integer getNextID() {
                Integer newID = nextID;
                nextID++;
                return newID;
        }

        public String getName(){
                return this.name;
        }

        public Integer getProductID(){
                return this.ID;
        }

        public Float getDefaultPrice(){
                return this.defaultPrice;
        }

        public String getProductCategory(){
                return this.productCategory.getName();
        }

        public Integer getProductCategoryID(){
                return this.productCategory.getID();
        }

        public String getProductExpirationDate(){
                return this.productCategory.getExpirationDateString();
        }
}