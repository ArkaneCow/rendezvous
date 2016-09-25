package edu.gatech.rendezvous.network.rendezvous.command;

import edu.gatech.rendezvous.network.ApiCommand;

import java.util.List;

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

    public ApiCommand getUsersExistCommand(String userQuery) {
        return new RendezvousUserExists(userQuery);
    }

    public ApiCommand getRegisterUserCommand(String username, String password) {
        return new RendezvousRegisterUser(username, password);
    }

    public ApiCommand getAuthenticateCommand(String username, String password) {
        return new RendezvousAuthenticate(username, password);
    }

    public ApiCommand getAddFriendCommand(String username, String friendName) {
        return new RendezvousAddFriend(username, friendName);
    }

    public ApiCommand getAddReminderCommand(String userTrigger, String userReceiver, String message) {
        return new RendezvousAddReminder(userTrigger, userReceiver, message);
    }

    public ApiCommand getChangeUserPasswordCommand(String username, String oldPassword, String newPasword) {
        return new RendezvousChangeUserPassword(username, oldPassword, newPasword);
    }

    public ApiCommand getProcessIdListCommand(String username, List<String> idList) {
        return new RendezvousProcessIdList(username, idList);
    }
}
