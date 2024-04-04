package com.restaurant.entity;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private final List<MenuItem> menuItemList;

    Menu(){
        menuItemList = new ArrayList<>();
    }

    public void addItem(MenuItem item){
        menuItemList.add(item);
    }

    public void removeItem(MenuItem item){
        menuItemList.remove(item);
    }

    public List<MenuItem> getMenuItem(){
        return  menuItemList;
    }

}
