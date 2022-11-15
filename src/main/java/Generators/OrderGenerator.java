package Generators;

import Models.Colors;
import Models.Order;

import java.util.List;

public class OrderGenerator {
    public static Order getOrderWithOneColor(String color){
        Order order = new Order();
        order.setColor(List.of(color));
        return order;
    }
    public static Order getOrderWithTwoColors(){
        Order order = new Order();
        order.setColor(List.of(Colors.BLACK, Colors.GRAY));
        return order;
    }
    public static Order getOrderWithoutColor(){
        return new Order();
    }

    public static Order getOrderSomeDataWithTwoColors(){
        return new Order("Евгений",
                         "Лукашин",
                          "3-я улица строителей, 25, кв. 12",
                      "Петрозаводская",
                           "8 800 999 88 77",
                         "4",
                      "2022-12-05",
                        "Звоните предварительно",
                                 List.of(Colors.BLACK, Colors.GRAY));
    }
}
