package com.example.huynh.weatherapp;

import com.example.huynh.weatherapp.Observer.Observer;
import com.example.huynh.weatherapp.Observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class MyCityList implements Subject {

    public static MyCityList instance = null;
    ArrayList<String> mList;
    private ArrayList<Observer> observers;
    private final Object MUTEX= new Object();

    public MyCityList() {
        mList = new ArrayList<String>();
        observers = new ArrayList<Observer>();
    }

    public static MyCityList getInstance() {
        if (instance == null) {
            instance = new MyCityList();
        }
        return instance;
    }

    @Override
    public void register(Observer obj) {
        synchronized (MUTEX) {
            if (!observers.contains(obj)) observers.add(obj);
        }
    }

    @Override
    public void unregister(Observer obj) {
        synchronized (MUTEX) {
            if (observers.contains(obj)) observers.remove(obj);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal;
        synchronized (MUTEX) {
            observersLocal = new ArrayList<>(this.observers);
        }
        for (Observer obj : observersLocal) {
            obj.update();
        }
    }

    public void AddNewCity(String name) {
        mList.add(name);
        notifyObservers();
    }

    public void RemoveCity(String name) {
        if (mList.contains(name)) mList.remove(name);
        notifyObservers();
    }

    public ArrayList<String> getmList() {
        return mList;
    }

    public boolean CheckCityName(String name) {
        return mList.contains(name);
    }
}
