/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;

/**
 *
 * @author david
 */
public class TestAppState extends BaseAppState{

    @Override
    protected void initialize(Application app) {
        System.out.println("Initialized ...");
    }

    @Override
    protected void cleanup(Application app) {
        System.out.println("cleaned up ...");
    }

    @Override
    protected void onEnable() {
        System.out.println("enabled ...");
    }

    @Override
    protected void onDisable() {
        System.out.println("disabled ...");
    }
    
}
