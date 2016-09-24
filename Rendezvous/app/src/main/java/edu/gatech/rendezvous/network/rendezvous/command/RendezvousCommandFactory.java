package edu.gatech.rendezvous.network.rendezvous.command;

import edu.gatech.rendezvous.network.ApiCommand;

/**
 * Created by jwpilly on 9/24/16.
 */
public class RendezvousCommandFactory {

    private static RendezvousCommandFactory ourInstance = null;

    private RendezvousCommandFactory() {
    }

    public static RendezvousCommandFactory getInstance() {
        if (ourInstance == null) {
            ourInstance = new RendezvousCommandFactory();
        }
        return ourInstance;
    }

    public ApiCommand getFriendListCommand(String username) {
        return new RendezvousFriendList(username);
    }

    public ApiCommand getReminderListCommand(String username) {
        return new RendezvousReminderList(username);
    }

    public ApiCommand getAddDeviceCommand(String username, String deviceId) {
        return new RendezvousAddDevice(username, deviceId);
    }

    public ApiCommand getUsersExistsCommand(String userQuery) {
        return new RendezvousUserExists(userQuery);
    }

    public ApiCommand getRegisterUserCommand(String username, String password) {
        return new RendezvousRegisterUser(username, password);
    }
}
