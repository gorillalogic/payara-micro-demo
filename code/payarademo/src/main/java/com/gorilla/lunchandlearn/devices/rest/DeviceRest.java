package com.gorilla.lunchandlearn.devices.rest;

import com.gorilla.lunchandlearn.devices.model.Device;
import com.gorilla.lunchandlearn.devices.service.DeviceService;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Adam M. Gamboa G
 */
@RequestScoped
@Path("device")
public class DeviceRest {
    
    @Inject 
    private DeviceService deviceService;
    
    @GET
    public List<Device> getDevices(){
        return deviceService.getDevices();
    }
}
