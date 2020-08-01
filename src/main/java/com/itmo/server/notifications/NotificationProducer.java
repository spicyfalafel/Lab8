package com.itmo.server.notifications;

public class NotificationProducer {
  private List<SocketChannel> channels;
  private[] data;
  
  
  private void setNotification(Notification notification){
    channels.forEach(c -> {
      c.write()
    });
  }
}
