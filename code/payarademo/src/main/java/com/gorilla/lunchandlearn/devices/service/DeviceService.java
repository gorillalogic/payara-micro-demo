package com.gorilla.lunchandlearn.devices.service;

import com.gorilla.lunchandlearn.devices.model.Device;
//import com.hazelcast.core.HazelcastInstance;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

/**
 *
 * @author Adam M. Gamboa Gonzalez
 */
@ApplicationScoped
public class DeviceService {
    
    
    @Inject
    private SessionService sessionService;
    
    //@Resource(name = "payara/Hazelcast") 
    //private HazelcastInstance hcInstance;
       
    
    private Set<Device> devices = new HashSet<>();
    
    @PostConstruct
    public void init(){
        //devices = hcInstance.getSet("devices");
        devices = new HashSet<>();
    }
    
    
    public List<Device> getDevices() {
        return new ArrayList<>(devices);
    }
    
    
    public void addDevice(Device device) {
        device.setId(new Random().nextInt());
        devices.add(device);
        JsonObject addMessage = createAddMessage(device);
        sendToAllConnectedSessions(addMessage);
    }

    public void removeDevice(int id) {
        Device device = getDeviceById(id);
        if (device != null) {
            devices.remove(device);
            JsonProvider provider = JsonProvider.provider();
            JsonObject removeMessage = provider.createObjectBuilder()
                    .add("action", "remove")
                    .add("id", id)
                    .build();
            sendToAllConnectedSessions(removeMessage);
        }
    }

    public void toggleDevice(int id) {
        JsonProvider provider = JsonProvider.provider();
        Device device = getDeviceById(id);
        if (device != null) {
            if ("On".equals(device.getStatus())) {
                device.setStatus("Off");
            } else {
                device.setStatus("On");
            }
            JsonObject updateDevMessage = provider.createObjectBuilder()
                    .add("action", "toggle")
                    .add("id", device.getId())
                    .add("status", device.getStatus())
                    .build();
            sendToAllConnectedSessions(updateDevMessage);
        }
    }

    
    
    private Device getDeviceById(int id) {
        for (Device device : devices) {
            if (device.getId() == id) {
                return device;
            }
        }
        return null;
    }

    private JsonObject createAddMessage(Device device) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", device.getId())
                .add("name", device.getName())
                .add("type", device.getType())
                .add("status", device.getStatus())
                .add("description", device.getDescription())
                .build();
        return addMessage;
    }

    private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessionService.getSessions()) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessionService.getSessions().remove(session);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendDevicesToSession(Session session){
        for (Device device : this.getDevices()) {
            JsonObject addMessage = createAddMessage(device);
            sendToSession(session, addMessage);
        }
    }
    
    
}
