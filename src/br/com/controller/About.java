package br.com.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by lucas on 14/09/16.
 */
public class About {
    @FXML
    private Label LBip;
    @FXML
    private Label LBmac;

    @FXML
    private void initialize() throws SocketException, UnknownHostException {
        try {
            LBip.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
        }

        String macAddr = null;
        try {
            Enumeration<NetworkInterface> eth = NetworkInterface
                    .getNetworkInterfaces();
            while (eth.hasMoreElements()) {
                NetworkInterface eth0 = eth.nextElement();
                byte[] mac = eth0.getHardwareAddress();
                StringBuilder sb = new StringBuilder();
                if (mac != null) {
                    for (int i = 0; i < mac.length; i++) {
                        String aux = String.format("%02X%s", mac[i],
                                (i < mac.length - 1) ? "-" : "");
                        sb.append(aux);
                    }
                    if (sb.toString().length() <= 18) {
                        macAddr = sb.toString();
                        LBmac.setText(macAddr);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
