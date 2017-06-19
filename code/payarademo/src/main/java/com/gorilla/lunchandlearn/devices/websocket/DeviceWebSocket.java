package com.gorilla.lunchandlearn.devices.websocket;

import com.gorilla.lunchandlearn.devices.model.Device;
import com.gorilla.lunchandlearn.devices.service.DeviceService;
import com.gorilla.lunchandlearn.devices.service.SessionService;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author adamgamboa
 */
@ApplicationScoped
@ServerEndpoint("/actions")
public class DeviceWebSocket {
    
    @Inject 
    private DeviceService deviceService;
    
    @Inject
    private SessionService sessionService;
    
    @OnOpen
    public void open(Session session){
        sessionService.addSession(session);  
    }
    
    @OnClose
    public void close(Session session){
        sessionService.removeSession(session);
    }
    
    @OnError
    public void onError(Throwable error){
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, error);
    }
    
    @OnMessage
    public void handleMessage(String message, Session session){
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();
            
            if ("add".equals(jsonMessage.getString("action"))) {
                Device device = new Device();
                device.setName(jsonMessage.getString("name"));
                device.setDescription(jsonMessage.getString("description"));
                device.setType(jsonMessage.getString("type"));
                device.setStatus("Off");
                deviceService.addDevice(device);
            }

            if ("remove".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                deviceService.removeDevice(id);
            }

            if ("toggle".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                deviceService.toggleDevice(id);
            }
            if("query".equals(jsonMessage.getString("action"))){
                deviceService.sendDevicesToSession(session);
            }
        }
    }
    
}
