package com.openmission;

import com.openmission.controller.MailController;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        MailController mailController = new MailController();
        mailController.run();
    }
}
