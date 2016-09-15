package br.com.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.InetAddress;

/**
 * Created by lucas on 14/09/16.
 */
public class About {
    @FXML
    private Label LBip;

    @FXML
    private void initialize(){
        try {
            LBip.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
        }
    }
}
