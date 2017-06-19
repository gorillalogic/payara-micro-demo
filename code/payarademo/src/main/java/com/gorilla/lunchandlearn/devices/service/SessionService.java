package com.gorilla.lunchandlearn.devices.service;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.websocket.Session;

/**
 *
 * @author Adam M. Gamboa Gonzalez
 */
@Singleton
public class SessionService {
    
    
    private Set<Session> sessions;
    
    @PostConstruct
    public void init(){
        sessions = new HashSet<>();
    }
    
    
    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
    
    public Set<Session> getSessions(){
        return sessions;
    }
    
}
