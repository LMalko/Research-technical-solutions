package Model;

import Interface.Iterator;

import java.util.ArrayList;

public class Basket{

        private Iterator iterator;
        private ArrayList<Product> productList = new ArrayList<Product>();

        public Iterator getIterator() {
                iterator = new ProductIterator();
                return iterator;
        }

        public void addProduct(Product product){
                productList.add(product);
        }

        public Boolean removeProduct(Product product){
                return productList.remove(product);
        }

        public ArrayList<Product> getBasketProductList(){
                return productList;
        }

        private class ProductIterator implements Iterator{

                int index;

                @Override
                public boolean hasNext() {
                        if(index < productList.size()){
                                return true;
                        }
                        return false;
                }

                @Override
                public Object next() {
                        if(this.hasNext()){
                                return productList.get(index++);
                        }
                        return null;
                }
        }
}