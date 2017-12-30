package client.ui.manage;

import client.ClientStart;
import client.ui.manage.manager.Manager;
import client.util.ConnDB;

public class ManageManage {
    protected static ConnDB connDB = new ConnDB();

    protected void backToManager() {
        Manager manager = new Manager();
        try {
            manager.init();
            manager.start(ClientStart.getStage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail to go to manager interface");
        }
    }
}
