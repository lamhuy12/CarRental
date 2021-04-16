/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvl.cart;

import huyvl.car.CarDetailDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HUYVU
 */
public class CartObject implements Serializable {

    private Map<String, CarDetailDTO> cars;   

    public Map<String, CarDetailDTO> getCars() {
        return cars;
    }

    public void setCars(Map<String, CarDetailDTO> cars) {
        this.cars = cars;
    }


    

    public void addCarToCart(CarDetailDTO dto) {
        if (this.cars == null) {
            this.cars = new HashMap<>();
        }

        if (this.cars.containsKey(dto.getCarID())) {
            int newQuantity = this.cars.get(dto.getCarID()).getQuantity() + 1;
            this.cars.get(dto.getCarID()).setQuantity(newQuantity);
        } else {
            this.cars.put(dto.getCarID(), dto);
        }
    }

    public void removeCarFromCart(String carID) {
        if (this.cars == null) {
            return;
        }

        if (this.cars.containsKey(carID)) {
            this.cars.remove(carID);
            if (this.cars.isEmpty()) {
                this.cars = null;
            }
        }
    }

    public float getTotal() throws Exception {
        float result = 0;

        for (CarDetailDTO dto : this.cars.values()) {
            result += dto.getQuantity() * dto.getPrice();
        }
        return result;
    }

    public void updateCart(String carID, int quantity) throws Exception {
        if (this.cars.containsKey(carID)) {
            this.cars.get(carID).setQuantity(quantity);
        }
    }

}
